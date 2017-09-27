package v2.org.analysis.apihandle.winapi.sechost;

import v2.org.analysis.apihandle.winapi.API;

public abstract class SechostAPI extends API {
	public SechostAPI () {
		this.libraryName = "sechost.dll";
	}
}