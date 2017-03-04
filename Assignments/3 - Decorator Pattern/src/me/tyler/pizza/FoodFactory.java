package me.tyler.pizza;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class FoodFactory
{
	protected final String fullyQualifiedName;
	
	public FoodFactory()
	{
		this.fullyQualifiedName = this.getClass().getName();
	}
	
	public abstract FoodItem wrapFood(FoodItem food, String type) throws Exception;
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface DynamicOption
	{
		boolean allowDynamic() default false;
	}
}
