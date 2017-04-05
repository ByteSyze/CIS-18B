package tic.tac.toe.event;

/**
 * British English is the best English.
 * */
public abstract class CancellableEvent 
{
	private boolean cancel;
	
	public void setCancelled(boolean cancel)
	{
		this.cancel = cancel;
	}
	
	public boolean getCancelled()
	{
		return this.cancel;
	}
}
