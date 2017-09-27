package v2.org.analysis.apihandle.structures;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
 
/* 
* This class is generated automatically from PyWin32 Project - Generator Module
* Author: Le Vinh
*/
 
public  class WKSTA_INFO_502 extends Structure {
    // Part 1: Define possible constant value, {option, this part can be empty}

    // Part 2: Define inner class type - nested type, {option, this part can be empty}

    // Part 3: Define fields of class
    public DWORD wki502_char_wait;
    public DWORD wki502_collection_time;
    public DWORD wki502_maximum_collection_count;
    public DWORD wki502_keep_conn;
    public DWORD wki502_max_cmds;
    public DWORD wki502_sess_timeout;
    public DWORD wki502_siz_char_buf;
    public DWORD wki502_max_threads;
    public DWORD wki502_lock_quota;
    public DWORD wki502_lock_increment;
    public DWORD wki502_lock_maximum;
    public DWORD wki502_pipe_increment;
    public DWORD wki502_pipe_maximum;
    public DWORD wki502_cache_file_timeout;
    public DWORD wki502_dormant_file_limit;
    public DWORD wki502_read_ahead_throughput;
    public DWORD wki502_num_mailslot_buffers;
    public DWORD wki502_num_srv_announce_buffers;
    public DWORD wki502_max_illegal_datagram_events;
    public DWORD wki502_illegal_datagram_event_reset_frequency;
    public BOOL wki502_log_election_packets;
    public BOOL wki502_use_opportunistic_locking;
    public BOOL wki502_use_unlock_behind;
    public BOOL wki502_use_close_behind;
    public BOOL wki502_buf_named_pipes;
    public BOOL wki502_use_lock_read_unlock;
    public BOOL wki502_utilize_nt_caching;
    public BOOL wki502_use_raw_read;
    public BOOL wki502_use_raw_write;
    public BOOL wki502_use_write_raw_data;
    public BOOL wki502_use_encryption;
    public BOOL wki502_buf_files_deny_write;
    public BOOL wki502_buf_read_only_files;
    public BOOL wki502_force_core_create_mode;
    public BOOL wki502_use_512_byte_max_transfer;

    // Part 4: List of field names
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] {
                "wki502_char_wait", "wki502_collection_time", "wki502_maximum_collection_count", "wki502_keep_conn", "wki502_max_cmds", "wki502_sess_timeout", "wki502_siz_char_buf", "wki502_max_threads", "wki502_lock_quota", "wki502_lock_increment", "wki502_lock_maximum", "wki502_pipe_increment", "wki502_pipe_maximum", "wki502_cache_file_timeout", "wki502_dormant_file_limit", "wki502_read_ahead_throughput", "wki502_num_mailslot_buffers", "wki502_num_srv_announce_buffers", "wki502_max_illegal_datagram_events", "wki502_illegal_datagram_event_reset_frequency", "wki502_log_election_packets", "wki502_use_opportunistic_locking", "wki502_use_unlock_behind", "wki502_use_close_behind", "wki502_buf_named_pipes", "wki502_use_lock_read_unlock", "wki502_utilize_nt_caching", "wki502_use_raw_read", "wki502_use_raw_write", "wki502_use_write_raw_data", "wki502_use_encryption", "wki502_buf_files_deny_write", "wki502_buf_read_only_files", "wki502_force_core_create_mode", "wki502_use_512_byte_max_transfer" });
    }
}
