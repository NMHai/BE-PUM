package v2.org.analysis.comparison;

import java.util.ArrayList;
import java.util.List;

public class CFG {
	List<Vertex> vertices = new ArrayList<Vertex>();
	List<Edge> edges = new ArrayList<Edge>();
	private Vertex startPoint = null;

	public void insertVertex(Vertex t) {
		// TODO Auto-generated method stub
		if (!contain(t))
			vertices.add(t);
	}

	public void insertEdge(Edge t) {
		// TODO Auto-generated method stub
		if (!contain(t))
			edges.add(t);
	}

	public boolean contain(Vertex t) {
		for (Vertex temp : vertices)
			if (temp.equals(t))
				return true;
		return false;
	}

	public boolean contain(Edge t) {
		for (Edge temp : edges)
			if (temp.equals(t))
				return true;
		return false;
	}

	@Override
	public String toString() {
		String r = "";
		for (Vertex t : vertices)
			r += t.toString() + ", ";

		return r;
	}

	public List<Vertex> getVertecesList() {
		// TODO Auto-generated method stub
		return vertices;
	}

	public List<Edge> getEdgesList() {
		// TODO Auto-generated method stub
		return edges;
	}

	public Vertex getVertex(String id) {
		// TODO Auto-generated method stub
		for (Vertex v : vertices) {
			if (v.getID().equals(id))
				return v;
		}

		Vertex t = new Vertex();
		t.setID(id);
		vertices.add(t);
		return t;
	}

	/**
	 * @return the startPoint
	 */
	public Vertex getStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoint
	 *            the startPoint to set
	 */
	public void setStartPoint(Vertex startPoint) {
		this.startPoint = startPoint;
	}

	public void insert(String line) {
		// TODO Auto-generated method stub

	}
}
