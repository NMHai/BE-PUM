package org.analysis.cfg;

import org.jakstab.asm.AbsoluteAddress;

import java.util.ArrayList;
import java.util.List;

public class CFGVertices {
	private List<CFGVertex> vertices;

	public CFGVertices() {
		vertices = new ArrayList<CFGVertex>();
	}

	public boolean remove(CFGVertex vertex) {
		int i = 0;
		for (CFGVertex v : vertices) {
			if (v.getAddr().getValue() == vertex.getAddr().getValue()) {
				vertices.remove(i);
				return true;
			}
			i++;
		}
		return false;
	}

	public boolean contain(CFGVertex vertex) {
		if (vertex == null)
			return false;
		for (CFGVertex v : vertices)
			if (v.getAddr().getValue() == vertex.getAddr().getValue())
				return true;
		return false;
	}

	public boolean contain(AbsoluteAddress addr) {
		if (addr == null)
			return false;
		for (CFGVertex v : vertices)
			if (v.getAddr().getValue() == addr.getValue())
				return true;
		return false;
	}

	public boolean add(CFGVertex vertex) {
		if (contain(vertex))
			return false;
		this.vertices.add(vertex);
		return true;
	}

	public CFGVertex getVertex(AbsoluteAddress addr) {
		for (CFGVertex vertex : vertices)
			if (vertex.getAddr().getValue() == addr.getValue())
				return vertex;
		return null;
	}

	public void printInfo() {
		System.out.println("Information of Vertices:");
		System.out.println("Number of vertices:" + this.vertices.size());
		for (CFGVertex vertex : vertices)
			vertex.printInfo();
	}

	public List<CFGVertex> getListCFGVertex() {
		return vertices;
	}
}
