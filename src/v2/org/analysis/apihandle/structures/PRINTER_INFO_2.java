package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class PRINTER_INFO_2 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public String pServerName;
    public String pPrinterName;
    public String pShareName;
    public String pPortName;
    public String pDriverName;
    public String pComment;
    public String pLocation;
    public DEVMODE pDevMode;
    public String pSepFile;
    public String pPrintProcessor;
    public String pDatatype;
    public String pParameters;
    public Pointer pSecurityDescriptor;
    public DWORD Attributes;
    public DWORD Priority;
    public DWORD DefaultPriority;
    public DWORD StartTime;
    public DWORD UntilTime;
    public DWORD Status;
    public DWORD cJobs;
    public DWORD AveragePPM;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "pServerName", "pPrinterName", "pShareName", "pPortName", "pDriverName", "pComment", "pLocation", "pDevMode", "pSepFile", "pPrintProcessor", "pDatatype", "pParameters", "pSecurityDescriptor", "Attributes", "Priority", "DefaultPriority", "StartTime", "UntilTime", "Status", "cJobs", "AveragePPM" });
    }
}
