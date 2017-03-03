package me.tyler.pizza.options.condiments;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class CondimentFactory extends FoodFactory
{
	@Override
	public FoodItem wrapFood(FoodItem food, String condiment) throws Exception 
	{
		switch(condiment.toLowerCase())
		{
			case "alfredo":
				return new Alfredo(food);
			case "cheese":
				return new Cheese(food);
			case "chicken":
				return new Chicken(food);
			case "olives":
				return new Olives(food);
			case "pepperoni":
				return new Pepperoni(food);
			case "sausage":
				return new Sausage(food);
			default:
				throw new RuntimeException("Unknown condiment: " + condiment);
		}
	}
	
	class Alfredo extends PizzaOption 
	{
		public Alfredo(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 1f;
			description = "Alfredo";
		}
	}

	class Cheese extends PizzaOption
	{
		public Cheese(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 0f;
			description = "Cheese";
		}
	}
	
	class Chicken extends PizzaOption
	{
		public Chicken(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 1.5f;
			description = "Chicken";
		}
	}

	class Olives extends PizzaOption
	{
		public Olives(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Olives";
		}
	}
	
	class Pepperoni extends PizzaOption
	{
		public Pepperoni(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Pepperoni";
		}
	}
	
	class Sausage extends PizzaOption 
	{
		public Sausage(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = .5f;
			description = "Sausage";
		}
	}
}
