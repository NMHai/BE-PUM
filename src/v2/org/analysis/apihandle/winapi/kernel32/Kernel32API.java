/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32
 * File name: Kernel32API.java
 * Created date: Jan 30, 2015
 * Decription:
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32;

import v2.org.analysis.apihandle.winapi.API;

/**
 * @author Yen Nguyen
 *
 */
public abstract class Kernel32API extends API {
	public Kernel32API() {
		this.libraryName = "kernel32.dll";
	}
}