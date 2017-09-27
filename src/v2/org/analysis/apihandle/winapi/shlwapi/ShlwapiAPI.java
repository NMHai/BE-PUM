/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shlwapi
 * File name: ShlwapiAPI.java
 * Created date: Aug 31, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shlwapi;

import v2.org.analysis.apihandle.winapi.API;

/**
 * @author Yen Nguyen
 *
 */
public abstract class ShlwapiAPI extends API {

	public ShlwapiAPI() {
		this.libraryName = "Shlwapi.dll";
	}

}
