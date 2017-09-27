package v2.org.analysis.system;

public class FilePointer {
	public FilePointer(long numberByte, long address, long moveMethod) {
		super();
		this.numberByte = numberByte;
		this.address = address;
		this.moveMethod = moveMethod;
	}

	private long numberByte, address, moveMethod;

	public long getNumberByte() {
		return numberByte;
	}

	public void setNumberByte(long numberByte) {
		this.numberByte = numberByte;
	}

	public long getAddress() {
		return address;
	}

	public void setAddress(long address) {
		this.address = address;
	}

	public long getMoveMethod() {
		return moveMethod;
	}

	public void setMoveMethod(long moveMethod) {
		this.moveMethod = moveMethod;
	}

	public long getLowOrderAddress() {
		// TODO Auto-generated method stub
		return 1;
	}
}
