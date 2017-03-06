package me.tyler.pizza;

public class Pizza extends FoodItem
{
	public Pizza()
	{
		this.description = "Pizza";
	}

	@Override
	public float calculateCost() 
	{
		return this.surcharge;
	}
}
