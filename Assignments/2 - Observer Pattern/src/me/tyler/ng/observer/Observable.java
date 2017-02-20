package me.tyler.ng.observer;

import java.util.ArrayList;

public class Observable<T> implements IObservable<T>
{
	public ArrayList<IObserver<T>> observers = new ArrayList<IObserver<T>>();

	@Override
	public void addObserver(IObserver<T> observer) throws Exception
	{
		if(observers.contains(observer))
		{
			throw new Exception("Observer is already observing: " + observer);
		}
		else
		{
			observers.add(observer);
		}
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
