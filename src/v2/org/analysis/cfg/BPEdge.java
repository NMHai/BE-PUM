package v2.org.analysis.cfg;

public class BPEdge {
	private BPVertex src, dest;

	/**
	 * @param src
	 * @param dest
	 */
	public BPEdge(BPVertex src, BPVertex dest) {
		super();
		this.src = src;
		this.dest = dest;
	}

	public BPEdge() {
		// TODO Auto-generated constructor stub
		src = null;
		dest = null;
	}

	/**
	 * @return the src
	 */
	public BPVertex getSourceVertex() {
		return src;
	}

	/**
	 * @param src
	 *            the src to set
	 */
	public void setSourceVertex(BPVertex src) {
		this.src = src;
	}

	/**
	 * @return the dest
	 */
	public BPVertex getDestVertex() {
		return dest;
	}

	/**
	 * @param dest
	 *            the dest to set
	 */
	public void setDestVertex(BPVertex dest) {
		this.dest = dest;
	}

	public boolean equal(BPEdge v) {
		if (v == null || v.getSourceVertex() == null || v.getDestVertex() == null || src == null || dest == null) {
			return false;
		}
		
		return src.equal(v.getSourceVertex()) && dest.equal(v.getDestVertex());
	}

	@Override
	public String toString() {
		return src.toString() + " --> " + dest.toString();
	}
}
