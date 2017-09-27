/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.system.registry
 * File name: ERegKeySecuritynAccessRights.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.system.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * The Windows security model enables you to control access to registry keys.
 * For more information about security, see Access-Control Model.
 * 
 * You can specify a security descriptor for a registry key when you call the
 * RegCreateKeyEx or RegSetKeySecurity function. If you specify NULL, the key
 * gets a default security descriptor. The ACLs in a default security descriptor
 * for a key are inherited from its direct parent key.
 * 
 * To get the security descriptor of a registry key, call the RegGetKeySecurity,
 * GetNamedSecurityInfo, or GetSecurityInfo function.
 * 
 * The valid access rights for registry keys include the DELETE, READ_CONTROL,
 * WRITE_DAC, and WRITE_OWNER standard access rights. Registry keys do not
 * support the SYNCHRONIZE standard access right.
 * 
 * @author Yen Nguyen
 *
 */
public enum ERegKeySecuritynAccessRights {
	NULL (0x0),
	KEY_ALL_ACCESS (0xF003F), 
	// Combines the STANDARD_RIGHTS_REQUIRED, KEY_QUERY_VALUE, KEY_SET_VALUE, 
	// KEY_CREATE_SUB_KEY, KEY_ENUMERATE_SUB_KEYS, KEY_NOTIFY, and KEY_CREATE_LINK access rights.
	KEY_CREATE_LINK (0x0020), 
	// Reserved for system use.
	KEY_CREATE_SUB_KEY (0x0004), 
	// Required to create a subkey of a registry key.
	KEY_ENUMERATE_SUB_KEYS (0x0008), 
	// Required to enumerate the subkeys of a registry key.
	KEY_EXECUTE (0x20019),	
	// Equivalent to KEY_READ.
	KEY_NOTIFY (0x0010), 
	//	Required to request change notifications for a registry key or for subkeys of a registry key.
	KEY_QUERY_VALUE (0x0001), 
	// Required to query the values of a registry key.
	KEY_READ (0x20019), 
	// Combines the STANDARD_RIGHTS_READ, KEY_QUERY_VALUE, KEY_ENUMERATE_SUB_KEYS, and KEY_NOTIFY values.
	KEY_SET_VALUE (0x0002), 
	// Required to create, delete, or set a registry value.
	KEY_WOW64_32KEY (0x0200),
	// Indicates that an application on 64-bit Windows should operate on the 32-bit registry view. 
	// This flag is ignored by 32-bit Windows. For more information, see Accessing an Alternate Registry View.
	// This flag must be combined using the OR operator with the other flags in this table 
	// that either query or access registry values.
	// Windows 2000:  This flag is not supported.
	KEY_WOW64_64KEY (0x0100),
	// Indicates that an application on 64-bit Windows should operate on the 64-bit registry view. 
	// This flag is ignored by 32-bit Windows. For more information, see Accessing an Alternate Registry View.
	// This flag must be combined using the OR operator with the other flags in this table 
	// that either query or access registry values.
	// Windows 2000:  This flag is not supported.
	KEY_WRITE (0x20006);
	// Combines the STANDARD_RIGHTS_WRITE, KEY_SET_VALUE, and KEY_CREATE_SUB_KEY access rights.
	

	private static final Map<Long, ERegKeySecuritynAccessRights> longToTypeMap = new HashMap<Long, ERegKeySecuritynAccessRights>();

	static {
		for (ERegKeySecuritynAccessRights type : ERegKeySecuritynAccessRights.values()) {
			longToTypeMap.put(type.value, type);
		}
	}

	private long value = 0;

	ERegKeySecuritynAccessRights(long value) {
		this.value = value;
	}

	public long getValue() {
		return this.value;
	}

	public static ERegKeySecuritynAccessRights fromLong(long i) {
		ERegKeySecuritynAccessRights type = longToTypeMap.get(Long.valueOf(i));
		if (type == null)
			return ERegKeySecuritynAccessRights.NULL;
		return type;
	}
}
