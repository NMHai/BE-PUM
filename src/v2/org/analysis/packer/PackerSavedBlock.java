package v2.org.analysis.packer;

public class PackerSavedBlock {

	private long beginBlockLocation;
	private long endBlockLocation;
	
	public PackerSavedBlock ()
	{
		this.beginBlockLocation 	= 0x0;
		this.endBlockLocation		= 0x0;
	}
	
	public PackerSavedBlock (long beginLoc, long endLoc)
	{
		this.beginBlockLocation 	= beginLoc;
		this.endBlockLocation 		= endLoc;
	}	
	
	public long getBeginBlock ()
	{
		return this.beginBlockLocation;
	}
	
	public long getEndBlock ()
	{
		return this.endBlockLocation;
	}
}
