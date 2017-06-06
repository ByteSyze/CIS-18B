package game.chess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Game2D;
import game.TurnBasedPlayer;
import game.chess.piece.ChessPieceController;
import game.position.Position;

public class ChessPlayer extends TurnBasedPlayer
{
	private Color color;
	
	private ChessPieceController king;
	
	private List<ChessPieceController> alivePieces;
	private List<ChessPieceController> slainPieces;
	
	private int[][] validMoveMap = new int[8][8];
	
	public ChessPlayer(int playerID, Color color)
	{
		super(playerID);
		
		this.color = color;
		
		this.alivePieces = new ArrayList<ChessPieceController>(16);
		this.slainPieces = new ArrayList<ChessPieceController>();
	}

	@Override
	public void update(Game2D game){}

	@Override
	public void onTurn(Game2D game)
	{
		generateValidMoveMap();
	}
	
	/**
	 * Invalidates all cached moves for all of this player's alive chess pieces.
	 * */
	public void invalidateMoves()
	{
		for(ChessPieceController p : alivePieces)
		{
			p.getModel().invalidateMoves();
		}
	}
	
	public void generateValidMoveMap()
	{
		validMoveMap = new int[8][8];
		
		for(ChessPieceController p : alivePieces)
		{
			for(ChessMove move : p.getModel().getValidMoves())
			{
				for(ChessMove chain : move.asList())
				{
					Position chainPos = Position.add(p.getModel().getBoardPosition(), chain);
					
					validMoveMap[(int)chainPos.getX()][(int)chainPos.getY()] = 1;
				}
			}
		}
	}
	
	/**
	 * Returns true if this player has at least one valid move to make.
	 * */
	public boolean hasValidMoves()
	{
		for(ChessPieceController c : getAlivePieces())
		{
			if(c.getModel().getValidMoves().size() > 0)
				return true;
		}
		
		return false;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void addAlivePiece(ChessPieceController alive)
	{
		alivePieces.add(alive);
	}
	
	public void setKing(ChessPieceController king)
	{
		this.king = king;
	}
	
	public ChessPieceController getKing()
	{
		return king;
	}
	
	public List<ChessPieceController> getAlivePieces()
	{
		return alivePieces;
	}
	
	public void setAlivePieces(List<ChessPieceController> pieces)
	{
		alivePieces = pieces;
	}
	
	public List<ChessPieceController> getSlainPieces()
	{
		return slainPieces;
	}
	
	public void setSlainPieces(List<ChessPieceController> pieces)
	{
		slainPieces = pieces;
	}
	
	public int[][] getValidMoveMap()
	{
		return validMoveMap;
	}
}
