package me.tyler.ng;

import me.tyler.ng.observer.Observable;

/**
 * 	Sends out new editions of the ever-so-popular 
 *  "National Geomorphology" Magazine to all subscribers!
 * */
public class MailingList extends Observable<String>
{
	public static void main(String[] args)
	{
		MailingList ng = new MailingList();
	}
	
	public MailingList(){}

}
