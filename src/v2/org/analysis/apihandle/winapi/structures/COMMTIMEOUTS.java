package v2.org.analysis.apihandle.winapi.structures;

/**
 * @author Hai Nguyen
 *
 */
import java.util.List;
//import com.sun.jna.platform.win32.WinBase.COMMTIMEOUTS;

import com.sun.jna.platform.win32.WinDef.DWORD;

public class COMMTIMEOUTS extends com.sun.jna.Structure{
	public DWORD	ReadIntervalTimeout;
	public DWORD	ReadTotalTimeoutConstant;
	public DWORD	ReadTotalTimeoutMultiplier;
	public DWORD	WriteTotalTimeoutConstant;
	public DWORD	WriteTotalTimeoutMultiplier;
	
	@Override
	protected List getFieldOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
