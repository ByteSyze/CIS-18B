package me.tyler.pizza;

public class CarryOutPizza extends FoodItem
{
	public CarryOutPizza()
	{
		this.description = "Carry-Out Pizza";
	}

	@Override
	public float calculateCost()
	{
		return 0;
	}

	@Override
	public String generateReceipt()
	{
		return " -Carry-out \t(+$" + this.surcharge + ")";
	}
	

}
