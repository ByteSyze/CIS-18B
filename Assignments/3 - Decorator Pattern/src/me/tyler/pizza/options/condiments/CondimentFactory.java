package me.tyler.pizza.options.condiments;

import java.lang.reflect.Constructor;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;
import me.tyler.pizza.PizzaOption;

public class CondimentFactory extends FoodFactory
{
	@Override
	public FoodItem wrapFood(FoodItem food, String condiment) throws Exception 
	{
		Class<?> condimentClass = Class.forName(fullyQualifiedName + "$" + condiment);
		if(condimentClass.isAnnotationPresent(DynamicOption.class))
		{
			if(condimentClass.getDeclaredAnnotation(DynamicOption.class).allowDynamic())
			{
				Constructor<?> condimentConstructor = condimentClass.getConstructors()[0];
				return (FoodItem)condimentConstructor.newInstance(this, food);
			}
		}
		
		return null;
	}
	
	@DynamicOption(allowDynamic=true)
	class Cheese extends PizzaOption
	{
		public Cheese(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 0f;
			description = "Cheese";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	class Chicken extends PizzaOption
	{
		public Chicken(FoodItem foodItem)
		{
			super(foodItem);
			
			surcharge = 1.5f;
			description = "Chicken";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	class Olives extends PizzaOption
	{
		public Olives(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Olives";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	class Pepperoni extends PizzaOption
	{
		public Pepperoni(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Pepperoni";
		}
	}
	
	@DynamicOption(allowDynamic=true)
	class Peppers extends PizzaOption
	{
		public Peppers(FoodItem foodItem) 
		{
			super(foodItem);
			
			surcharge = .2f;
			description = "Pepperoni";
		}
	}
	
	@DynamicOption(allowDynamic=true)
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
