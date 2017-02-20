package me.tyler.ng.observer;

/**
 * Interface for notifying a list of 
 * */
public interface IObservable<T> 
{
	/**
	 * Adds observer to this Observable interface.
	 * 
	 * Throws an exception if the observer is already observing.
	 * */
	public void addObserver(IObserver<T> observer) throws Exception;

	/**
	 * Removes observer from this Observable interface.
	 * 
	 * Throws an exception if the observer is not currently observing.
	 * */
	public void removeObserver(IObserver<T> observer) throws Exception;
	
	public void notifyObservers(IObservable<T> observable, T arg);
}
