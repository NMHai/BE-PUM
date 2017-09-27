package v2.org.analysis.apihandle.structures;
import com.sun.jna.Union;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class INPUT_RECORD_U2 extends Union {
    public static class ByValue extends INPUT_RECORD_U2 implements Union.ByValue { };
    
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public KEY_EVENT_RECORD KeyEvent;
    public MOUSE_EVENT_RECORD MouseEvent;
    public WINDOW_BUFFER_SIZE_RECORD WindowBufferSizeEvent;
    public MENU_EVENT_RECORD MenuEvent;
    public FOCUS_EVENT_RECORD FocusEvent;

}
