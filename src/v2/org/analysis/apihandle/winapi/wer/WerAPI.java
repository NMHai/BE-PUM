package v2.org.analysis.apihandle.winapi.wer;

import v2.org.analysis.apihandle.winapi.API;

public abstract class WerAPI extends API {
	public WerAPI () {
		this.libraryName = "wer.dll";
	}
}