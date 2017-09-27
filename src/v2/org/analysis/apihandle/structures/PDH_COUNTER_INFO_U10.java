package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PDH_COUNTER_INFO_U10 extends Union {
    public static class ByValue extends PDH_COUNTER_INFO_U10 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public PDH_DATA_ITEM_PATH_ELEMENTS DataItemPath;
    public PDH_DATA_ITEM_PATH_ELEMENTS CounterPath;
    public PDH_COUNTER_INFO_U10_S3 tmp3;

}
