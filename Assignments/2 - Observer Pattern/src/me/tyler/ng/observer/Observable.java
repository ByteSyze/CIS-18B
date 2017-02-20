package me.tyler.ng.observer;

/**
 * Generic interface for notifying a list of observers.
 * 
 * The type T specifies the type of argument observers should expect.
 * */
public interface Observable<T> 
{
	/**
	 * Adds observer to this Observable interface.
	 * 
	 * Throws an exception if the observer is already observing.
	 * */
	public void addObserver(Observer<T> observer) throws Exception;

	/**
	 * Removes observer from this Observable interface.
	 * 
	 * Throws an exception if the observer is not currently observing.
	 * */
	public void removeObserver(Observer<T> observer) throws Exception;
	
	/**
	 * Sends an argument to all registered observers.
	 * */
	public void notifyObservers(T arg);
}
