package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
import com.sun.jna.platform.win32.WinDef.ULONGLONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class ULARGE_INTEGER extends Union {
    public static class ByValue extends ULARGE_INTEGER implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULARGE_INTEGER_S1 tmp1;
    public ULARGE_INTEGER_S2 tmp2;
    public ULONGLONG QuadPart;

}
