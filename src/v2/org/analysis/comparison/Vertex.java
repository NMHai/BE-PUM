package v2.org.analysis.comparison;

public class Vertex {
	private String id = "";
	private String addr = null;
	private String inst = "";
	private boolean isAPI = false;

	public boolean equals(Vertex t) {
		return id.equals(t.getID()) && addr.equals(t.getAddress()) && inst.equals(t.getInstructionString());
	}

	public Vertex() {
	}

	public Vertex(String a, String inst) {
		this.setAddress(a);
		this.setInstructionString(inst);
	}

	public Vertex(String id, String a, String inst) {
		this.setID(id);
		this.setAddress(a);
		this.setInstructionString(inst);
	}

	/**
	 * @return the addr
	 */
	public String getAddress() {
		return addr;
	}

	/**
	 * @param addr
	 *            the addr to set
	 */
	public void setAddress(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the inst
	 */
	public String getInstructionString() {
		return inst;
	}

	/**
	 * @param inst
	 *            the inst to set
	 */
	public void setInstructionString(String inst) {
		this.inst = inst;
	}

	@Override
	public String toString() {
		return id + ": " + addr + " " + inst;
	}

	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setID(String id) {
		this.id = id;
	}

	/**
	 * @return the isAPI
	 */
	public boolean isAPI() {
		return isAPI;
	}

	/**
	 * @param isAPI
	 *            the isAPI to set
	 */
	public void setAPI(boolean isAPI) {
		this.isAPI = isAPI;
	}
}