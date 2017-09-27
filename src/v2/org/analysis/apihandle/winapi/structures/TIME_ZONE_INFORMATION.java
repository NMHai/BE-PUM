package v2.org.analysis.apihandle.winapi.structures;
/**
 * @author Hai Nguyen
 *
 */
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef;

//import com.sun.jna.platform.win32.WinBase.TIME_ZONE_INFORMATION;

//WinDef.LONG	Bias 
//WinDef.LONG	DaylightBias 
//WinBase.SYSTEMTIME	DaylightDate 
//String	DaylightName 
//WinDef.LONG	StandardBias 
//WinBase.SYSTEMTIME	StandardDate 
//String	StandardName 

public class TIME_ZONE_INFORMATION {
	public WinDef.LONG Bias;
	public String StandardName;
	public WinDef.LONG	DaylightBias; 
	public SYSTEMTIME	DaylightDate; 
	public String	DaylightName; 
	public WinDef.LONG	StandardBias; 
	public SYSTEMTIME	StandardDate; 
	
	public TIME_ZONE_INFORMATION () {
		super();
	}

}
