package me.tyler.pizza;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;

/**
 * A special factory for allowing dynamic retrieval of nested classes via Reflection.
 * */
public abstract class FoodFactory
{
	protected final String fullyQualifiedName;
	
	public FoodFactory()
	{
		this.fullyQualifiedName = this.getClass().getName();
	}

	/**
	 * Wraps the specified FoodItem in another FoodItem class declared within this FoodFactory.
	 * 
	 * @param	food	the FoodItem to wrap
	 * @param	type	the exact [simple] name of a nested FoodItem class in this FoodFactory
	 * 
	 * @return	the new FoodItem of the specified type, wrapping food
	 * */
	public FoodItem wrapFood(FoodItem food, String type) throws Exception 
	{
		Class<?> condimentClass = Class.forName(fullyQualifiedName + "$" + type);
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
	
	/**
	 * Prints all nested FoodItem classes with @DynamicOption(allowDynamic=true) from the specified FoodFactory class in an itemized fashion.
	 * */
	public static FoodItem printDynamicFoodItems(Class<? extends FoodFactory> factory)
	{
		char startOption = 'a';
		int optionIndex = 0;
		
		for(Class<?> option : factory.getDeclaredClasses())
		{
			if(option.isAnnotationPresent(DynamicOption.class))
			{
				if(option.getAnnotation(DynamicOption.class).allowDynamic())
				{
					System.out.println((char)(startOption + optionIndex) + ". " + option.getSimpleName());
					optionIndex++;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the simple name of the nth nested class of the specified FoodFactory child class.
	 * 
	 * @throws ArrayIndexOutOfBoundsException If index is outside the range [0, factory.getDeclaredClasses().length)
	 * */
	public static String getDynamicFoodClassName(Class<? extends FoodFactory> factory, int index) throws ArrayIndexOutOfBoundsException
	{
		if(index >= 0 && index < factory.getDeclaredClasses().length)
			return factory.getDeclaredClasses()[index].getSimpleName();
		else
			throw new ArrayIndexOutOfBoundsException(index);
	}

	/**
	 * Signifies a class that can allow/restrict itself from being loaded dynamically in a FoodFactory.
	 * */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface DynamicOption
	{
		boolean allowDynamic() default false;
	}
}
