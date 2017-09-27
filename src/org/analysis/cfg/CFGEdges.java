package org.analysis.cfg;

import java.util.ArrayList;
import java.util.List;

public class CFGEdges {
	private List<CFGEdge> edges;

	public CFGEdges() {
		edges = new ArrayList<CFGEdge>();
	}

	public boolean checkContained(CFGEdge edge) {
		if (edge == null)
			return false;
		for (CFGEdge e : edges)
			if (e.getSource().equal(edge.getSource()) && e.getDestination().equal(edge.getDestination()))
				// && e.getCond().equal(edge.getCond()))
				return true;
		return false;
	}

	public boolean add(CFGEdge edge) {
		if (edge == null)
			return false;
		if (checkContained(edge))
			return false;
		edges.add(edge);
		return true;
	}

	public void printInfo() {
		System.out.println("Information of Edges:");
		System.out.println("Number of Edges:" + this.edges.size());
		for (CFGEdge edge : edges)
			edge.printInfo();
	}
}
