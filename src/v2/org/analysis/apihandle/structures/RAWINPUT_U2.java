package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class RAWINPUT_U2 extends Union {
    public static class ByValue extends RAWINPUT_U2 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public RAWMOUSE mouse;
    public RAWKEYBOARD keyboard;
    public RAWHID hid;

}
