/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: UpdateLayeredWindow.java
 * Created date: Sep 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.value.LongValue;

/**
 * Updates the position, size, shape, content, and translucency of a layered
 * window.
 * 
 * @param hwnd
 *            A handle to a layered window. A layered window is created by
 *            specifying WS_EX_LAYERED when creating the window with the
 *            CreateWindowEx function.
 * 
 * @param hdcDst
 *            A handle to a DC for the screen. This handle is obtained by
 *            specifying NULL when calling the function. It is used for palette
 *            color matching when the window contents are updated. If hdcDst
 *            isNULL, the default palette will be used.
 * 
 * @param pptDst
 *            A pointer to a structure that specifies the new screen position of
 *            the layered window. If the current position is not changing,
 *            pptDst can be NULL.
 * 
 * @param psize
 *            A pointer to a structure that specifies the new size of the
 *            layered window. If the size of the window is not changing, psize
 *            can be NULL. If hdcSrc is NULL, psize must be NULL.
 * 
 * @param hdcSrc
 *            A handle to a DC for the surface that defines the layered window.
 *            This handle can be obtained by calling the CreateCompatibleDC
 *            function. If the shape and visual context of the window are not
 *            changing, hdcSrc can be NULL.
 * 
 * @param pptSrc
 *            A pointer to a structure that specifies the location of the layer
 *            in the device context. If hdcSrc is NULL, pptSrc should be NULL.
 * 
 * @param crKey
 *            A structure that specifies the color key to be used when composing
 *            the layered window. To generate a COLORREF, use the RGB macro.
 * 
 * @param pblend
 *            A pointer to a structure that specifies the transparency value to
 *            be used when composing the layered window.
 * 
 * @param dwFlags
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError. /**
 * @author Yen Nguyen
 *
 */
public class UpdateLayeredWindow extends User32API {

	public UpdateLayeredWindow() {
		super();
		NUM_OF_PARMS = 9;
	}

	@Override
	public void execute() {
		System.out.println("\tNon-process API");
		
		register.mov("eax", new LongValue(1));

	}

}
