/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: AddFontResource.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32.functions;

import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.WString;

/**
 * The AddFontResource function adds the font resource from the specified file
 * to the system font table. The font can subsequently be used for text output
 * by any application.
 * 
 * @param lpszFilename
 *            A pointer to a null-terminated character string that contains a
 *            valid font file name. This parameter can specify any of the
 *            following files.
 * 
 * @return If the function succeeds, the return value specifies the number of
 *         fonts added. If the function fails, the return value is zero. No
 *         extended error information is available.
 * 
 * @author Yen Nguyen
 *
 */
public class AddFontResource extends Gdi32API {

	public AddFontResource() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		WString lpszFilename = (t1 == 0L) ? null : new WString(memory.getText(this, t1));
		int ret = Gdi32DLL.INSTANCE.AddFontResource(lpszFilename);

		register.mov("eax", new LongValue(ret));
		System.out.println("Return Value: " + ret);
	}
}
