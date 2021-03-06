package clashsoft.mods.tml.mcmod;

import java.io.File;

import clashsoft.cslib.io.CSFiles;
import clashsoft.cslib.src.parser.ParserManager;
import clashsoft.mods.tml.IMod;
import clashsoft.mods.tml.mcmod.ast.ModSection;
import clashsoft.mods.tml.mcmod.parser.MCModParser;

public class MCMod implements IMod
{
	public String		name;
	public String		modid;
	public String		version;
	
	private long		start;
	private long		end;
	
	public ModSection	theSection;
	
	public MCMod()
	{
	}
	
	@Override
	public String getModID()
	{
		return this.modid;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String getVersion()
	{
		return this.version;
	}
	
	@Override
	public boolean load(File file)
	{
		try
		{
			this.start = System.currentTimeMillis();
			
			String fileContent = CSFiles.read(file);
			MCModParser parser = new MCModParser(this);
			ParserManager pm = new ParserManager(parser);
			pm.parse(fileContent);
			
			this.end = System.currentTimeMillis();
			return true;
		}
		catch (Exception ex)
		{
			
		}
		return false;
	}
	
	@Override
	public long getLoadTime()
	{
		return this.end - this.start;
	}
}
