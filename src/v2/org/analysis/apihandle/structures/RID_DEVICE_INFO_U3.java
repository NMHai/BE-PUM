package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class RID_DEVICE_INFO_U3 extends Union {
    public static class ByValue extends RID_DEVICE_INFO_U3 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public RID_DEVICE_INFO_MOUSE mouse;
    public RID_DEVICE_INFO_KEYBOARD keyboard;
    public RID_DEVICE_INFO_HID hid;

}
