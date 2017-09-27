package org.analysis.api_stub;

import org.jakstab.Program;

public class JNA {
	Program program;

	public static long getModuleHandleA(int i) {
		// TODO Auto-generated method stub
		if (i == 0)
			return 4194304;
		return 0;
	}

	public JNA(Program p) {
		this.program = p;
	}

	public long getBaseCodeAddress() {
		long ret = 0;
		// 4194304
		return ret;
	}

}
