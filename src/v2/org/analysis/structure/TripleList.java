package v2.org.analysis.structure;

import java.util.ArrayList;
import java.util.List;

public class TripleList <K, V, T>{
	private List<TripleData <K, V, T>> list; 
	
	public TripleList() {
		list = new ArrayList<TripleData <K, V, T>> ();
	}
	
	public void addTriple(K key, V v1, T v2) {
		for (TripleData <K, V, T> t : list) {
			if (t.getKey() == key) {
				t.addValue(v1, v2);
				return;
			}
		}
		TripleData <K, V, T> temp = new TripleData <K, V, T>(key);
		temp.addValue(v1, v2);
		list.add(temp);
	}

	public List<TripleData <K, V, T>> getList() {
		// TODO Auto-generated method stub
		return list;
	}
}
