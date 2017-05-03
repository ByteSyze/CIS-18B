package game.chess.piece;

import java.awt.Polygon;

class ChessModels 
{
	public enum Type
	{
		KING,
		QUEEN,
		ROOK,
		KNIGHT,
		BISHOP,
		PAWN
	}
	
	public static ChessModels.Model create(ChessModels.Type type)
	{
		switch(type)
		{
			case KING:
				return new King();
			case QUEEN:
				return new Queen();
			case ROOK:
				return new Rook();
			case KNIGHT:
				return new Knight();
			case BISHOP:
				return new Bishop();
			case PAWN:
				return new Pawn();
			default:
				return null;
		}
	}
	
	static abstract class Model extends Polygon
	{
		public Model()
		{
			super();
			createModel();
		}
		
		protected abstract void createModel();
	}
	
	static class King extends Model
	{
			protected void createModel()
			{
			}
	}
	
	static class Queen extends Model
	{
			protected void createModel()
			{
				this.addPoint(0,50);
				this.addPoint(50, 50);
				this.addPoint(48, 45);
				this.addPoint(45, 28);
			}
	}
	
	static class Rook extends Model
	{
			protected void createModel()
			{
				this.addPoint(0,50);
				this.addPoint(50, 50);
				this.addPoint(50, 40);
				this.addPoint(45, 40);
				this.addPoint(45, 0);
				this.addPoint(35, 0);
				this.addPoint(35, 10);
				this.addPoint(15, 10);
				this.addPoint(15, 0);
				this.addPoint(5, 0);
				this.addPoint(5, 40);
				this.addPoint(0, 40);
			}
	}
	
	static class Knight extends Model
	{
			protected void createModel()
			{
				this.addPoint(0,50);
				this.addPoint(50, 50);
				this.addPoint(48, 45);
				this.addPoint(45, 28);
			}
	}
	
	static class Bishop extends Model
	{
			protected void createModel()
			{
				this.addPoint(0,50);
				this.addPoint(50, 50);
				this.addPoint(48, 45);
				this.addPoint(45, 28);
			}
	}
	
	static class Pawn extends Model
	{
			protected void createModel()
			{
				this.addPoint(0,50);
				this.addPoint(50, 50);
				this.addPoint(48, 45);
				this.addPoint(45, 28);
			}
	}
}
