package v2.org.analysis.environment.processthread;

public class UnicodeString {

	private long length;
	private long maxLength;
	private long pString;
	
	public UnicodeString(long len, long mLen, long pString)
	{
		this.length 	= len;
		this.maxLength 	= mLen;
		this.pString	= pString;
	}
	
	public long getLength ()
	{
		return this.length;
	}
	
	public long getMaxLength ()
	{
		return this.maxLength;
	}
	
	public long getPointerStr ()
	{
		return this.pString;
	}
}
