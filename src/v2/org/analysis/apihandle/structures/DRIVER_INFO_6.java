package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDLONG;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class DRIVER_INFO_6 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD cVersion;
    public String pName;
    public String pEnvironment;
    public String pDriverPath;
    public String pDataFile;
    public String pConfigFile;
    public String pHelpFile;
    public String pDependentFiles;
    public String pMonitorName;
    public String pDefaultDataType;
    public String pszzPreviousNames;
    public FILETIME ftDriverDate;
    public DWORDLONG dwlDriverVersion;
    public String pszMfgName;
    public String pszOEMUrl;
    public String pszHardwareID;
    public String pszProvider;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "cVersion", "pName", "pEnvironment", "pDriverPath", "pDataFile", "pConfigFile", "pHelpFile", "pDependentFiles", "pMonitorName", "pDefaultDataType", "pszzPreviousNames", "ftDriverDate", "dwlDriverVersion", "pszMfgName", "pszOEMUrl", "pszHardwareID", "pszProvider" });
    }
}
