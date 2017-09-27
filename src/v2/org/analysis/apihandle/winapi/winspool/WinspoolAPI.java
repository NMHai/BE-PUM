/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool
 * File name: WinspoolAPI.java
 * Created date: Aug 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.winspool;

import v2.org.analysis.apihandle.winapi.API;

/**
 * @author Yen Nguyen
 *
 */
public abstract class WinspoolAPI extends API {
	public WinspoolAPI() {
		this.libraryName = "winspool.drv";
	}
}
