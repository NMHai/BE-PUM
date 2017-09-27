/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegCreateKeyEx.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.system.registry.EPredefinedKeys;
import v2.org.analysis.system.registry.ERegKeySecuritynAccessRights;
import v2.org.analysis.system.registry.RegistryHandle;
import v2.org.analysis.value.LongValue;

/**
 * Creates the specified registry key. If the key already exists, the function
 * opens it. Note that key names are not case sensitive.
 * 
 * @param hKey
 *            A handle to an open registry key. The calling process must have
 *            KEY_CREATE_SUB_KEY access to the key. For more information, see
 *            Registry Key Security and Access Rights.
 * 
 * @param lpSubKey
 *            The name of a subkey that this function opens or creates. The
 *            subkey specified must be a subkey of the key identified by the
 *            hKey parameter; it can be up to 32 levels deep in the registry
 *            tree. For more information on key names, see Structure of the
 *            Registry.
 * 
 * @param Reserved
 *            This parameter is reserved and must be zero.
 * 
 * @param lpClass
 *            The user-defined class type of this key. This parameter may be
 *            ignored. This parameter can be NULL.
 * 
 * @param dwOptions
 *            This parameter can be one of the following values.
 * 
 *            REG_OPTION_BACKUP_RESTORE 0x00000004L If this flag is set, the
 *            function ignores the samDesired parameter and attempts to open the
 *            key with the access required to backup or restore the key. If the
 *            calling thread has the SE_BACKUP_NAME privilege enabled, the key
 *            is opened with the ACCESS_SYSTEM_SECURITY and KEY_READ access
 *            rights. If the calling thread has the SE_RESTORE_NAME privilege
 *            enabled, beginning with Windows Vista, the key is opened with the
 *            ACCESS_SYSTEM_SECURITY, DELETE and KEY_WRITE access rights. If
 *            both privileges are enabled, the key has the combined access
 *            rights for both privileges. For more information, see Running with
 *            Special Privileges.
 * 
 *            REG_OPTION_CREATE_LINK 0x00000002L Note Registry symbolic links
 *            should only be used for for application compatibility when
 *            absolutely necessary. This key is a symbolic link. The target path
 *            is assigned to the L"SymbolicLinkValue" value of the key. The
 *            target path must be an absolute registry path.
 * 
 *            REG_OPTION_NON_VOLATILE 0x00000000L This key is not volatile; this
 *            is the default. The information is stored in a file and is
 *            preserved when the system is restarted. The RegSaveKey function
 *            saves keys that are not volatile.
 * 
 *            REG_OPTION_VOLATILE 0x00000001L All keys created by the function
 *            are volatile. The information is stored in memory and is not
 *            preserved when the corresponding registry hive is unloaded. For
 *            HKEY_LOCAL_MACHINE, this occurs only when the system initiates a
 *            full shutdown. For registry keys loaded by the RegLoadKey
 *            function, this occurs when the corresponding RegUnLoadKey is
 *            performed. The RegSaveKey function does not save volatile keys.
 *            This flag is ignored for keys that already exist. Note On a user
 *            selected shutdown, a fast startup shutdown is the default behavior
 *            for the system.
 * 
 * @param samDesired
 *            A mask that specifies the access rights for the key to be created.
 *            For more information, see Registry Key Security and Access Rights.
 * 
 * @param lpSecurityAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure that determines
 *            whether the returned handle can be inherited by child processes.
 *            If lpSecurityAttributes is NULL, the handle cannot be inherited.
 * 
 * @param phkResult
 *            A pointer to a variable that receives a handle to the opened or
 *            created key. If the key is not one of the predefined registry
 *            keys, call the RegCloseKey function after you have finished using
 *            the handle.
 * 
 * @param lpdwDisposition
 *            A pointer to a variable that receives one of the following
 *            disposition values.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h. You can use the FormatMessage function with the
 *         FORMAT_MESSAGE_FROM_SYSTEM flag to get a generic description of the
 *         error.
 * 
 * @author Yen Nguyen
 *
 */
public class RegCreateKeyEx extends Advapi32API {

	public RegCreateKeyEx() {
		super();
		NUM_OF_PARMS = 9;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		long t7 = this.params.get(6);
		long t8 = this.params.get(7);
		long t9 = this.params.get(8);
		
		String handleKey = "UNKNOWN";
		switch (Long.toHexString(t1)) {
			case "80000000":
				handleKey = "HKEY_CLASSES_ROOT";
				break;
			case "80000001":
				handleKey = "HKEY_CURRENT_USER";
				break;
			case "80000002":
				handleKey = "HKEY_LOCAL_MACHINE";
				break;
			case "80000003":
				handleKey = "HKEY_USERS";
				break;
			case "80000005":
				handleKey = "HKEY_CURRENT_CONFIG";
				break;
			default: 
				break;
		}		
		
		String lpSubKey = memory.getText(this, t2);
		System.out.println("Handle Key:" + handleKey + ", Subkey:" + lpSubKey);		
		
		EPredefinedKeys hKey = EPredefinedKeys.fromLong(t1);
		if (hKey == EPredefinedKeys.NONE) {
			register.mov("eax", new LongValue(6));
			return;
		}

		ERegKeySecuritynAccessRights accessRights = ERegKeySecuritynAccessRights.fromLong(t6);		

		switch (accessRights) {
		case KEY_ALL_ACCESS:
		case KEY_SET_VALUE:
		case KEY_WRITE:
			/*******************************************/
			/** Below can not set new value in subKey **/
			/*******************************************/
		case KEY_CREATE_SUB_KEY:
		case KEY_ENUMERATE_SUB_KEYS:
		case KEY_NOTIFY:
		case KEY_WOW64_32KEY:
		case KEY_WOW64_64KEY:
			long handle = RegistryHandle.generateNewHandle();
			RegistryHandle.addHandle(handle, hKey.getValue(), lpSubKey, accessRights);
			RegistryHandle.setRegValue(handle, "", 0, null); // Create sub tree
			break;
		case KEY_QUERY_VALUE:
		case KEY_EXECUTE: // Equivalent to KEY_READ.
		case KEY_READ:
			break;
		// Reserved for system use.
		case KEY_CREATE_LINK:
		case NULL:
		default:
			break;
		}
		
		// HaiNM: always set the return value 0 (SUCESS case)
		register.mov("eax", new LongValue(0));
	}

}
