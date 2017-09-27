package v2.org.analysis.packer;

public class PackerRecord {
	
	private static PackerRecord instance = null;
	
	private static String flag 			= "-";
	private String pTechRecord 			= "";
	
	public PackerRecord ()
	{
	}
	
	public String getPackerTechniqueRecord ()
	{
//		return this.pTechRecord.substring(1, this.pTechRecord.length());
		
		if (this.pTechRecord.length() > 1) {
			return this.pTechRecord.substring(1, this.pTechRecord.length());
		}
		
		return "";
	}
	
	public void updatePackerTechniqueRecord (String tech)
	{
		this.pTechRecord += flag + tech;
	}
	
	public static PackerRecord getInstance ()
	{
		if (instance == null)
		{
			instance = new PackerRecord();
		}
		return instance;
	}
	
}
