package v2.org.analysis.apihandle.winapi.structures;

/**
 * @author Hai Nguyen
 *
 */

//import com.sun.jna.platform.win32.WinBase.COMPUTER_NAME_FORMAT;
public interface COMPUTER_NAME_FORMAT {
	static int	ComputerNameDnsDomain = 0;
	
	static int	ComputerNameDnsFullyQualified = 0;
	
	static int	ComputerNameDnsHostname = 0;
	
	static int	ComputerNameMax = 0;
	
	static int	ComputerNameNetBIOS = 0;
	
	static int	ComputerNamePhysicalDnsDomain = 0;
	
	static int	ComputerNamePhysicalDnsFullyQualified = 0;
	
	static int	ComputerNamePhysicalDnsHostname = 0;
	
	static int	ComputerNamePhysicalNetBIOS = 0;
}
