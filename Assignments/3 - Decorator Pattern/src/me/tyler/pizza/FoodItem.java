package me.tyler.pizza;

/**
 * An abstract food item, containing a description of the item and the base cost associated with it.
 * */
public abstract class FoodItem 
{
	protected String description = "Undefined Food Item";
	protected float  surcharge;
	
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Generates and returns a receipt for this FoodItem.
	 * */
	public String generateReceipt()
	{
		return "--"+this.description+"--";
	}
	
	/**
	 * Calculates the total price of this FoodItem.
	 * */
	public abstract float calculateCost();

}
