package me.tyler.ng.observer;

/**
 * Generic interface for an observer.
 * */
public interface IObserver<T> 
{
	/**
	 * Called by any Observables that this Observer is listening to.
	 * 
	 * @param	observable		The Observable that changed
	 * */
	public void update(IObservable<T> observable, T arg);
	
	/**
	 * Returns a unique identifier for this Observer.
	 * */
	public String getUUID();
}
