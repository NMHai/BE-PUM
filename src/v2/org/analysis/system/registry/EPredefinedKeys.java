/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.system.registry
 * File name: PredefinedKeys.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.system.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * An application must open a key before it can add data to the registry. To
 * open a key, an application must supply a handle to another key in the
 * registry that is already open. The system defines predefined keys that are
 * always open. Predefined keys help an application navigate in the registry and
 * make it possible to develop tools that allow a system administrator to
 * manipulate categories of data. Applications that add data to the registry
 * should always work within the framework of predefined keys, so administrative
 * tools can find and use the new data.
 * 
 * An application can use handles to these keys as entry points to the registry.
 * These handles are valid for all implementations of the registry, although the
 * use of the handles may vary from platform to platform. In addition, other
 * predefined handles have been defined for specific platforms. The following
 * are handles to the predefined keys.
 * 
 * @author Yen Nguyen
 *
 */
public enum EPredefinedKeys {
	// @formatter:off
	NONE							(0x0L), 
	HKEY_CLASSES_ROOT				(0x80000000L), 
	HKEY_CURRENT_USER				(0x80000001L), 
	HKEY_LOCAL_MACHINE				(0x80000002L), 
	HKEY_USERS						(0x80000003L), 
	HKEY_PERFORMANCE_DATA			(0x80000004L), 
	HKEY_PERFORMANCE_TEXT			(0x80000050L), 
	HKEY_PERFORMANCE_NLSTEXT		(0x80000060L), 
	HKEY_CURRENT_CONFIG				(0x80000005L), 
	HKEY_DYN_DATA					(0x80000006L),
	HKEY_CURRENT_USER_LOCAL_SETTINGS(0x80000007L);
	// @formatter:on

	private static final Map<Long, EPredefinedKeys> longToTypeMap = new HashMap<Long, EPredefinedKeys>();

	static {
		for (EPredefinedKeys type : EPredefinedKeys.values()) {
			longToTypeMap.put(type.value, type);
		}
	}

	private long value = 0;

	EPredefinedKeys(long value) {
		this.value = value;
	}

	public long getValue() {
		return this.value;
	}

	public static EPredefinedKeys fromLong(long i) {
		EPredefinedKeys type = longToTypeMap.get(Long.valueOf(i));
		if (type == null) {
			return EPredefinedKeys.NONE;
		}
		return type;
	}
}
