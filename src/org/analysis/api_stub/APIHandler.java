package org.analysis.api_stub;

import org.jakstab.Program;

public class APIHandler {

	public static long getModuleHandleA(int i) {
		// TODO Auto-generated method stub
		if (i == 0)
			return 4194304;
		return 0;
	}

	public static long getModuleHandleA(int i, Program program) {
		// TODO Auto-generated method stub
		if (i == 0)
			return program.getImageBase();
		return 0;
	}

	public static long getModuleFileNameA(long hModule, long lpFilename, long nSize, Program program) {
		// TODO Auto-generated method stub
		return nSize;
	}

	public static long getProcAddress(long value, long value2, Program program) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static long loadLibraryA(long value, Program program) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static long getModuleHandleA(String libraryName, Program program) {
		// TODO Auto-generated method stub
		return 0;
	}

}
