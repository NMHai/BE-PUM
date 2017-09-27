package v2.org.analysis.apihandle.winapi.loadperf;

import v2.org.analysis.apihandle.winapi.API;

public abstract class LoadperfAPI extends API {
	public LoadperfAPI () {
		this.libraryName = "loadperf.dll";
	}
}