package game.position;

public class Position implements Cloneable
{
	private float x,y;
	
	public Position(){}
	
	public Position(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void move(Position displacement)
	{
		this.x += displacement.getX();
		this.y += displacement.getY();
	}
	
	public double distance(Position p)
	{
		return Math.sqrt(distanceSq(p));
	}
	
	public double distanceSq(Position p)
	{
		return Math.pow(getX()-p.getX(), 2) + Math.pow(getY()-p.getY(), 2);
	}
	
	public String toString()
	{
		return String.format("(%f, %f)", getX(), getY());
	}
	
	public Position clone()
	{
		return new Position(x,y);
	}
	
	public boolean equals(Position position)
	{
		return (getX() == position.getX()) && (getY() == position.getY());
	}
	
	public static Position add(Position p1, Position p2)
	{
		return new Position(p1.getX()+p2.getX(), p1.getY()+p2.getY());
	}
	
	public static Position subtract(Position p1, Position p2)
	{
		return new Position(p1.getX()-p2.getX(), p1.getY()-p2.getY());
	}
}
