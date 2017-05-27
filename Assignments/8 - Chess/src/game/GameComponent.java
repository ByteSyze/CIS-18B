package game;

public interface GameComponent 
{
	/**
	 * Updates this component.
	 * 
	 * <p>
	 * Fired any time an instance of {@link Game2D} containing this
	 * GameComponent is updated. Time intervals between updates are 
	 * not fixed.
	 * </p>
	 * 
	 * @param	game	the Game2D that updated this component
	 * */
	public void update(Game2D game);
}
