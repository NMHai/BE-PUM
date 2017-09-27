/**
 * 
 */
package v2.org.analysis.system;

/**
 * @author MinhHai
 * 
 */
public class MapView {
	private long securityAttribute, protectionMapping, highOrder, lowOrder, fileMapNameAddress;
	private String fileMapName;
	private long mapViewHandle;

	public MapView(long securityAttribute, long protectionMapping, long highOrder, long lowOrder, String fileMapName) {
		super();
		this.securityAttribute = securityAttribute;
		this.protectionMapping = protectionMapping;
		this.highOrder = highOrder;
		this.lowOrder = lowOrder;
		this.fileMapName = fileMapName;
	}

	public MapView() {
		// TODO Auto-generated constructor stub
		this(0, 0, 0, 0, "");
	}

	public MapView(long t2, long t3, long t4, long t5, long t6) {
		// TODO Auto-generated constructor stub
		this.securityAttribute = t2;
		this.protectionMapping = t3;
		this.highOrder = t4;
		this.lowOrder = t5;
		this.setFileMapNameAddress(t6);
	}

	public long getSecurityAttribute() {
		return securityAttribute;
	}

	public void setSecurityAttribute(long securityAttribute) {
		this.securityAttribute = securityAttribute;
	}

	public long getProtectionMapping() {
		return protectionMapping;
	}

	public void setProtectionMapping(long protectionMapping) {
		this.protectionMapping = protectionMapping;
	}

	public long getHighOrder() {
		return highOrder;
	}

	public void setHighOrder(long highOrder) {
		this.highOrder = highOrder;
	}

	public long getLowOrder() {
		return lowOrder;
	}

	public void setLowOrder(long lowOrder) {
		this.lowOrder = lowOrder;
	}

	public String getFileMapName() {
		return fileMapName;
	}

	public void setFileMapName(String fileMapName) {
		this.fileMapName = fileMapName;
	}

	/**
	 * @return the mapViewHandle
	 */
	public long getMapViewHandle() {
		return mapViewHandle;
	}

	/**
	 * @param mapViewHandle
	 *            the mapViewHandle to set
	 */
	public void setMapViewHandle(long mapViewHandle) {
		this.mapViewHandle = mapViewHandle;
	}

	/**
	 * @return the fileMapNameAddress
	 */
	public long getFileMapNameAddress() {
		return fileMapNameAddress;
	}

	/**
	 * @param fileMapNameAddress
	 *            the fileMapNameAddress to set
	 */
	public void setFileMapNameAddress(long fileMapNameAddress) {
		this.fileMapNameAddress = fileMapNameAddress;
	}
}
