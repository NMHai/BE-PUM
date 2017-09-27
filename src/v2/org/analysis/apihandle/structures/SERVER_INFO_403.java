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
 
public  class SERVER_INFO_403 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD sv403_ulist_mtime;
    public DWORD sv403_glist_mtime;
    public DWORD sv403_alist_mtime;
    public char sv403_alerts;
    public DWORD sv403_security;
    public DWORD sv403_numadmin;
    public DWORD sv403_lanmask;
    public LPWSTR sv403_guestacct;
    public DWORD sv403_chdevs;
    public DWORD sv403_chdevq;
    public DWORD sv403_chdevjobs;
    public DWORD sv403_connections;
    public DWORD sv403_shares;
    public DWORD sv403_openfiles;
    public DWORD sv403_sessopens;
    public DWORD sv403_sessvcs;
    public DWORD sv403_sessreqs;
    public DWORD sv403_opensearch;
    public DWORD sv403_activelocks;
    public DWORD sv403_numreqbuf;
    public DWORD sv403_sizreqbuf;
    public DWORD sv403_numbigbuf;
    public DWORD sv403_numfiletasks;
    public DWORD sv403_alertsched;
    public DWORD sv403_erroralert;
    public DWORD sv403_logonalert;
    public DWORD sv403_accessalert;
    public DWORD sv403_diskalert;
    public DWORD sv403_netioalert;
    public DWORD sv403_maxauditsz;
    public LPWSTR sv403_srvheuristics;
    public DWORD sv403_auditedevents;
    public DWORD sv403_autoprofile;
    public LPWSTR sv403_autopath;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "sv403_ulist_mtime", "sv403_glist_mtime", "sv403_alist_mtime", "sv403_alerts", "sv403_security", "sv403_numadmin", "sv403_lanmask", "sv403_guestacct", "sv403_chdevs", "sv403_chdevq", "sv403_chdevjobs", "sv403_connections", "sv403_shares", "sv403_openfiles", "sv403_sessopens", "sv403_sessvcs", "sv403_sessreqs", "sv403_opensearch", "sv403_activelocks", "sv403_numreqbuf", "sv403_sizreqbuf", "sv403_numbigbuf", "sv403_numfiletasks", "sv403_alertsched", "sv403_erroralert", "sv403_logonalert", "sv403_accessalert", "sv403_diskalert", "sv403_netioalert", "sv403_maxauditsz", "sv403_srvheuristics", "sv403_auditedevents", "sv403_autoprofile", "sv403_autopath" });
    }
}
