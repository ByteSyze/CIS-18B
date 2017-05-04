package game.chess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Game2D;
import game.TurnBasedPlayer;
import game.chess.piece.ChessPiece;
import game.position.Position;

public class ChessPlayer extends TurnBasedPlayer
{
	private Color color;
	
	private ChessPiece king;
	
	private List<ChessPiece> alivePieces;
	private List<ChessPiece> slainPieces;
	
	private int[][] validMoveMap = new int[8][8];
	
	public ChessPlayer(int playerID, Color color)
	{
		super(playerID);
		
		this.color = color;
		
		this.alivePieces = new ArrayList<ChessPiece>(16);
		this.slainPieces = new ArrayList<ChessPiece>();
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
		for(ChessPiece p : alivePieces)
		{
			p.invalidateMoves();
		}
	}
	
	public void generateValidMoveMap()
	{
		validMoveMap = new int[8][8];
		
		for(ChessPiece p : alivePieces)
		{
			for(ChessMove move : p.getValidMoves())
			{
				for(ChessMove chain : move.asList())
				{
					Position chainPos = Position.add(p.getBoardPosition(), chain);
					
					validMoveMap[(int)chainPos.getX()][(int)chainPos.getY()] = 1;
				}
			}
		}
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void addAlivePiece(ChessPiece alive)
	{
		alivePieces.add(alive);
	}
	
	public void setKing(ChessPiece king)
	{
		this.king = king;
	}
	
	public ChessPiece getKing()
	{
		return king;
	}
	
	public List<ChessPiece> getAlivePieces()
	{
		return alivePieces;
	}
	
	public void setAlivePieces(List<ChessPiece> pieces)
	{
		alivePieces = pieces;
	}
	
	public List<ChessPiece> getSlainPieces()
	{
		return slainPieces;
	}
	
	public void setSlainPieces(List<ChessPiece> pieces)
	{
		slainPieces = pieces;
	}
	
	public int[][] getValidMoveMap()
	{
		return validMoveMap;
	}
}
