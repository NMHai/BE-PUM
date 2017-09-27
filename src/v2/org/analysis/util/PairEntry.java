/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.util
 * File name: PairEntry.java
 * Created date: Aug 29, 2015
 * Description:
 */
package v2.org.analysis.util;

/**
 * @author Yen Nguyen
 *
 */
public class PairEntry<K, V> {
	final K key;
	V value;

	public PairEntry(K k, V v) {
		value = v;
		key = k;
	}

	public final K getKey() {
		return key;
	}

	public final V getValue() {
		return value;
	}

	public final V setValue(V newValue) {
		V oldValue = value;
		value = newValue;
		return oldValue;
	}

	public final boolean equals(Object o) {
		if (!(o instanceof PairEntry))
			return false;

		@SuppressWarnings("rawtypes")
		PairEntry e = (PairEntry) o;
		Object k1 = getKey();
		Object k2 = e.getKey();

		if (k1 == k2 || (k1 != null && k1.equals(k2))) {
			Object v1 = getValue();
			Object v2 = e.getValue();
			if (v1 == v2 || (v1 != null && v1.equals(v2)))
				return true;
		}
		return false;
	}
}
