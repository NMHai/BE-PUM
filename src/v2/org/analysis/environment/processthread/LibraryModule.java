package v2.org.analysis.environment.processthread;

public class LibraryModule {

	private long handle;
	private long entry_point;
	private byte[] f_name;
	private byte[] b_name;
	
	public LibraryModule(long handle, long entry_point, String f_name, String b_name)
	{
		this.handle 		= handle;
		this.entry_point 	= entry_point;
		this.f_name 		= f_name.getBytes();
		this.b_name			= b_name.getBytes();
	}
	
	public long getHandle()
	{
		return this.handle;
	}
	
	public long getEntryPoint()
	{
		return this.entry_point;
	}
	
	public byte[] getFullName ()
	{
		return this.f_name;
	}
	
	public byte[] getBaseName ()
	{
		return this.b_name;
	}
}
