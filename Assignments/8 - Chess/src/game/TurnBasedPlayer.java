package game;

public abstract class TurnBasedPlayer implements GameComponent
{
	private int playerID;
	
	public TurnBasedPlayer(int playerID)
	{
		this.playerID = playerID;
	}
	
	public int getID()
	{
		return playerID;
	}
	
	public abstract void onTurn(Game2D game);
}
