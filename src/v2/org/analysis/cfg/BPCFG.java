package v2.org.analysis.cfg;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

public class BPCFG {
	private List<BPVertex> verteces;
	private List<BPEdge> edges;

	// private boolean isCompleted = true;

	/**
	 * @param vertex
	 * @param edge
	 */
	public BPCFG() {
		super();
		this.verteces = new CopyOnWriteArrayList<BPVertex>();
		this.edges = new CopyOnWriteArrayList<BPEdge>();
	}

	public List<BPVertex> getVertecesList() {
		return verteces;
	}

	public List<BPEdge> getEdgesList() {
		return edges;
	}

	/**
	 * @return the vertex
	 */
	public BPVertex getVertex(int index) {
		return verteces.get(index);
	}

	/**
	 * @param vertex
	 *            the vertex to set
	 * @return
	 */
	public BPVertex insertVertex(BPVertex vertex) {
		for (BPVertex v : verteces) {
			if (v.equal(vertex)) {
				return v;
			}
		}

		this.verteces.add(vertex);
		return vertex;
	}

	public boolean containVertex(BPVertex vertex) {
		// TODO Auto-generated method stub
		for (BPVertex v : verteces) {
			if (v.equal(vertex)) {
				return true;
			}
		}

		return false;
	}

	private boolean containEdge(BPEdge edge) {
		// TODO Auto-generated method stub
		for (BPEdge e : edges) {
			if (e.equal(edge)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return the edge
	 */
	public BPEdge getEdge(int i) {
		return edges.get(i);
	}

	/**
	 * @param edge
	 *            the edge to set
	 */
	public void insertEdge(BPEdge edge) {
		if (!containEdge(edge)) {
			this.edges.add(edge);
		}
	}

	public BPVertex getVertex(AbsoluteAddress location, Instruction instruction) {
		// TODO Auto-generated method stub
		if (location == null && instruction == null) {
			return null;
		}

		for (BPVertex v : verteces) {
			if (v.getAddress() != null && v.getAddress().getValue() == location.getValue()
					&& v.getInstruction().compareInstruction(instruction)) {
				return v;
			}
		}

		return null;
	}

	public Object getInstructionCount() {
		// TODO Auto-generated method stub
		int l = 0;
		for (BPVertex v : verteces) {
			if (v.getType() != 3) {
				l++;
			}
		}

		return l;
	}

	public Object getVertexCount() {
		// TODO Auto-generated method stub
		return verteces.size();
	}

	public Object getEdgeCount() {
		// TODO Auto-generated method stub
		return edges.size();
	}

//	public boolean isCompleted() {
//		// TODO Auto-generated method stub
//		for (BPVertex v : verteces) {
//			if (v.getType() == BPVertex.UnknownNode) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	public boolean isCompleted() {
		// TODO Auto-generated method stub
		for (BPVertex v : verteces) {
			if (v.getType() == BPVertex.ExitNode) {
				return true;
			}
		}
		return false;
	}

	public BPVertex getEntryPoint() {
		// TODO Auto-generated method stub
		for (BPVertex s : verteces) {
			if (s.getType() == 0) {
				return s;
			}
		}

		return null;
	}

	public void removeVertex(int i) {
		// TODO Auto-generated method stub
		verteces.remove(i);
	}

	public boolean containVertex(long value) {
		// TODO Auto-generated method stub
		for (BPVertex v : verteces) {
			if (v.getAddress().getValue() == value) {
				return true;
			}
		}

		return false;
	}
}
