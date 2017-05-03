package game;

import game.chess.Chess;

public class Main 
{
	public static void main(String[] args)
	{
		GameManager manager = GameManager.instance();
		Chess board = new Chess();
		
		manager.createGame(board);
	}
}
