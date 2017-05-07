package game.chess.piece;

import java.awt.Polygon;

class ChessModels 
{	
	public static ChessModels.Model create(ChessPiece.Type type)
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
				this.addPoint(0, 0);
				this.addPoint(50, 0);
				this.addPoint(50,50);
				this.addPoint(0, 50);
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
				this.addPoint((int)50.0,(int)55.0);
				this.addPoint((int)50.0,(int)50.0);
				this.addPoint((int)45.0,(int)50.0);
				this.addPoint((int)45.0,(int)42.0);
				this.addPoint((int)40.0,(int)37.0);
				this.addPoint((int)40.0,(int)13.0);
				this.addPoint((int)45.0,(int)8.0);
				this.addPoint((int)45.0,(int)0.0);
				this.addPoint((int)37.0,(int)0.0);
				this.addPoint((int)37.0,(int)5.0);
				this.addPoint((int)29.0,(int)5.0);
				this.addPoint((int)29.0,(int)0.0);
				this.addPoint((int)21.0,(int)0.0);
				this.addPoint((int)21.0,(int)5.0);
				this.addPoint((int)13.0,(int)5.0);
				this.addPoint((int)13.0,(int)0.0);
				this.addPoint((int)5.0,(int)0.0);
				this.addPoint((int)5.0,(int)8.0);
				this.addPoint((int)11.0,(int)13.0);
				this.addPoint((int)11.0,(int)37.0);
				this.addPoint((int)5.0,(int)42.0);
				this.addPoint((int)5.0,(int)50.0);
				this.addPoint((int)0.0,(int)50.0);
				this.addPoint((int)0.0,(int)55.0);
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
				this.addPoint((int)50.0,(int)50.0);
				this.addPoint((int)47.0,(int)48.0);
				this.addPoint((int)43.0,(int)47.0);
				this.addPoint((int)34.0,(int)48.0);
				this.addPoint((int)32.0,(int)48.0);
				this.addPoint((int)28.0,(int)47.0);
				this.addPoint((int)25.0,(int)45.0);
				this.addPoint((int)32.0,(int)43.0);
				this.addPoint((int)34.0,(int)42.0);
				this.addPoint((int)36.0,(int)38.0);
				this.addPoint((int)34.0,(int)35.0);
				this.addPoint((int)30.0,(int)32.0);
				this.addPoint((int)34.0,(int)30.0);
				this.addPoint((int)36.0,(int)29.0);
				this.addPoint((int)40.0,(int)26.0);
				this.addPoint((int)40.0,(int)22.0);
				this.addPoint((int)36.0,(int)17.0);
				this.addPoint((int)32.0,(int)14.0);
				this.addPoint((int)25.0,(int)11.0);
				this.addPoint((int)30.0,(int)9.0);
				this.addPoint((int)32.0,(int)6.0);
				this.addPoint((int)32.0,(int)5.0);
				this.addPoint((int)30.0,(int)1.0);
				this.addPoint((int)25.0,(int)0.0);
				this.addPoint((int)23.0,(int)0.0);
				this.addPoint((int)20.0,(int)1.0);
				this.addPoint((int)17.0,(int)5.0);
				this.addPoint((int)17.0,(int)6.0);
				this.addPoint((int)20.0,(int)9.0);
				this.addPoint((int)23.0,(int)11.0);
				this.addPoint((int)17.0,(int)14.0);
				this.addPoint((int)12.0,(int)17.0);
				this.addPoint((int)10.0,(int)22.0);
				this.addPoint((int)10.0,(int)26.0);
				this.addPoint((int)12.0,(int)29.0);
				this.addPoint((int)15.0,(int)30.0);
				this.addPoint((int)20.0,(int)32.0);
				this.addPoint((int)15.0,(int)35.0);
				this.addPoint((int)12.0,(int)38.0);
				this.addPoint((int)15.0,(int)42.0);
				this.addPoint((int)17.0,(int)43.0);
				this.addPoint((int)23.0,(int)45.0);
				this.addPoint((int)21.0,(int)47.0);
				this.addPoint((int)17.0,(int)48.0);
				this.addPoint((int)15.0,(int)48.0);
				this.addPoint((int)6.0,(int)47.0);
				this.addPoint((int)3.0,(int)48.0);
				this.addPoint((int)0.0,(int)50.0);
			}
	}
	
	static class Pawn extends Model
	{
			protected void createModel()
			{
				this.addPoint((int)50.0,(int)50.0);
				this.addPoint((int)47.0,(int)40.0);
				this.addPoint((int)43.0,(int)35.0);
				this.addPoint((int)39.0,(int)33.0);
				this.addPoint((int)31.0,(int)31.0);
				this.addPoint((int)39.0,(int)28.0);
				this.addPoint((int)43.0,(int)23.0);
				this.addPoint((int)43.0,(int)19.0);
				this.addPoint((int)39.0,(int)14.0);
				this.addPoint((int)31.0,(int)12.0);
				this.addPoint((int)35.0,(int)9.0);
				this.addPoint((int)35.0,(int)2.0);
				this.addPoint((int)31.0,(int)0.0);
				this.addPoint((int)20.0,(int)0.0);
				this.addPoint((int)16.0,(int)2.0);
				this.addPoint((int)16.0,(int)9.0);
				this.addPoint((int)20.0,(int)12.0);
				this.addPoint((int)12.0,(int)14.0);
				this.addPoint((int)8.0,(int)19.0);
				this.addPoint((int)8.0,(int)23.0);
				this.addPoint((int)12.0,(int)28.0);
				this.addPoint((int)20.0,(int)31.0);
				this.addPoint((int)12.0,(int)33.0);
				this.addPoint((int)8.0,(int)35.0);
				this.addPoint((int)4.0,(int)40.0);
				this.addPoint((int)0.0,(int)50.0);

			}
	}
}
