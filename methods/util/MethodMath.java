package com.chaosdev.textmodloader.methods.util;

import clashsoft.clashsoftapi.util.CSUtil;

import com.chaosdev.textmodloader.methods.MethodExecutor;

public class MethodMath extends MethodExecutor
{
	
	@Override
	public Object execute(Object... parameters)
	{
		String type = ((String) parameters[0]).toLowerCase().trim();
		String calculation = (String) parameters[1];
		if (type.equals("integer") || type.equals("int"))
			return (int) CSUtil.calculateFromString(calculation);
		else if (type.equals("float"))
			return (float) CSUtil.calculateFromString(calculation);
		else if (type.equals("double"))
			return (double) CSUtil.calculateFromString(calculation);
		return Double.NaN;
	}
	
	@Override
	public String getName()
	{
		return "math";
	}
	
	@Override
	public String getUsage()
	{
		return "math(\"[type]\", \"[calculation]\")";
	}
	
}
