package game.position;

public class Vector extends Position
{
	private float scale = 1f;
	
	public Vector(float x, float y, float scale)
	{
		super(x,y);
		
		this.scale = scale;
	}
	
	public Vector(Position position)
	{
		super(position.getX(), position.getY());
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
	}
	
	public float getScale()
	{
		return this.scale;
	}
	
	public float getX()
	{
		return super.getX()*scale;
	}
	
	public float getY()
	{
		return super.getY()*scale;
	}
	
	public float getUnscaledX()
	{
		return super.getX();
	}
	
	public float getUnscaledY()
	{
		return super.getY();
	}
}
