package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class EVENT_TRACE_HEADER_U8 extends Union {
    public static class ByValue extends EVENT_TRACE_HEADER_U8 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public EVENT_TRACE_HEADER_U8_S1 tmp1;
    public EVENT_TRACE_HEADER_U8_S2 tmp2;
    public long ProcessorTime;

}
