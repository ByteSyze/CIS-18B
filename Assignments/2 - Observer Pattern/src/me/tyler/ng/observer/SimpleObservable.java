package me.tyler.ng.observer;

import java.util.ArrayList;

/**
 * Basic implementation of the Observable interface.
 * */
public class SimpleObservable<T> implements Observable<T>
{
	private ArrayList<Observer<T>> observers = new ArrayList<Observer<T>>();

	@Override
	public void addObserver(Observer<T> observer) throws Exception
	{
		for(Observer<T> o : observers)
		{
			//Check if observer is an exact or similar copy of an already registered observer.
			if((o == observer) || o.getUID().equalsIgnoreCase(observer.getUID()))
			{
				//Normally, this would throw a class that inherits from Exception, but I'm keeping things "simple".
				throw new Exception("Observer is already observing: " + observer);
			}
		}
		
		//No exception was thrown; this is a unique observer.
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer<T> observer) throws Exception
	{
		boolean removed = false;
		
		for(Observer<T> o : observers)
		{
			//Check if observer is an exact or similar copy of an already registered observer.
			if((o == observer) || o.getUID().equalsIgnoreCase(observer.getUID()))
			{
				observers.remove(o);
				removed = true;
				
				break;
			}
		}
		
		if(!removed)
		{
			throw new Exception("Observer is not currently observing: " + observer);
		}
	}

	@Override
	public void notifyObservers(T arg)
	{
		for(Observer<T> observer : observers)
		{
			observer.update(this, arg);
		}
	}

}
