package v2.org.analysis.apihandle.winapi.structures;
/**
 * @author Hai Nguyen
 *
 */
import java.util.List;

import com.sun.jna.platform.win32.WinDef.RECT;

//import com.sun.jna.platform.win32.WinUser.MONITORINFO;
public class MONITORINFO extends com.sun.jna.Structure{
	public int	cbSize;
	public int	dwFlags;
	public RECT	rcMonitor;
	public RECT	rcWork;
	
	@Override
	protected List getFieldOrder() {
		// TODO Auto-generated method stub
		return getFieldList();
	}
	
}
