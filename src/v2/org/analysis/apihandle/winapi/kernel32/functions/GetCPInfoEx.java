/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetCPInfoEx.java
 * Created date: Oct 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.Winnls.CPINFOEX;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

/**
 * Retrieves information about any valid installed or available code page.
 * 
 * @param CodePage
 *            Identifier for the code page for which to retrieve information.
 *            The application can specify the code page identifier for any
 *            installed or available code page, or one of the following
 *            predefined values. See Code Page Identifiers for a list of
 *            identifiers for ANSI and other code pages.
 * 
 * @param dwFlags
 *            Reserved; must be 0.
 * 
 * @param lpCPInfoEx
 *            Pointer to a CPINFOEX structure that receives information about
 *            the code page.
 * 
 * @return Returns a nonzero value if successful, or 0 otherwise. To get
 *         extended error information, the application can call GetLastError,
 *         which can return one of the following error codes:
 * 
 *         ERROR_INVALID_PARAMETER. Any of the parameter values was invalid.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCPInfoEx extends Kernel32API {

	public GetCPInfoEx() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		UINT CodePage = new UINT(t1);
		DWORD dwFlags = new DWORD(t2);
		CPINFOEX lpCPInfoEx = new CPINFOEX();
		
		BOOL ret = Kernel32DLL.INSTANCE.GetCPInfoEx(CodePage, dwFlags, lpCPInfoEx);
		
		register.mov("eax", new LongValue(ret.longValue()));
		
//		public UINT  MaxCharSize;
//		public BYTE  DefaultChar[] = new BYTE[2];
//		public BYTE  LeadByte[] = new BYTE[12];
//		public char UnicodeDefaultChar;
//		public UINT  CodePage;
//		public byte CodePageName[] = new byte[260];
		if (lpCPInfoEx != null) {
			memory.setDoubleWordMemoryValue(t3, new LongValue(lpCPInfoEx.MaxCharSize.longValue()));
			t3 = t3 + 4;
			
			for (int i = 0; i < lpCPInfoEx.DefaultChar.length; i++) {
				memory.setByteMemoryValue(t3, new LongValue(lpCPInfoEx.DefaultChar[i].longValue()));
				t3++;
			}
			
			for (int i = 0; i < lpCPInfoEx.LeadByte.length; i++) {
				memory.setByteMemoryValue(t3, new LongValue(lpCPInfoEx.LeadByte[i].longValue()));
				t3++;
			}

			memory.setByteMemoryValue(t3, new LongValue(lpCPInfoEx.UnicodeDefaultChar));
			t3++;
			

			memory.setDoubleWordMemoryValue(t3, new LongValue(lpCPInfoEx.CodePage.longValue()));
			t3 = t3 + 4;

			for (int i = 0; i < lpCPInfoEx.CodePageName.length; i++) {
				memory.setByteMemoryValue(t3, new LongValue(lpCPInfoEx.CodePageName[i]));
				t3++;
			}
		}
	}

}
