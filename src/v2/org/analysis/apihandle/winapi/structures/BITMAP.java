package v2.org.analysis.apihandle.winapi.structures;

import java.util.List;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

/**
 * @author Hai Nguyen
 *
 */

//import com.sun.jna.platform.win32.WinGDI.BITMAP;
public class BITMAP extends com.sun.jna.Structure{

	public NativeLong bmType;
	public NativeLong bmWidth;
	public NativeLong bmHeight;
	public NativeLong bmWidthBytes;
	public short bmPlanes;
	public short bmBitsPixel;
	public Pointer bmBits;
	@Override
	protected List getFieldOrder() {
		// TODO Auto-generated method stub
		return getFieldList();
	}

}
