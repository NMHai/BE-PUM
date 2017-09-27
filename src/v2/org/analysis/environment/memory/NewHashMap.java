package v2.org.analysis.environment.memory;

import java.util.HashMap;

public class NewHashMap<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public V put(K x, V y) {
		/*
		 * if (x instanceof X86MemoryOperand) { if (((X86MemoryOperand)
		 * x).getDisplacement() == 1245108) System.out.println("Debug"); }
		 */
		if (y != null)
			super.put(x, y);

		return y;
	}
}
