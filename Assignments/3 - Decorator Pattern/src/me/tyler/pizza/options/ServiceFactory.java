package me.tyler.pizza.options;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;

/**
 * Contains concrete classes that directly inherit from FoodItem. All other factories can be used to decorate these classes.
 * */
public class ServiceFactory extends FoodFactory
{
	@NestedType(allowDynamic=true)
	public class CarryOut extends PizzaOption
	{
		public CarryOut(FoodItem food)
		{
			super(food);
			
			this.description = "Carry-Out";
		}
	}
	
	@NestedType(allowDynamic=true)
	public class Delivery extends PizzaOption
	{
		public Delivery(FoodItem food)
		{
			super(food);
			
			this.description = "Delivery";
			this.surcharge = 5f;
		}
	}
}
