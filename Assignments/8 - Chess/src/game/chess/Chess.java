package game.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import game.Game2D;
import game.GameComponent;
import game.GameObject;
import game.chess.piece.*;
import game.chess.piece.ChessPiece.Type;
import game.chess.piece.path.BoundedPath;
import game.chess.piece.path.ChessPath;
import game.chess.piece.path.CowardPath;
import game.chess.piece.path.PathFactory;
import game.chess.piece.path.ServantPath;
import game.chess.piece.path.SimplePath;
import game.chess.piece.path.SymmetricalPath;
import game.command.CaptureCommand;
import game.command.MoveCommand;
import game.command.ReversibleCommandQueue;
import game.position.Position;

public class Chess extends Game2D
{
	private static final long serialVersionUID = 6942133545477058702L;
	
	private static final String TITLE = "Chess";
	
	private final Color[] cellColors = {Color.GRAY, Color.DARK_GRAY};
	
	private float cellSize = 50f;
	
	private float scale = 1f;
	
	private ReversibleCommandQueue commandQueue;
	
	private ChessPiece[][] board; 
	
	private ChessPlayer currentTurn;
	
	private ChessPlayer player1;
	
	private ChessPlayer player2;
	
	public Chess()
	{
		super();
		
		commandQueue = new ReversibleCommandQueue();
		
		currentTurn = player1;
		
		this.setMinimumSize(new Dimension(300,300));
		this.setPreferredSize(this.getMinimumSize());
	}
	
	public void fireUpdate()
	{
		super.fireUpdate();
		
		for(GameComponent c : components)
		{
			c.update(this);
		}
	}

	@Override
	public void draw(Graphics2D g)
	{
		float smallestAxis = getHeight() < getWidth() ? getHeight() : getWidth();
		scale = smallestAxis/400f;
		
		g.scale(scale, scale);
		
		int colorIdx = 0;
		
		for(int i = 0; i < 8; i++)
		{
			colorIdx++;
			
			for(int j = 0; j < 8; j++)
			{
				g.setColor(cellColors[colorIdx%cellColors.length]);
				
				g.fillRect((int)(i*cellSize), (int)(j*cellSize), (int)(cellSize), (int)(cellSize));
				
				colorIdx++;
			}
		}
	}
	
	public void drawOverlay(Graphics2D g)
	{
		if(selectedObject != null)
		{
			ChessPiece selectedPiece = (ChessPiece)selectedObject;
			Position anchor = selectedPiece.getBoardPosition();
			
			int offset = (int)(cellSize/4);
			int moveX, moveY;
			
			g.setColor(Color.GREEN);
			
			for(ChessMove move : selectedPiece.getValidMoves())
			{
				for(ChessMove chain : move.asList())
				{
					moveX = (int)((anchor.getX()+chain.getX())*cellSize)+offset;
					moveY = (int)((anchor.getY()+chain.getY())*cellSize)+offset;
					
					g.fillOval(moveX, moveY, 25, 25);
				}
			}
		}
	}
	
	protected void initializePlayers()
	{
		player1 = new ChessPlayer(1, Color.WHITE);
		player2 = new ChessPlayer(2, Color.BLACK);
		//player2 = new ChessAIPlayer(2, Color.BLACK);
		
		components.add(player1);
		components.add(player2);
	}

	@Override
	protected List<GameComponent> initializeGameComponents()
	{
		board = new ChessPiece[8][8];
		
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
	
	private ChessPiece createPiece(ChessPlayer owner, Position boardPosition, ChessPiece.Type type)
	{
		ChessPiece piece = new ChessPiece(owner, boardPosition, type);
		
		piece.setPath(PathFactory.createPath(this, piece));
		
		owner.addAlivePiece(piece);
		this.setPieceAt(piece.getBoardPosition(), piece);
		
		if(type == Type.KING)
			owner.setKing(piece);
		
		return piece;
	}
	
	public ChessPiece[][] getBoard()
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
	public ChessPiece getPieceAt(Position boardPosition)
	{
		return board[(int)boardPosition.getX()][(int)boardPosition.getY()];
	}
	
	/**
	 * Sets the ChessPiece at the specified location of the chess board.
	 * 
	 * @param boardPosition the position on the chess board to place the ChessPiece
	 * @param piece the ChessPiece to be set
	 * */
	public void setPieceAt(Position boardPosition, ChessPiece piece)
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
			ChessPiece selectedPiece = (ChessPiece)selectedObject;
			
			if(selectedPiece.getOwner() == currentTurn)
			{
				Rectangle2D.Float optionBounds = new Rectangle2D.Float();
				
				Position anchor = selectedPiece.getBoardPosition();
	
				int moveX, moveY;
				
				for(ChessMove move : selectedPiece.getValidMoves())
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
							ChessPiece captured = null;
							
							for(ChessPiece p : opponent.getAlivePieces())
							{
								if(p.getBoardPosition().equals(Position.add(selectedPiece.getBoardPosition(), dependentMove)))
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
								commandQueue.add(new MoveCommand(this, selectedPiece, dependentMove));
							}

							commandQueue.executeNextCommand();
							
							selectedObject = null;
							
							fireTurnChangeEvent();
							
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
					if(obj instanceof ChessPiece)
					{
						if(!((ChessPiece)obj).isActive())
						{
							continue;
						}
						else if(((ChessPiece)obj).getOwner() != currentTurn)
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
