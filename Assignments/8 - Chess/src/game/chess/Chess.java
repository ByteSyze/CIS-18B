package game.chess;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import game.Game2D;
import game.GameComponent;
import game.GameObject;
import game.chess.listener.CommandUndoListener;
import game.chess.piece.*;
import game.chess.piece.ChessPiece.Type;
import game.chess.piece.path.PathFactory;
import game.command.CaptureCommand;
import game.command.MoveCommand;
import game.command.PromoteCommand;
import game.command.ReversibleCommandQueue;
import game.position.Position;

public class Chess extends Game2D
{
	private static final long serialVersionUID = 6942133545477058702L;
	
	private static final String TITLE = "Chess";
	
	private final Paint[] cellPaints = new Paint[2];
	
	private float cellSize = 50f;
	
	private float scale = 1f;
	
	private ReversibleCommandQueue commandQueue;
	
	/**Underlying board layout.*/
	private ChessPieceController[][] board; 
	
	private ChessPlayer currentTurn;
	
	private ChessPlayer player1;
	
	private ChessPlayer player2;
	
	private ChessPlayer winner;
	
	private boolean gameOver = false;
	
	private int boardWidth, boardHeight;
	
	//UI stuff
	
	private JButton undoButton, redoButton;
	
	public Chess()
	{
		super();
		
		commandQueue = new ReversibleCommandQueue();
		
		currentTurn = player1;
		
		//
		//Generate checker board textures
		//
		//Marble texture
		Point2D start = new Point2D.Float(0, 0);
		Point2D end = new Point2D.Float(8, 10);
		float[] dist = {0.2f, 0.25f, 0.7f};
		Color[] colors = {new Color(225, 199, 138),
						  new Color(225, 199, 138),
						  new Color(228, 221, 163)};
		
		LinearGradientPaint p = new LinearGradientPaint(start, end, dist, colors, MultipleGradientPaint.CycleMethod.REFLECT);
		
		cellPaints[0] = p;
		
		//Wood texture
		end.setLocation(-5, 5);
		
		colors[0] = new Color(60, 25, 14);
		colors[1] = new Color(103, 40, 28);
		colors[2] = new Color(143, 50, 28);
		
		p = new LinearGradientPaint(start, end, dist, colors, MultipleGradientPaint.CycleMethod.REPEAT);
		
		cellPaints[1] = p;
		
		this.setMinimumSize(new Dimension(740,480));
		this.setPreferredSize(this.getMinimumSize());
	}
	
	public void update()
	{
		if(!player1.hasValidMoves())
		{
			gameOver = true;
			winner = player2;
		}
		else if(!player2.hasValidMoves())
		{
			gameOver = true;
			winner = player1;
		}
		else
		{
			gameOver = false;
			winner = null;
		}
	}

	@Override
	public void draw(Graphics2D g)
	{	
		//boardWidth and boardHeight determine the maximum window real estate the chess board can use.
		boardWidth = getWidth();
		boardHeight = getHeight();
		
		float smallestAxis = boardHeight < boardWidth ? boardHeight : boardWidth;
		scale = smallestAxis/(cellSize*8);
		
		g.scale(scale, scale);
		
		int colorIdx = 0;
		
		//Draw the checkerboard
		for(int i = 0; i < 8; i++)
		{
			colorIdx++;
			
			for(int j = 0; j < 8; j++)
			{
				g.setPaint(cellPaints[colorIdx%cellPaints.length]);
				//g.setColor(cellColors[colorIdx%cellColors.length]);
				
				g.fillRect((int)(i*cellSize), (int)(j*cellSize), (int)(cellSize), (int)(cellSize));
				
				colorIdx++;
			}
		}
	}
	
