package com.chaosdev.textmodloader.methods;

import com.chaosdev.textmodloader.TextMod;
import com.chaosdev.textmodloader.util.TextModHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import clashsoft.clashsoftapi.CustomBlock;
import clashsoft.clashsoftapi.ItemCustomBlock;
import clashsoft.clashsoftapi.util.CSArray;
import clashsoft.clashsoftapi.util.CSUtil;

public class MethodAddBlock implements IMethodExecuter
{

	@Override
	public Object execute(Object... par1)
	{	
		CustomBlock block = null;
		String name = "";
		if (par1.length >= 5 && par1[0] instanceof Integer &&
				par1[1] instanceof String &&
				par1[2] instanceof String &&
				par1[3] instanceof Integer &&
				par1[4] instanceof String)
		{
			int blockID = (Integer) par1[0];
			name = (String) par1[1];
			String icon = (String) par1[2];
			int material = (Integer) par1[3];
			String creativetab = (String) par1[4];
			
			block = (CustomBlock) new CustomBlock(blockID, getMaterial(material), name, icon, getCreativeTab(creativetab)).setStepSound(getStepSound(material));
		}
		else if (par1.length >= 5 &&
				par1[0] instanceof Integer &&
				par1[1] instanceof String[] &&
				par1[2] instanceof String[] &&
				par1[3] instanceof Integer &&
				par1[4] instanceof String[])
		{
			int blockID = (Integer) par1[0];
			String[] names = (String[]) par1[1];
			String[] icons = (String[]) par1[2];
			int material = (Integer) par1[3];
			String[] creativetab = (String[]) par1[4];
			
			block = (CustomBlock) new CustomBlock(blockID, getMaterial(material), names, icons, getCreativeTabs(creativetab)).setStepSound(getStepSound(material));
		}
		if (block != null)
		{
			GameRegistry.registerBlock(block, ItemCustomBlock.class, name);
			block.addNames();
			System.out.println("  Block added.");
			return block.blockID;
		}
		else
		{
			System.out.println("  Failed to add block: Invalid arguments.");
			return -1;
		}
	}
	
	public static Material getMaterial(int id)
	{
		if (id == 0)
			return Material.air;
		if (id == 1)
			return Material.ground;
		if (id == 2)
			return Material.rock;
		if (id == 3)
			return Material.grass;
		if (id == 4)
			return Material.wood;
		if (id == 5)
			return Material.cloth;
		if (id == 6)
			return Material.iron;
		return Material.rock;
	}
	
	public static StepSound getStepSound(int id)
	{
		if (id == 0)
			return Block.soundPowderFootstep;
		if (id == 1)
			return Block.soundGravelFootstep;
		if (id == 2)
			return Block.soundStoneFootstep;
		if (id == 3)
			return Block.soundGrassFootstep;
		if (id == 4)
			return Block.soundWoodFootstep;
		if (id == 5)
			return Block.soundClothFootstep;
		if (id == 6)
			return Block.soundMetalFootstep;
		return Block.soundStoneFootstep;
	}
	
	public static CreativeTabs getCreativeTab(String name)
	{
		for (CreativeTabs ct : CreativeTabs.creativeTabArray)
		{
			if (TextModHelper.changeName(ct.getTabLabel()) == TextModHelper.changeName(name))
				return ct;
		}
		return CreativeTabs.tabBlock;
	}
	
	public static CreativeTabs[] getCreativeTabs(String[] names)
	{
		CreativeTabs[] tabs = new CreativeTabs[names.length];
		for (int i = 0; i < names.length; i++)
		{
			tabs[i] = getCreativeTab(names[i]);
		}
		return tabs;
	}

	@Override
	public String getName()
	{
		return "addblock";
	}
	
	public String getUsage()
	{
		return ">addBlock([blockID]i, \"[blockName]\", \"[iconName]\", [material]i, \"[creativetab]\") OR "
				+ ">addBlock([blockID]i, string{\"blockName1\", ...}, string{\"iconName1\", ...}, [material]i, string{\"creativetab1\", ...})";
	}
}
