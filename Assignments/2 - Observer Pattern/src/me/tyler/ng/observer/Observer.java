package me.tyler.ng.observer;

/**
 * Generic interface for an observer.
 * */
public interface Observer<T> 
{
	/**
	 * Called by Observables that this Observer is listening to.
	 * 
	 * @param	observable		The Observable that changed
	 * @param	arg				The new information for the observer
	 * */
	public void update(Observable<T> observable, T arg);
	
	/**
	 * Returns a unique identifier for this Observer.
	 * */
	public String getUID();
}
