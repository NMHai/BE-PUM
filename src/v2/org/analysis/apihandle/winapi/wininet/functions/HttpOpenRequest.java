/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet.functions
 * File name: HttpOpenRequest.java
 * Created date: Mar 17, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet.functions;

import java.util.ArrayList;
import java.util.List;

import v2.org.analysis.apihandle.winapi.wininet.WininetAPI;
import v2.org.analysis.apihandle.winapi.wininet.WininetDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Creates an HTTP request handle.
 * 
 * @param hConnect
 *            A handle to an HTTP session returned by InternetConnect.
 * 
 * @param lpszVerb
 *            A pointer to a null-terminated string that contains the HTTP verb
 *            to use in the request. If this parameter is NULL, the function
 *            uses GET as the HTTP verb.
 * 
 * @param lpszObjectName
 *            A pointer to a null-terminated string that contains the name of
 *            the target object of the specified HTTP verb. This is generally a
 *            file name, an executable module, or a search specifier.
 * 
 * @param lpszVersion
 *            A pointer to a null-terminated string that contains the HTTP
 *            version to use in the request. Settings in Internet Explorer will
 *            override the value specified in this parameter. If this parameter
 *            is NULL, the function uses an HTTP version of 1.1 or 1.0,
 *            depending on the value of the Internet Explorer settings.
 * 
 * @param lpszReferer
 *            A pointer to a null-terminated string that specifies the URL of
 *            the document from which the URL in the request (lpszObjectName)
 *            was obtained. If this parameter is NULL, no referrer is specified.
 * 
 * @param lplpszAcceptTypes
 *            A pointer to a null-terminated array of strings that indicates
 *            media types accepted by the client. Here is an example. PCTSTR
 *            rgpszAcceptTypes[] = {_T( text/* ), NULL}; Failing to properly
 *            terminate the array with a NULL pointer will cause a crash. If
 *            this parameter is NULL, no types are accepted by the client.
 *            Servers generally interpret a lack of accept types to indicate
 *            that the client accepts only documents of type "text/*" (that is,
 *            only text documents�no pictures or other binary files). For more
 *            information and a list of valid media types, see
 *            ftp://ftp.isi.edu/in-notes/iana/assignments/media-types/media-
 *            types.
 * 
 * @param dwFlags
 *            Internet options. This parameter can be any of the following
 *            values.
 * 
 * @param dwContext
 *            A pointer to a variable that contains the application-defined
 *            value that associates this operation with any application data.
 * 
 * @return Returns an HTTP request handle if successful, or NULL otherwise. To
 *         retrieve extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class HttpOpenRequest extends WininetAPI {
	public HttpOpenRequest() {
		super();
		NUM_OF_PARMS = 8;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		long t7 = this.params.get(6);
		long t8 = this.params.get(7);

		HANDLE hConnect = new HANDLE(new Pointer(t1));
		String lpszVerb = (t2 == 0L) ? null : memory.getText(this, t2);
		String lpszObjectName = (t3 == 0L) ? null : memory.getText(this, t3);
		String lpszVersion = (t4 == 0L) ? null : memory.getText(this, t4);
		String lpszReferer = (t5 == 0L) ? null : memory.getText(this, t5);
		String[] lplpszAcceptTypes = new String[0];
		List<String> stringList = new ArrayList<>();
		while (true) {
			String text = memory.getText(this, t6);
			if (text != null && text.length() > 0) {
				stringList.add(text);
			} else {
				break;
			}
		}
		if (stringList.size() > 0) {
			lplpszAcceptTypes = new String[stringList.size()];
			for (int i = 0; i < stringList.size(); i++) {
				String text = stringList.get(i);
				lplpszAcceptTypes[i] = text;
			}
		}
		DWORD dwFlags = new DWORD(t7);
		DWORD_PTR dwContext = new DWORD_PTR(t8);
		
		System.out.println("lpszVerb:" + lpszVerb +", " + 
				"lpszObjectName:" + lpszObjectName +", " + 
				"lpszVersion:" + lpszVersion +", " + 
				"lpszReferer:" + lpszReferer +", " + 
				"lplpszAcceptTypes:" + lplpszAcceptTypes.toString() +", ");

		HANDLE ret = WininetDLL.INSTANCE.HttpOpenRequest(hConnect, lpszVerb, lpszObjectName, lpszVersion, lpszReferer,
				lplpszAcceptTypes, dwFlags, dwContext);

		register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
	}

}
