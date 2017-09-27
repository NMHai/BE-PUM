package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.LONGLONG;
import com.sun.jna.ptr.ByteByReference;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PDH_FMT_COUNTERVALUE_U2 extends Union {
    public static class ByValue extends PDH_FMT_COUNTERVALUE_U2 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public LONG longValue;
    public double doubleValue;
    public LONGLONG largeValue;
    public ByteByReference AnsiStringValue;
    public CHARByReference WideStringValue;

}
