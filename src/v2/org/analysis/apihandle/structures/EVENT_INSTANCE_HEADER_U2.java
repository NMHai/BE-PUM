package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
import com.sun.jna.platform.win32.WinDef.USHORT;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class EVENT_INSTANCE_HEADER_U2 extends Union {
    public static class ByValue extends EVENT_INSTANCE_HEADER_U2 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public USHORT FieldTypeFlags;
    public EVENT_INSTANCE_HEADER_U2_S2 tmp2;

}
