package me.tyler.pizza;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;

/**
 * A special factory for allowing dynamic retrieval of nested classes.
 * */
public abstract class FoodFactory
{
	protected final String fullyQualifiedName;
	
	public FoodFactory()
	{
		this.fullyQualifiedName = this.getClass().getName();
	}

	/**
	 * Creates and returns a new FoodItem of {@code type}, or, in the case of decorator factories,
	 * takes the specified FoodItem and "wraps" it in a new FoodItem of {@code type}.
	 * 
	 * @param	food	the FoodItem to wrap (null if factory creates an independent FoodItem)
	 * @param	type	the exact [simple] name of a nested FoodItem class in this FoodFactory
	 * 
	 * @return	the new FoodItem of the specified type, wrapping {@code food}
	 * */
	public FoodItem createFoodItem(FoodItem food, String type) throws Exception 
	{
		Class<?> condimentClass = Class.forName(fullyQualifiedName + "$" + type);
		
		if(condimentClass.isAnnotationPresent(NestedType.class))
		{
			if(condimentClass.getDeclaredAnnotation(NestedType.class).allowDynamic())
			{
				Constructor<?> condimentConstructor = condimentClass.getConstructors()[0];
				
				//Instantiate the new decorator from the current factory, passing in the specified food to its constructor.
				return (FoodItem)condimentConstructor.newInstance(this, food);
			}
		}
		
		return null;
	}
	
	/**
	 * Prints all nested FoodItem classes with @NestedType(allowDynamic=true) from the specified FoodFactory class in an itemized fashion.
	 * */
	public static FoodItem printDynamicFoodItems(Class<? extends FoodFactory> factory)
	{
		char startOption = 'a';
		int optionIndex = 0;
		
		for(Class<?> option : factory.getDeclaredClasses())
		{
			if(option.isAnnotationPresent(NestedType.class))
			{
				if(option.getAnnotation(NestedType.class).allowDynamic())
				{
					System.out.println((char)(startOption + optionIndex) + "." + option.getSimpleName().replaceAll("([A-Z](?=[a-z]))", " $1"));
					optionIndex++;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns the simple name of the nth nested class of the specified FoodFactory child class.
	 * 
	 * @throws ArrayIndexOutOfBoundsException If {@code index} is outside the range {@code [0, factory.getDeclaredClasses().length)}
	 * */
	public static String getDynamicFoodClassName(Class<? extends FoodFactory> factory, int index) throws ArrayIndexOutOfBoundsException
	{
		if(index >= 0 && index < factory.getDeclaredClasses().length)
			return factory.getDeclaredClasses()[index].getSimpleName();
		else
			throw new ArrayIndexOutOfBoundsException(index);
	}

	/**
	 * Signifies a nested class that can allow/restrict itself from being loaded dynamically in a FoodFactory.
	 * */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface NestedType
	{
		boolean allowDynamic() default false;
	}
}
