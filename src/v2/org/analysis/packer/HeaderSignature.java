package v2.org.analysis.packer;

public class HeaderSignature {

	private String packerName;
	private String packerVersion;
	private boolean isEntryPoint;
	private String[] packerSignature;
	
	public HeaderSignature (String pName, String pVer, boolean isEntry, String[] pSignature)
	{
		packerName 		= pName;
		packerVersion 	= pVer; 
		isEntryPoint	= isEntry;
		packerSignature = pSignature;
	}	
	
	public String getPackerName ()
	{
		return packerName;
	}
	
	public String getPackerVersion ()
	{
		return packerVersion;
	}
	
	public boolean isEntryPoint ()
	{
		return isEntryPoint;
	}
	
	public String[] getPackerSignature ()
	{
		return packerSignature;
	}
}
