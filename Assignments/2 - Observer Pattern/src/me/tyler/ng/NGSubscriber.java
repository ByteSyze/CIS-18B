package me.tyler.ng;

import me.tyler.ng.observer.Observable;
import me.tyler.ng.observer.Observer;

/**
 * A patron of National Geomorphic Magazine.
 * */
public class NGSubscriber implements Observer<String>
{
	/**The street address provided as-is.*/
	private String streetAddress;
	
	/**A formatted version of streetaddress for more reliable comparison to other addresses.*/
	private String formattedAddress;
	
	public NGSubscriber(String address)
	{
		this.streetAddress = address;
		
		formattedAddress = streetAddress.replaceAll("([ ,.])", "").toLowerCase();
	}
	
	/**
	 * Returns the street address exactly as it was given to us.
	 * */
	public String getStreetAddress()
	{
		return streetAddress;
	}

	@Override
	public void update(Observable<String> observable, String arg)
	{
		System.out.println("Subscriber at " + streetAddress + " received: " + arg);
	}

	@Override
	public String getUID() 
	{
		//Use the subscriber's street address as a unique way of identifying them.
		return formattedAddress;
	}
}
