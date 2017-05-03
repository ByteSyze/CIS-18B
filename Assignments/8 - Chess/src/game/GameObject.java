package game;

import java.awt.Graphics2D;
import java.awt.Shape;

import game.position.Position;

public interface GameObject extends GameComponent
{
	public void draw(Graphics2D g);
	
	public void highlight(Graphics2D g);
	
	public Position getPosition();
	
	public Shape getBounds();
	
	public float getComponentHeight();
	
	public float getComponentWidth();
}
