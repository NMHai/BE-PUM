package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class STAT_SERVER_0 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD sts0_start;
    public DWORD sts0_fopens;
    public DWORD sts0_devopens;
    public DWORD sts0_jobsqueued;
    public DWORD sts0_sopens;
    public DWORD sts0_stimedout;
    public DWORD sts0_serrorout;
    public DWORD sts0_pwerrors;
    public DWORD sts0_permerrors;
    public DWORD sts0_syserrors;
    public DWORD sts0_bytessent_low;
    public DWORD sts0_bytessent_high;
    public DWORD sts0_bytesrcvd_low;
    public DWORD sts0_bytesrcvd_high;
    public DWORD sts0_avresponse;
    public DWORD sts0_reqbufneed;
    public DWORD sts0_bigbufneed;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sts0_start", "sts0_fopens", "sts0_devopens", "sts0_jobsqueued", "sts0_sopens", "sts0_stimedout", "sts0_serrorout", "sts0_pwerrors", "sts0_permerrors", "sts0_syserrors", "sts0_bytessent_low", "sts0_bytessent_high", "sts0_bytesrcvd_low", "sts0_bytesrcvd_high", "sts0_avresponse", "sts0_reqbufneed", "sts0_bigbufneed" });
    }
}
