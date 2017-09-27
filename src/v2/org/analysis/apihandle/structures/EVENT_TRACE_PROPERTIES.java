package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class EVENT_TRACE_PROPERTIES extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public WNODE_HEADER Wnode;
    public ULONG BufferSize;
    public ULONG MinimumBuffers;
    public ULONG MaximumBuffers;
    public ULONG MaximumFileSize;
    public ULONG LogFileMode;
    public ULONG FlushTimer;
    public ULONG EnableFlags;
    public LONG AgeLimit;
    public ULONG NumberOfBuffers;
    public ULONG FreeBuffers;
    public ULONG EventsLost;
    public ULONG BuffersWritten;
    public ULONG LogBuffersLost;
    public ULONG RealTimeBuffersLost;
    public HANDLE LoggerThreadId;
    public ULONG LogFileNameOffset;
    public ULONG LoggerNameOffset;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "Wnode", "BufferSize", "MinimumBuffers", "MaximumBuffers", "MaximumFileSize", "LogFileMode", "FlushTimer", "EnableFlags", "AgeLimit", "NumberOfBuffers", "FreeBuffers", "EventsLost", "BuffersWritten", "LogBuffersLost", "RealTimeBuffersLost", "LoggerThreadId", "LogFileNameOffset", "LoggerNameOffset" });
    }
}
