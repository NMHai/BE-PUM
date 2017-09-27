/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32
 * File name: Kernel32API.java
 * Created date: Jan 30, 2015
 * Decription:
 * 
 */
package v2.org.analysis.apihandle.winapi.psapi;

import v2.org.analysis.apihandle.winapi.API;

/**
 * @author Yen Nguyen
 *
 */
public abstract class PsapiAPI extends API {
	public PsapiAPI() {
		this.libraryName = "psapi.dll";
	}
}