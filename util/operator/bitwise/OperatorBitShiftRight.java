package com.chaosdev.textmodloader.util.operator.bitwise;

public class OperatorBitShiftRight extends OperatorBitwise
{
	public OperatorBitShiftRight(String operator)
	{
		super(operator);
	}

	@Override
	public <T, U> Object operate(T par1, U par2)
	{
		if (par1 instanceof Number && par2 instanceof Number)
			return ((Number)par1).intValue() >> ((Number)par2).intValue();
		return par1;
	}
}
