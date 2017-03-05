package me.tyler.pizza.options;

import java.lang.reflect.Constructor;

import me.tyler.pizza.FoodFactory;
import me.tyler.pizza.FoodItem;

public class ServiceFactory extends FoodFactory
{
	@Override
	public FoodItem wrapFood(FoodItem food, String type) throws Exception 
	{
		Class<?> condimentClass = Class.forName(fullyQualifiedName + "$" + type);
		if(condimentClass.isAnnotationPresent(NestedType.class))
		{
			if(condimentClass.getDeclaredAnnotation(NestedType.class).allowDynamic())
			{
				Constructor<?> condimentConstructor = condimentClass.getConstructors()[0];
				return (FoodItem)condimentConstructor.newInstance(this);
			}
		}
		
		return null;
	}
	
	@NestedType(allowDynamic=true)
	public class CarryOutPizza extends FoodItem
	{
		public CarryOutPizza()
		{
			this.description = "Carry-Out Pizza";
		}

		@Override
		public float calculateCost()
		{
			return surcharge;
		}
	}
	
	@NestedType(allowDynamic=true)
	public class DeliveryPizza extends FoodItem
	{
		public DeliveryPizza()
		{
			this.description = "Delivery Pizza";
			this.surcharge = 5f;
		}

		@Override
		public float calculateCost()
		{
			return surcharge;
		}
	}
}
