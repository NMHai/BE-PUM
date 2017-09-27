/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet
 * File name: WininetAPI.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet;

import v2.org.analysis.apihandle.winapi.API;

/**
 * @author Yen Nguyen
 *
 */
public abstract class WininetAPI extends API {

	public WininetAPI() {
		this.libraryName = "Wininet.dll";
	}

}
