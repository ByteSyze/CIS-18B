package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import game.position.Position;
import game.position.Vector;

public class ChessPieceView 
{
	private Vector position;
	
	private ChessPiece model;
	
	/**The local scale of {@code model}.*/
	private float modelScale = .75f;
	
	/**X-Offset for centering {@code model} during {@link ChessPiece#draw(Graphics2D) draw()}.*/
	private int xOffset = 0;

	/**Y-Offset for centering {@code model} during {@link ChessPiece#draw(Graphics2D) draw()}.*/
	private int yOffset = 0;
	
	public ChessPieceView(ChessPiece model)
	{
		this.model = model;
		
		this.position = new Vector(model.getBoardPosition());
		
		this.position.setScale(50f);

		this.xOffset = (int)(((Rectangle2D)model.getBounds()).getWidth() - (model.getComponentWidth()*modelScale))/2;
		this.yOffset = (int)(((Rectangle2D)model.getBounds()).getHeight() - (model.getComponentHeight()*modelScale))/2;
	}
	
	public Position getPosition()
	{
		return position;
	}
	
	public void draw(Graphics2D g)
	{
		g.translate(xOffset, yOffset);	 // Center the ChessPiece model.
		g.scale(modelScale, modelScale); // Apply scaling
		
		g.setColor(model.getOwner().getColor());
		g.fill(model.getMesh());
	}
	
	public void highlight(Graphics2D g) 
	{
		g.translate(xOffset, yOffset);
		g.scale(modelScale, modelScale);
		
		g.setColor(Color.GREEN);
		g.draw(model.getMesh());
	}

}
