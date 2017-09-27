package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class JOB_INFO_4 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD JobId;
    public String pPrinterName;
    public String pMachineName;
    public String pUserName;
    public String pDocument;
    public String pNotifyName;
    public String pDatatype;
    public String pPrintProcessor;
    public String pParameters;
    public String pDriverName;
    public DEVMODE pDevMode;
    public String pStatus;
    public Pointer pSecurityDescriptor;
    public DWORD Status;
    public DWORD Priority;
    public DWORD Position;
    public DWORD StartTime;
    public DWORD UntilTime;
    public DWORD TotalPages;
    public DWORD Size;
    public SYSTEMTIME Submitted;
    public DWORD Time;
    public DWORD PagesPrinted;
    public LONG SizeHigh;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "JobId", "pPrinterName", "pMachineName", "pUserName", "pDocument", "pNotifyName", "pDatatype", "pPrintProcessor", "pParameters", "pDriverName", "pDevMode", "pStatus", "pSecurityDescriptor", "Status", "Priority", "Position", "StartTime", "UntilTime", "TotalPages", "Size", "Submitted", "Time", "PagesPrinted", "SizeHigh" });
    }
}