	public void drawOverlay(Graphics2D g)
	{
		if(selectedObject != null)
		{
			ChessPieceController selectedPiece = (ChessPieceController)selectedObject;
			Position anchor = selectedPiece.getModel().getBoardPosition();
			
			int offset = (int)(cellSize/4);
			int moveX, moveY;
			
			g.setColor(Color.GREEN);
			
			for(ChessMove move : selectedPiece.getModel().getValidMoves())
			{
				if(move.isAttack())
					g.setColor(Color.RED);
				else
					g.setColor(Color.GREEN);
				
				for(ChessMove chain : move.asList())
				{
					moveX = (int)((anchor.getX()+chain.getX())*cellSize)+offset;
					moveY = (int)((anchor.getY()+chain.getY())*cellSize)+offset;
					
					g.fillOval(moveX, moveY, 25, 25);
				}
			}
		}
		if(isGameOver())
		{
			g.setColor(Color.WHITE);
			g.drawString(winner + " wins!", 3*cellSize,4*cellSize);
		}
	}
	
	protected JPanel initializeWrapper()
	{
		JPanel wrapper = new JPanel();
		
		wrapper.setLayout(new BorderLayout());
		
		wrapper.add(this, BorderLayout.CENTER);
		
		JPanel buttonContainer = new JPanel();
		
		buttonContainer.setLayout(new GridLayout(1, 1));
		
		undoButton = new JButton("Undo Last Move");
		redoButton = new JButton("Redo Last Move");
		
		CommandUndoListener undoListener = new CommandUndoListener(this);
		
		undoButton.addActionListener(undoListener);
		redoButton.addActionListener(undoListener);
		
		buttonContainer.add(undoButton);
		//buttonContainer.add(redoButton);
		
		wrapper.add(buttonContainer, BorderLayout.SOUTH);
		
		return wrapper;
	}
	
	protected void initializePlayers()
	{
		player1 = new ChessPlayer(1, Color.WHITE);
		player2 = new ChessPlayer(2, Color.BLACK);
		//player2 = new ChessAIPlayer(2, Color.BLACK);
		
		components.add(player1);
		components.add(player2);
	}

