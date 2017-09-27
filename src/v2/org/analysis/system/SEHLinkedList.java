package v2.org.analysis.system;

public class SEHLinkedList {
	private long nextSEHRecord = 0;
	private long addrSEHRecord = 0;
	private long sehHandler = 0;
	private long addrSEHHandler = 0;
	private String nameFunction;

	public long getSehHandler() {
		return sehHandler;
	}

	public void setSEHHandler(long sehHandler) {
		this.sehHandler = sehHandler;
	}

	public long getNextSEHRecord() {
		return nextSEHRecord;
	}

	public void setNextSEHRecord(long nextSEHRecord) {
		this.nextSEHRecord = nextSEHRecord;
	}

	public SEHLinkedList(long addrSEHRecord, long nextSEHRecord, long sehHandler){
		super();
		this.addrSEHRecord = addrSEHRecord;
		this.nextSEHRecord = nextSEHRecord;
		this.sehHandler = sehHandler;
	}

	public SEHLinkedList(long nextSEHRecord, long sehHandler) {
		super();
		this.nextSEHRecord = nextSEHRecord;
		this.sehHandler = sehHandler;
	}

	public SEHLinkedList(long nextSEHRecord, long sehHandler, String nameFunction) {
		super();
		this.nextSEHRecord = nextSEHRecord;
		this.sehHandler = sehHandler;
		this.nameFunction = nameFunction;
	}

	public SEHLinkedList() {
		super();
	}

	public String getNameFunction() {
		return nameFunction;
	}

	public void setNameFunction(String nameFunction) {
		this.nameFunction = nameFunction;
	}

	public void setNextSEHRecord(long value, long l) {
		// TODO Auto-generated method stub
		this.addrSEHRecord = l;
		this.nextSEHRecord = value;
	}

	public void setSEHHandler(long value, long l) {
		// TODO Auto-generated method stub
		this.addrSEHHandler = l;
		this.sehHandler = value;
	}

	public long getAddrSEHRecord() {
		return addrSEHRecord;
	}

	public void setAddrSEHRecord(long addrSEHRecord) {
		this.addrSEHRecord = addrSEHRecord;
	}

	public long getAddrSEHHandler() {
		return addrSEHHandler;
	}

	public void setAddrSEHHandler(long addrSEHHandler) {
		this.addrSEHHandler = addrSEHHandler;
	}
}
