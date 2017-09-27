package v2.org.analysis.apihandle.winapi.powrprof;

import v2.org.analysis.apihandle.winapi.API;

public abstract class PowrprofAPI extends API {
	public PowrprofAPI () {
		this.libraryName = "powrprof.dll";
	}
}