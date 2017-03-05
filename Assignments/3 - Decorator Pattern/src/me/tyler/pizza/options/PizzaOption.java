package me.tyler.pizza.options;

import me.tyler.pizza.FoodItem;

public abstract class PizzaOption extends FoodItem
{
	protected FoodItem foodItem;
	
	public PizzaOption(FoodItem foodItem)
	{
		this.foodItem = foodItem;
	}

	@Override
	public float calculateCost()
	{
		return surcharge + foodItem.calculateCost();
	}

	@Override
	public String generateReceipt()
	{
		return foodItem.generateReceipt() + "\n -" + this.description + " \t(+$" + this.surcharge + ")";
	}
}
