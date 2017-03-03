package me.tyler.pizza;

public abstract class FoodItem 
{
	protected String description = "Undefined Food Item";
	protected float  surcharge;
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String generateReceipt()
	{
		return "--"+this.description+"--";
	}
	
	public abstract float calculateCost();

}
