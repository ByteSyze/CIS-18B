package me.tyler.ng;

import me.tyler.ng.observer.IObservable;
import me.tyler.ng.observer.IObserver;

/**
 * A patron of National Geomorphology Magazine.
 * */
public class NGSubscriber implements IObserver<String>
{
	private String streetAddress;
	
	public NGSubscriber(String address)
	{
		this.streetAddress = address;
	}

	@Override
	public void update(IObservable<String> observable, String arg)
	{
		System.out.println("Message received: " + arg);
	}

	@Override
	public String getUUID() 
	{
		//Use the subscriber's street address as a unique way of identifying them.
		return streetAddress;
	}
}
