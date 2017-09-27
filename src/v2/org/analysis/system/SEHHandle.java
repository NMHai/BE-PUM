package v2.org.analysis.system;

public class SEHHandle {
	
	public static int READ_WRITE_VIOLATION 	= 1 << 0;
	public static int DIVIDE_BY_ZERO		= 1 << 1;
	public static int INTERUPT				= 1 << 2;
	public static int SINGLE_STEP			= 1 << 3;
	
	private int exception_type;
	
	private SEHLinkedList start;
	private boolean isSet;

	public boolean isSet() {
		return isSet;
	}

	public void setSEHReady(boolean ready) {
		this.isSet = ready;
	}

	public void setSEHType(int type)
	{
		this.exception_type = type;
	}
	
	public SEHHandle() {
		long addrSEH = 0x12FFE0;
		long nextSEH = 0xFFFFFFFF;
		long sehHandler = 0x7C839AA8;
		start = new SEHLinkedList(addrSEH, nextSEH, sehHandler);
		isSet = false;
		exception_type = READ_WRITE_VIOLATION;
	}

	public SEHLinkedList getStart() {
		// this.isSet = true;
		return start;
	}

	public void setStart(SEHLinkedList start) {
		this.start = start;
		this.isSet = true;
	}

	public void removeStart() {
		this.start = null;
		this.isSet = false;
	}
	
	public int typeOf ()
	{
		return this.exception_type;
	}
}
