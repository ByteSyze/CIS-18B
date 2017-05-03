package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import game.command.CommandQueue;
import game.command.ReversibleCommand;
import game.position.Position;

public abstract class Game2D extends JPanel implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -3072628509335710782L;
	
	protected CommandQueue<ReversibleCommand> chessCommandQueue;
	
	protected List<GameComponent> components;
	
	protected List<GameObject> gameObjects;
	
	protected GameObject selectedObject;
	
	public Game2D()
	{
		super();
		
		components = new ArrayList<GameComponent>();
		gameObjects = new ArrayList<GameObject>();
		
		initializePlayers();
		components.addAll(initializeGameComponents());
		
		for(GameComponent c : components)
		{
			if(c instanceof GameObject)
			{
				gameObjects.add((GameObject)c);
			}
		}
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void update()
	{
		for(GameComponent c : components)
		{
			c.fixedUpdate(this);
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		update();
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		draw(g2d);
		
		Position cPos;
		
		for(GameObject obj : gameObjects)
		{
			cPos = obj.getPosition();
			
			int x = (int)(cPos.getX());
			int y = (int)(cPos.getY());
			int width = (int)(obj.getComponentWidth());
			int height = (int)(obj.getComponentHeight());
			
			Graphics2D cg = (Graphics2D)g2d.create(x,y,width,height);
			
			obj.draw(cg);
		}
		
		if(selectedObject != null)
		{
			cPos = selectedObject.getPosition();
			
			int x = (int)(cPos.getX());
			int y = (int)(cPos.getY());
			int width = (int)Math.floor(selectedObject.getComponentWidth());
			int height = (int)Math.floor(selectedObject.getComponentHeight());
			
			Graphics2D cg = (Graphics2D)g2d.create(x,y,width,height);
			selectedObject.highlight(cg);
		}
		
		drawOverlay(g2d);
	}

	public List<GameComponent> getGameComponents()
	{
		return components;
	}
	
	public void mouseClicked(MouseEvent e){}
	
	public void mouseEntered(MouseEvent e){}
	
	public void mouseExited(MouseEvent e){}
	
	public void mousePressed(MouseEvent e){}
	
	public void mouseReleased(MouseEvent e){}
	
	public void mouseMoved(MouseEvent e){}
	
	public void mouseDragged(MouseEvent e){}
	
	public abstract void draw(Graphics2D g);
	
	public abstract void drawOverlay(Graphics2D g);
	
	public abstract String getTitle();
	
	public abstract float getXScale();
	
	public abstract float getYScale();
	
	protected abstract void initializePlayers();
	
	protected abstract List<GameComponent> initializeGameComponents();
}
