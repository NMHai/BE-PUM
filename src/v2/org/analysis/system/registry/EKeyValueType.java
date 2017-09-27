/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.system.registry
 * File name: KeyValueType.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.system.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry value can store data in various formats. When you store data
 * under a registry value, for instance by calling the RegSetValueEx
 * function, you can specify one of the following values to indicate the
 * type of data being stored. When you retrieve a registry value, functions
 * such as RegQueryValueEx use these values to indicate the type of data
 * retrieved.
 * 
 * The following registry value types are defined in Winnt.h.
 * 
 * @author Yen Nguyen
 *
 */
public enum EKeyValueType {
	// @formatter:off
	REG_NONE(0) // No value type
	, REG_SZ(1) // Unicode nul terminated string
	, REG_EXPAND_SZ(2) // Unicode nul terminated string (with environment variable references)
	, REG_BINARY(3) // Free form binary
	, REG_DWORD(4) // 32-bit number
	, REG_DWORD_LITTLE_ENDIAN(4) // 32-bit number (same as REG_DWORD)
	, REG_DWORD_BIG_ENDIAN(5) // 32-bit number
	, REG_LINK(6) // Symbolic Link (unicode)
	, REG_MULTI_SZ(7) // Multiple Unicode strings
	, REG_RESOURCE_LIST(8) // Resource list in the resource map
	, REG_FULL_RESOURCE_DESCRIPTOR(9) // Resource list in the hardware description
	, REG_RESOURCE_REQUIREMENTS_LIST(10) //
	, REG_QWORD(11) // 64-bit number
	, REG_QWORD_LITTLE_ENDIAN(11); // 64-bit number (same as REG_QWORD)
	// @formatter:on

	private static final Map<Integer, EKeyValueType> intToTypeMap = new HashMap<Integer, EKeyValueType>();

	static {
		for (EKeyValueType type : EKeyValueType.values()) {
			intToTypeMap.put(type.value, type);
		}
	}

	private int value = 0;

	EKeyValueType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static EKeyValueType fromInt(int i) {
		EKeyValueType type = intToTypeMap.get(Integer.valueOf(i));
		if (type == null)
			return EKeyValueType.REG_NONE;
		return type;
	}
}
