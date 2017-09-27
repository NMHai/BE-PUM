/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.util
 * File name: TriadEntry.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.util;

/**
 * @author Yen Nguyen
 *
 */
public class TriadEntry<F, S, T> {
	final F first;
	S second;
	T third;

	public TriadEntry(F f, S s, T t) {
		first = f;
		second = s;
		third = t;
	}

	public final F getFirst() {
		return first;
	}

	public final S getSecond() {
		return second;
	}

	public final T getThird() {
		return third;
	}

	public final void setValue(S nS, T nT) {
		second = nS;
		third = nT;
	}

	public final boolean equals(Object o) {
		if (!(o instanceof TriadEntry))
			return false;

		@SuppressWarnings("rawtypes")
		TriadEntry e = (TriadEntry) o;
		Object k1 = getFirst();
		Object k2 = e.getFirst();

		if (k1 == k2 || (k1 != null && k1.equals(k2))) {
			Object s1 = getSecond();
			Object s2 = e.getSecond();
			
			if ((s1 == s2) || (s1 != null && s1.equals(s2))) {
				Object t1 = getThird();
				Object t2 = e.getThird();
				
				if ((t1 == t2) || (t1 != null && t1.equals(s2)))
					return true;
			}
		}
		return false;
	}
}
