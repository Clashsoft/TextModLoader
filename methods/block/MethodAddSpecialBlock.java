package com.chaosdev.textmodloader.methods.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import clashsoft.clashsoftapi.ItemCustomBlock;
import clashsoft.clashsoftapi.metatools.ItemMetaAxe;
import clashsoft.clashsoftapi.metatools.ItemMetaHoe;
import clashsoft.clashsoftapi.metatools.ItemMetaPickaxe;
import clashsoft.clashsoftapi.metatools.ItemMetaSpade;
import clashsoft.clashsoftapi.metatools.ItemMetaSword;
import clashsoft.clashsoftapi.specialblocks.BlockSpecialSlab;
import clashsoft.clashsoftapi.specialblocks.BlockSpecialWorkbench;

import com.chaosdev.textmodloader.methods.MethodExecuter;
import com.chaosdev.textmodloader.util.TextModHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MethodAddSpecialBlock extends MethodExecuter
{
	@Override
	public Object execute(Object... parameters)
	{
		Block block = null;
		String name = "";
		if (matches(parameters, String.class, Integer.class))
		{
			String type = (String) parameters[0];
			int blockID = (Integer) parameters[1];

			if (type.equals("workbench") && matches(parameters, String.class, Integer.class, String[].class, String[].class, String[].class, String[].class, String[].class))
			{
				String[] names = (String[]) parameters[2];
				name = names[0];
				String[] topIcons = (String[]) parameters[3];
				String[] sideIcons = (String[]) parameters[4];
				String[] side2Icons = (String[]) parameters[5];
				String[] bottomIcons = (String[]) parameters[6];
				block = new BlockSpecialWorkbench(blockID, names, topIcons, sideIcons, side2Icons, bottomIcons);
				GameRegistry.registerBlock(block, ItemCustomBlock.class, name.toUpperCase().replace(" ", ""));
				((BlockSpecialWorkbench)block).addNames();
			}
			if (type.equals("slab") && matches(parameters, String.class, Integer.class, String[].class, String[].class, String[].class, Integer.class, Boolean.class))
			{
				String[] names = (String[]) parameters[2];
				name = names[0];
				String[] topIcons = (String[]) parameters[3];
				String[] sideIcons = (String[]) parameters[4];
				int singleSlabID = (Integer) parameters[5];
				boolean doubleSlab = (Boolean) parameters[6];
				
				block = new BlockSpecialSlab(blockID, names, topIcons, sideIcons, singleSlabID, doubleSlab);
				GameRegistry.registerBlock(block, ItemCustomBlock.class, name.toUpperCase().replace(" ", ""));
				((BlockSpecialSlab)block).addNames();
			}
		}
		if (block != null)
		{
			System.out.println("  Special block added.");
			return block.blockID;
		}
		return -1;
	}

	@Override
	public String getName()
	{
		return "addspecialblock";
	}

	@Override
	public String getUsage()
	{
		return ">addSpecialBlock(\"[blocktype]\", [id]i, [parameters]...)";
	}
}