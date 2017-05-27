package game.chess.piece;

import java.awt.Graphics2D;
import java.awt.Shape;

import game.Game2D;
import game.GameObject;
import game.chess.ChessPlayer;
import game.position.Position;
import game.position.Vector;

public class ChessPieceController implements GameObject
{
	
	private static final float POSITION_SNAP_THRESH = 0.5f;
	
	ChessPiece model;
	ChessPieceView view;

	/**Indicates whether this piece has been captured or not.*/
	private boolean isCaptured = false;
	
	public ChessPieceController(ChessPiece model)
	{
		this.model = model;
		
		this.view = new ChessPieceView(model);
	}
	
	public void update(Game2D game)
	{
		//TODO use transforms to help with all of this junk.
		
		//Move the ChessPieceView's position towards the ChessPiece's board position
		Vector scaledBoardPos = new Vector(model.getBoardPosition());
		Vector pos = (Vector)getPosition();
		
		scaledBoardPos.setScale(pos.getScale());
		
		if(pos.distanceSq(scaledBoardPos) > POSITION_SNAP_THRESH)
		{
			pos.move(new Position((scaledBoardPos.getX()-pos.getX())/500f,(scaledBoardPos.getY()-pos.getY())/500f));
		}
		else if((pos.getX() != scaledBoardPos.getX()) || (pos.getY() != scaledBoardPos.getY()))
		{
			pos.setX(scaledBoardPos.getUnscaledX());
			pos.setY(scaledBoardPos.getUnscaledY());
		}
		
		model.bounds.setRect(view.getPosition().getX()*game.getXScale(), view.getPosition().getY()*game.getYScale(), getComponentWidth()*game.getXScale(), getComponentHeight()*game.getYScale());
	}

	public void draw(Graphics2D g) 
	{	
		view.draw(g);
	}

	public void highlight(Graphics2D g) 
	{
		view.highlight(g);
	}
	
	public void setCaptured(boolean captured)
	{
		this.isCaptured = captured;
	}
	
	public ChessPiece getModel()
	{
		return model;
	}
	
	public ChessPlayer getOwner()
	{
		return model.getOwner();
	}
	
	public Position getPosition()
	{
		return view.getPosition();
	}
	
	public float getComponentHeight()
	{
		return model.getComponentHeight();
	}

	public float getComponentWidth() 
	{
		return model.getComponentWidth();
	}
	
	public boolean isCaptured()
	{
		return isCaptured;
	}

	public boolean isActive()
	{
		return !isCaptured();
	}

	public Shape getBounds() 
	{
		return model.getBounds();
	}

}
