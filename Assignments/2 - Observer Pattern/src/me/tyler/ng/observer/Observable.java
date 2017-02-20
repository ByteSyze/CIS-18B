package me.tyler.ng.observer;

import java.util.ArrayList;

/**
 * Basic implementation of the IObservable interface.
 * */
public class Observable<T> implements IObservable<T>
{
	private ArrayList<IObserver<T>> observers = new ArrayList<IObserver<T>>();

	@Override
	public void addObserver(IObserver<T> observer) throws Exception
	{
		for(IObserver<T> o : observers)
		{
			//Check if observer is an exact or similar copy of an already registered observer.
			if((o == observer) || o.getUUID().equalsIgnoreCase(observer.getUUID()))
			{
				//Normally, this would throw a class that inherits from Exception, but I'm keeping things "simple".
				throw new Exception("Observer is already observing: " + observer);
			}
		}
		
		//No exception was thrown; this is a unique observer.
		observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver<T> observer) throws Exception
	{
		if(!observers.contains(observer))
		{
			throw new Exception("Observer is not currently observing: " + observer);
		}
		else
		{
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObservers(IObservable<T> observable, T arg)
	{
		for(IObserver<T> observer : observers)
		{
			observer.update(arg);
		}
	}

}
