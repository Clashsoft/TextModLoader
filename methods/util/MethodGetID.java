package com.chaosdev.textmodloader.methods.util;

import com.chaosdev.textmodloader.methods.MethodExecutor;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class MethodGetID extends MethodExecutor
{
	@Override
	public Object execute(Object... parameters)
	{
		String name = (String) parameters[0];
		for (Block b : Block.blocksList)
		{
			if (b != null)
				if (b.getUnlocalizedName().toLowerCase().trim().equals("tile." + name.toLowerCase().trim()))
					return b.blockID;
		}
		for (Item i : Item.itemsList)
		{
			if (i != null)
				if (i.getUnlocalizedName().toLowerCase().trim().equals("item." + name.toLowerCase().trim()))
					return i.itemID;
		}
		return -1;
	}

	@Override
	public String getName()
	{
		return "getID|getId";
	}

	@Override
	public String getUsage()
	{
		return "getID(\"[block]\") OR getID(\"[item]\")";
	}

}
