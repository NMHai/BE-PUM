/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: InternetOpenUrl.java
 * Created date: Mar 11, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.structures.Wininet.URL_COMPONENTS;
import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetWithoutOptionDLL;
import v2.org.analysis.value.LongValue;

/**
 * @author YeNLH
 *
 */
public class InternetCrackUrl extends WininetAPI {

	public InternetCrackUrl() {
		super();
		NUM_OF_PARMS = 4;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		String lpszUrl = memory.getText(this, t1);
		int dwUrlLength = (int) (t2);
		int dwFlags = (int) (t3);
		URL_COMPONENTS lpUrlComponents = (t4 == 0L) ? null : new URL_COMPONENTS();

		System.out.println(String.format("lpszUrl: %s, dwUrlLength: %d", lpszUrl, dwUrlLength));

		BOOL ret = is64bit()
				? WininetWithoutOptionDLL.INSTANCE.InternetCrackUrlW(new WString(lpszUrl), dwUrlLength, dwFlags, lpUrlComponents)
				: WininetWithoutOptionDLL.INSTANCE.InternetCrackUrlA(lpszUrl, dwUrlLength, dwFlags, lpUrlComponents);
		register.mov("eax", new LongValue(ret.longValue()));

		if (lpUrlComponents != null) {
			t4 = this.params.get(3);

			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.dwStructSize));
			t4 += 4;
			// //////////////////////////
			System.out.println("Stored scheme: " + lpUrlComponents.lpszScheme.getString(0, is64bit()) + " at: "
					+ ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue());
			memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(),
					lpUrlComponents.lpszScheme.getString(0, is64bit()));
			t4 += 4;
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.dwSchemeLength));
			t4 += 4;
			// //////////////////////////
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.nScheme));
			t4 += 4;
			// //////////////////////////
			System.out.println("Stored host name: " + lpUrlComponents.lpszHostName.getString(0, is64bit()) + " at: "
					+ ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue());
			memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(),
					lpUrlComponents.lpszHostName.getString(0, is64bit()));
			t4 += 4;
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.dwHostNameLength));
			t4 += 4;
			// //////////////////////////
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.nPort));
			// //////////////////////////
			t4 += 4;
			memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(),
					lpUrlComponents.lpszUserName.getString(0, is64bit()));
			t4 += 4;
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.dwUserNameLength));
			// //////////////////////////
			t4 += 4;
			memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(),
					lpUrlComponents.lpszPassword.getString(0, is64bit()));
			t4 += 4;
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.dwPasswordLength));
			// //////////////////////////
			t4 += 4;
			memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(),
					lpUrlComponents.lpszUrlPath.getString(0, is64bit()));
			t4 += 4;
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.dwUrlPathLength));
			// //////////////////////////
			t4 += 4;
			memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(),
					lpUrlComponents.lpszExtraInfo.getString(0, is64bit()));
			t4 += 4;
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpUrlComponents.dwExtraInfoLength));

			lpUrlComponents.release();
		}
	}

	public static void main(String[] args) {
		String url = "http://www.whatever.com/user/test.zip";
		int dwUrlLength = url.length();
		int dwFlags = 0x80000000;
		URL_COMPONENTS lpUrlComponents = new URL_COMPONENTS();

		BOOL ret = WininetWithoutOptionDLL.INSTANCE.InternetCrackUrlW(new WString(url), dwUrlLength, dwFlags, lpUrlComponents);

		System.out.println(lpUrlComponents.lpszScheme.getWideString(0));
		System.out.println(lpUrlComponents.lpszHostName.getWideString(0));
		System.out.println(lpUrlComponents.lpszUrlPath.getWideString(0));

		System.out.println(ret);
		System.out.println(Kernel32.INSTANCE.GetLastError());

		lpUrlComponents.release();
	}
}
