package v2.org.analysis.packer;

public class PackerSavedState {

	private long insLocation;
	private String insName;
	
	public PackerSavedState ()
	{
		this.insLocation 	= 0x0;
		this.insName		= null;
	}
	
	public PackerSavedState (long insLocation, String insName)
	{
		this.insLocation 	= insLocation;
		this.insName 		= insName;
	}	
	
	public long getInsLoc ()
	{
		return this.insLocation;
	}
	
	public String getInsName ()
	{
		return this.insName;
	}
	
	public void setStates (long insLoc, String insName)
	{
		this.insLocation = insLoc;
		this.insName = insName;
	}
	
	public void reset ()
	{
		this.insLocation = 0x0;
		this.insName = null;
	}
	
}
