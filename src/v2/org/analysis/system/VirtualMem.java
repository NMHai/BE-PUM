/**
 * 
 */
package v2.org.analysis.system;

/**
 * @author MinhHai
 * 
 */
public class VirtualMem {
	private long address, size, typeAllo, typeProtec;
	private long baseAddress;

	public VirtualMem(long address, long size, long typeAllo, long typeProtec) {
		super();
		this.address = address;
		this.size = size;
		this.typeAllo = typeAllo;
		this.typeProtec = typeProtec;
	}

	// PHONG: change here
	public VirtualMem(long baseaddress, long address, long size, long typeAllo, long typeProtec) {
		super();
		this.address = address;
		this.size = size;
		this.typeAllo = typeAllo;
		this.typeProtec = typeProtec;
		this.baseAddress = baseaddress;
	}

	/**
	 * @return the address
	 */
	public long getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(long address) {
		this.address = address;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the typeAllo
	 */
	public long getTypeAllo() {
		return typeAllo;
	}

	/**
	 * @param typeAllo
	 *            the typeAllo to set
	 */
	public void setTypeAllo(long typeAllo) {
		this.typeAllo = typeAllo;
	}

	/**
	 * @return the typeProtec
	 */
	public long getTypeProtection() {
		return typeProtec;
	}

	/**
	 * @param typeProtec
	 *            the typeProtec to set
	 */
	public void setTypeProtection(long typeProtec) {
		this.typeProtec = typeProtec;
	}

	/**
	 * @return the baseAddress
	 */
	public long getBaseAddress() {
		return baseAddress;
	}

	/**
	 * @param baseAddress
	 *            the baseAddress to set
	 */
	public void setBaseAddress(long baseAddress) {
		this.baseAddress = baseAddress;
	}

	public long free(long size2, long type) {
		// TODO Auto-generated method stub
		return 1;
	}
}
