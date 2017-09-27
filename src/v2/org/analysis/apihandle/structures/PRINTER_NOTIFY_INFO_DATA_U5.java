package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PRINTER_NOTIFY_INFO_DATA_U5 extends Union {
    public static class ByValue extends PRINTER_NOTIFY_INFO_DATA_U5 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
	public DWORD[] adwData = new DWORD[2];
    public PRINTER_NOTIFY_INFO_DATA_U5_S2 tmp2;

}
