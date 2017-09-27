package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import v2.org.analysis.apihandle.winapi.structures.TIME_ZONE_INFORMATION;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class TRACE_LOGFILE_HEADER extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public ULONG BufferSize;
    public TRACE_LOGFILE_HEADER_U2 tmp2;
    public ULONG ProviderVersion;
    public ULONG NumberOfProcessors;
    public LARGE_INTEGER EndTime;
    public ULONG TimerResolution;
    public ULONG MaximumFileSize;
    public ULONG LogFileMode;
    public ULONG BuffersWritten;
    public TRACE_LOGFILE_HEADER_U10 tmp10;
    public LPWSTR LoggerName;
    public LPWSTR LogFileName;
    public TIME_ZONE_INFORMATION TimeZone;
    public LARGE_INTEGER BootTime;
    public LARGE_INTEGER PerfFreq;
    public LARGE_INTEGER StartTime;
    public ULONG ReservedFlags;
    public ULONG BuffersLost;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "BufferSize", "tmp2", "ProviderVersion", "NumberOfProcessors", "EndTime", "TimerResolution", "MaximumFileSize", "LogFileMode", "BuffersWritten", "tmp10", "LoggerName", "LogFileName", "TimeZone", "BootTime", "PerfFreq", "StartTime", "ReservedFlags", "BuffersLost" });
    }
}
