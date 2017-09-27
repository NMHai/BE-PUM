package v2.org.analysis.comparison;

public class Edge {
	private Vertex source, dest;

	public boolean equals(Edge t) {
		return source.equals(t.getSource()) && dest.equals(t.getDest());
	}

	public Edge() {
		source = new Vertex();
		dest = new Vertex();
	}

	public Edge(Vertex s, Vertex d) {
		this.source = s;
		this.dest = d;
	}

	/**
	 * @return the source
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * @return the dest
	 */
	public Vertex getDest() {
		return dest;
	}

	/**
	 * @param dest
	 *            the dest to set
	 */
	public void setDest(Vertex dest) {
		this.dest = dest;
	}

	public String toString() {
		return source.getAddress() + ": " + source.getInstructionString() + " --> " + dest.getAddress() + ": "
				+ dest.getInstructionString();
	}
}