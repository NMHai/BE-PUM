package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class JOBOBJECT_CPU_RATE_CONTROL_INFORMATION_U2 extends Union {
    public static class ByValue extends JOBOBJECT_CPU_RATE_CONTROL_INFORMATION_U2 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD CpuRate;
    public DWORD Weight;
    public JOBOBJECT_CPU_RATE_CONTROL_INFORMATION_U2_S3 tmp3;

}