	protected List<GameComponent> initializeGameComponents()
	{
		board = new ChessPieceController[8][8];
		
		List<GameComponent> chessPieces = new ArrayList<GameComponent>();

		ChessPiece.Type[][] boardLayout = 
			{{Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.KING, Type.QUEEN, Type.BISHOP, Type.KNIGHT, Type.ROOK},
			 {Type.PAWN, Type.PAWN,   Type.PAWN,   Type.PAWN, Type.PAWN,  Type.PAWN,   Type.PAWN,   Type.PAWN}};
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				chessPieces.add(createPiece(player1, new Position(i,j), boardLayout[j][i]));
				chessPieces.add(createPiece(player2, new Position(i,7-j), boardLayout[j][i]));
			}
		}
		
		return chessPieces;
	}
	
	/**
	 * Creates a new ChessPieceController with the given parameters.
	 * 
	 * @param	owner			the owner of the new ChessPiece
	 * @param	boardPosition	the initial board position of the ChessPiece
	 * @param	type			the type of ChessPiece to create
	 * */
	public ChessPieceController createPiece(ChessPlayer owner, Position boardPosition, ChessPiece.Type type)
	{
		ChessPiece model = new ChessPiece(owner, boardPosition, type);
		ChessPieceController controller = new ChessPieceController(model);
		
		model.setPath(PathFactory.createPath(this, controller));
		
		owner.addAlivePiece(controller);
		this.setPieceAt(model.getBoardPosition(), controller);
		
		if(type == Type.KING)
			owner.setKing(controller);
		
		return controller;
	}
	
	public ChessPieceController[][] getBoard()
	{
		return board;
	}
	
	/**
	 * Switches currentTurn to the next ChessPlayer and calls their onTurn() method.
	 * */
	public void fireTurnChangeEvent()
	{
		ChessPlayer lastTurn = currentTurn;
		currentTurn = (currentTurn == player1) ? player2 : player1;
		
		currentTurn.invalidateMoves();
		
		lastTurn.generateValidMoveMap();
		currentTurn.onTurn(this);
	}
	
	public ReversibleCommandQueue getCommandQueue()
	{
		return commandQueue;
	}
	
	/**
	 * Invalidates all cached moves for both Chess players.
	 * */
	public void invalidateMoves()
	{
		player1.invalidateMoves();
		player2.invalidateMoves();
	}
	
	/**
	 * Returns the ChessPiece at the specified location on the chess board.
	 * 
	 * @return the ChessPiece at {@code boardPosition}, or null if no ChessPiece is at that location.
	 * */
	public ChessPieceController getPieceAt(Position boardPosition)
	{
		return board[(int)boardPosition.getX()][(int)boardPosition.getY()];
	}
	
	/**
	 * Sets the ChessPiece at the specified location of the chess board.
	 * 
	 * @param boardPosition the position on the chess board to place the ChessPiece
	 * @param piece the ChessPiece to be set
	 * */
	public void setPieceAt(Position boardPosition, ChessPieceController piece)
	{
		board[(int)boardPosition.getX()][(int)boardPosition.getY()] = piece;
	}
	
	public ChessPlayer getCurrentTurn()
	{
		return currentTurn;
	}
	
	public ChessPlayer getPlayerOne()
	{
		return player1;
	}
	
	public ChessPlayer getPlayerTwo()
	{
		return player2;
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public float getCellSize()
	{
		return cellSize;
	}
	
	public float getXScale()
	{
		return scale;
	}
	
	public float getYScale()
	{
		return scale;
	}

	@Override
	public String getTitle()
	{
		return TITLE;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		boolean moved = false;
		
		if(selectedObject != null)
		{
			ChessPieceController selectedPiece = (ChessPieceController)selectedObject;
			
			if(selectedPiece.getOwner() == currentTurn)
			{
				Rectangle2D.Float optionBounds = new Rectangle2D.Float();
				
				Position anchor = selectedPiece.getModel().getBoardPosition();
	
				int moveX, moveY;
				
				for(ChessMove move : selectedPiece.getModel().getValidMoves())
				{
					ChessMove dependentMove = move;
					
					while(dependentMove != null)
					{
						moveX = (int)((anchor.getX()+dependentMove.getX())*cellSize*scale);
						moveY = (int)((anchor.getY()+dependentMove.getY())*cellSize*scale);
						
						optionBounds.setRect(moveX, moveY, cellSize*scale, cellSize*scale);
						
						if(optionBounds.contains(e.getPoint()))
						{
							ChessPlayer opponent = (currentTurn == player1) ? player2 : player1;
							ChessPieceController captured = null;
							
							for(ChessPieceController p : opponent.getAlivePieces())
							{
								if(p.getModel().getBoardPosition().equals(Position.add(selectedPiece.getModel().getBoardPosition(), dependentMove)))
								{
									captured = p;
									break;
								}
							}
							
							if(captured != null)
							{
								commandQueue.add(new CaptureCommand(this, selectedPiece, captured));
							}
							else
							{
								boolean doSimpleMove = true;
								if(selectedPiece.getModel().getType() == Type.PAWN)
								{
									if(selectedPiece.getModel().getBoardPosition().getY() == 6 && selectedPiece.getOwner() == player1 || 
											selectedPiece.getModel().getBoardPosition().getY() == 1 && selectedPiece.getOwner() == player2)
									{
										System.out.println("Promote");
										doSimpleMove = false;
										commandQueue.add(new PromoteCommand(this, selectedPiece, dependentMove));
									}
								}
								
								//Only add a MoveCommand if a more complicated move did not occur.
								if(doSimpleMove)
									commandQueue.add(new MoveCommand(this, selectedPiece, dependentMove));
							}

							commandQueue.executeNextCommand();
							
							selectedObject = null;
							
							moved = true;
							
							break;
						}
						
						dependentMove = dependentMove.getNextMove();
					}
					
					if(moved)
						break;
				}
			}
		}
		
		if(!moved)
		{
			selectedObject = null;
			
			for(GameObject obj : gameObjects)
			{
				if(obj.getBounds().contains(e.getPoint()))
				{
					if(obj instanceof ChessPieceController)
					{
						if(!((ChessPieceController)obj).isActive())
						{
							continue;
						}
						else if(((ChessPieceController)obj).getOwner() != currentTurn)
						{
							return;
						}
					}
					
					selectedObject = obj;
					break;
				}
			}
		}
	}

}
