package me.tyler.pizza;

public abstract class FoodFactory
{
	public abstract FoodItem wrapFood(FoodItem food, String type) throws Exception;
}
