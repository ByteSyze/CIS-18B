package me.tyler.ng.observer;

import java.util.ArrayList;

public class Observable<T> implements IObservable<T>
{
	public ArrayList<IObserver<T>> observers = new ArrayList<IObserver<T>>();

	@Override
	public void addObserver(IObserver<T> observer) throws Exception
	{
		if(observers.contains(observer))
			throw new Exception();
	}

	@Override
	public void removeObserver(IObserver<T> observer)
	{
		
	}

	@Override
	public void notifyObservers(IObservable<T> observable, T arg)
	{
		
	}

}
