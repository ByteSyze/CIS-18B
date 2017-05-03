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
import game.chess.piece.ChessPiece;
import game.chess.piece.King;
import game.chess.piece.Knight;
import game.chess.piece.BoundedPiece;
import game.chess.piece.Rook;
import game.chess.piece.ServantPiece;
import game.chess.piece.SymmetricalPiece;
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
				ChessMove dependentMove = move;
				
				while(dependentMove != null)
				{
					moveX = (int)((anchor.getX()+dependentMove.getX())*cellSize)+offset;
					moveY = (int)((anchor.getY()+dependentMove.getY())*cellSize)+offset;
					
					g.fillOval(moveX, moveY, 25, 25);
					
					dependentMove = dependentMove.getNextMove();
				}
			}
		}
	}
	
	protected void initializePlayers()
	{
		player1 = new ChessPlayer(1, Color.WHITE);
		player2 = new ChessPlayer(2, Color.BLACK);
		
		components.add(player1);
		components.add(player2);
	}

	@Override
	protected List<GameComponent> initializeGameComponents()
	{
		board = new ChessPiece[8][8];
		
		List<GameComponent> chessPieces = new ArrayList<GameComponent>();

		addsome(chessPieces);
		
		return chessPieces;
	}

	private void addsome(List<GameComponent> chessPieces)
	{
		ChessPiece k = new King(player1, new Position(4,0));
		k = new SymmetricalPiece(k);
		k = new BoundedPiece(k, this);
		
		player1.setKing(k);
		player1.addAlivePiece(k);
		chessPieces.add(k);
		this.setPieceAt(k.getBoardPosition(), k);
		
		ChessPiece knight = new Knight(player1, new Position(1,0));
		knight = new SymmetricalPiece(knight);
		knight = new BoundedPiece(knight, this);
		knight = new ServantPiece(knight, this);
		
		player1.addAlivePiece(knight);
		chessPieces.add(knight);
		this.setPieceAt(knight.getBoardPosition(), knight);
		
		ChessPiece rook = new Rook(player1, new Position(0,0));
		rook = new SymmetricalPiece(rook);
		rook = new BoundedPiece(rook, this);
		rook = new ServantPiece(rook, this);
		
		player1.addAlivePiece(rook);
		chessPieces.add(rook);
		this.setPieceAt(rook.getBoardPosition(), rook);
		
		ChessPiece k2 = new King(player2, new Position(4,7));
		k2 = new SymmetricalPiece(k2);
		k2 = new BoundedPiece(k2, this);
		
		player2.setKing(k2);
		player2.addAlivePiece(k2);
		chessPieces.add(k2);
		this.setPieceAt(k2.getBoardPosition(), k2);
		
		ChessPiece knight2 = new Knight(player2, new Position(6,7));
		knight2 = new SymmetricalPiece(knight2);
		knight2 = new BoundedPiece(knight2, this);
		knight2 = new ServantPiece(knight2, this);
		
		player2.addAlivePiece(knight2);
		chessPieces.add(knight2);
		this.setPieceAt(knight2.getBoardPosition(), knight2);
		
		ChessPiece rook2 = new Rook(player2, new Position(7,7));
		rook2 = new SymmetricalPiece(rook2);
		rook2 = new BoundedPiece(rook2, this);
		rook2 = new ServantPiece(rook2, this);
		
		player2.addAlivePiece(rook2);
		chessPieces.add(rook2);
		this.setPieceAt(rook2.getBoardPosition(), rook2);
	}
	
	public ChessPiece[][] getBoard()
	{
		return board;
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
							
							currentTurn = (currentTurn == player1) ? player2 : player1;
							
							player1.onTurn(this);
							player2.onTurn(this);
							
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
						if(((ChessPiece)obj).getOwner() != currentTurn)
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
