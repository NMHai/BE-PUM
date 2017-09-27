package v2.org.analysis.structure;

import java.util.HashMap;
import java.util.Map;

public class TripleData <K, V, T>{
	private K key; 
	private Map<V, T> value;
	
	public TripleData() {
		key = null;
		value = new HashMap<V, T>();
	}
	
	public TripleData(K k) {
		key = k;
		value = new HashMap<V, T>();
	}

	public K getKey() {
		return key;
	}
	
	public void addKey(K key) {
		this.key = key;
	}
	
	public Map<V, T> getValue() {
		return value;
	}
	
	public void setValue(Map<V, T> value) {
		this.value = value;
	} 
	
	public void addValue(V str, T l) {
		value.put(str, l);
	}
	
	
}
