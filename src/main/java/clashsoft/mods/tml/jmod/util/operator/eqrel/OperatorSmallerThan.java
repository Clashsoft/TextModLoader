package clashsoft.mods.tml.jmod.util.operator.eqrel;

import clashsoft.mods.tml.jmod.util.operator.math.OperatorMath;

public class OperatorSmallerThan extends OperatorMath
{
	public OperatorSmallerThan(String operator)
	{
		super(operator);
	}
	
	@Override
	public <T, U> Object operate(T par1, U par2)
	{
		if (par1 instanceof Number && par2 instanceof Number)
		{
			return ((Number)par1).doubleValue() < ((Number)par2).doubleValue();
		}
		return false;
	}
}
