package me.tyler.pizza;

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
