package v2.org.analysis.environment.processthread;

public class ListEntry {
	
	private long F_link;
	private long B_link;
	
	public ListEntry (long F_link, long B_link)
	{
		this.F_link = F_link;
		this.B_link = B_link;
	}
	
	public long getNext()
	{
		return F_link;
	}
	
	public long getBack ()
	{
		return B_link;
	}
}
