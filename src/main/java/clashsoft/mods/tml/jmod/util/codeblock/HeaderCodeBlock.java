package clashsoft.mods.tml.jmod.util.codeblock;

import java.util.ArrayList;
import java.util.List;

import clashsoft.mods.tml.jmod.util.CodeLine;
import clashsoft.mods.tml.jmod.util.codeblocktypes.CodeBlockType;
import clashsoft.mods.tml.jmod.util.exceptions.SyntaxException;

public class HeaderCodeBlock extends CodeBlock implements Breakable
{
	public CodeLine	executionLine;
	
	public HeaderCodeBlock(CodeLine header, CodeBlock superBlock, List<String> lines)
	{
		super(superBlock, lines);
		this.executionLine = header;
	}
	
	public HeaderCodeBlock(CodeLine header, CodeBlock superBlock)
	{
		this(header, superBlock, new ArrayList<String>());
	}
	
	public CodeBlockType getCodeBlockType() throws SyntaxException
	{
		return CodeBlockType.getCodeBlockType(this, this.executionLine.line);
	}
	
	private final class TempCodeBlock extends CodeBlock
	{
		public TempCodeBlock(CodeBlock superBlock, List<String> lines)
		{
			super(superBlock, lines);
		}
		
		@Override
		public void setSuperCodeBlock(CodeBlock superCodeBlock)
		{
			HeaderCodeBlock.this.setSuperCodeBlock(superCodeBlock);
		}
	}
	
	@Override
	public Object execute()
	{
		CodeBlockType cbt;
		try
		{
			cbt = this.getCodeBlockType();
			cbt.setup(this, this.executionLine);
			return cbt.execute(new TempCodeBlock(this.superCodeBlock, this.lines));
		}
		catch (SyntaxException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}
