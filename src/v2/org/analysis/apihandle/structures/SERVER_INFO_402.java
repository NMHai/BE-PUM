package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPWSTR;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class SERVER_INFO_402 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD sv402_ulist_mtime;
    public DWORD sv402_glist_mtime;
    public DWORD sv402_alist_mtime;
    public LPWSTR sv402_alerts;
    public DWORD sv402_security;
    public DWORD sv402_numadmin;
    public DWORD sv402_lanmask;
    public LPWSTR sv402_guestacct;
    public DWORD sv402_chdevs;
    public DWORD sv402_chdevq;
    public DWORD sv402_chdevjobs;
    public DWORD sv402_connections;
    public DWORD sv402_shares;
    public DWORD sv402_openfiles;
    public DWORD sv402_sessopens;
    public DWORD sv402_sessvcs;
    public DWORD sv402_sessreqs;
    public DWORD sv402_opensearch;
    public DWORD sv402_activelocks;
    public DWORD sv402_numreqbuf;
    public DWORD sv402_sizreqbuf;
    public DWORD sv402_numbigbuf;
    public DWORD sv402_numfiletasks;
    public DWORD sv402_alertsched;
    public DWORD sv402_erroralert;
    public DWORD sv402_logonalert;
    public DWORD sv402_accessalert;
    public DWORD sv402_diskalert;
    public DWORD sv402_netioalert;
    public DWORD sv402_maxauditsz;
    public LPWSTR sv402_srvheuristics;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sv402_ulist_mtime", "sv402_glist_mtime", "sv402_alist_mtime", "sv402_alerts", "sv402_security", "sv402_numadmin", "sv402_lanmask", "sv402_guestacct", "sv402_chdevs", "sv402_chdevq", "sv402_chdevjobs", "sv402_connections", "sv402_shares", "sv402_openfiles", "sv402_sessopens", "sv402_sessvcs", "sv402_sessreqs", "sv402_opensearch", "sv402_activelocks", "sv402_numreqbuf", "sv402_sizreqbuf", "sv402_numbigbuf", "sv402_numfiletasks", "sv402_alertsched", "sv402_erroralert", "sv402_logonalert", "sv402_accessalert", "sv402_diskalert", "sv402_netioalert", "sv402_maxauditsz", "sv402_srvheuristics" });
    }
}
