package v2.org.analysis.apihandle.winapi.structures;
/**
 * @author Hai Nguyen
 *
 */
import com.sun.jna.Pointer;

//import com.sun.jna.platform.win32.WinUser.HMONITOR;
public class HMONITOR extends com.sun.jna.platform.win32.WinNT.HANDLE{
	public HMONITOR() {
		super();
	}
	
	public HMONITOR(Pointer p) {
		this.setPointer(p);
	}
}
