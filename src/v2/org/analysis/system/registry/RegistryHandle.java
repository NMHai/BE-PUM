package v2.org.analysis.system.registry;

import java.util.HashMap;
import java.util.Map;

import v2.org.analysis.util.PairEntry;
import v2.org.analysis.util.TriadEntry;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinReg.HKEY;

/**
 * This class handle all the registry that not belong to the real registry of
 * system. In other words, this class contain all the registry (handle, key,
 * value) created by input binary file.
 * 
 * @author Yen Nguyen
 *
 */
public class RegistryHandle {
	/**
	 * This map store all the handle created by API
	 */
	private static Map<Long, TriadEntry<EPredefinedKeys, String, ERegKeySecuritynAccessRights>> handleMap = new HashMap<Long, TriadEntry<EPredefinedKeys, String, ERegKeySecuritynAccessRights>>();

	/**
	 * This map store all the registry value that not belong to the real
	 * registry of system
	 */
	private static Map<EPredefinedKeys, Map<String, PairEntry<EKeyValueType, char[]>>> registryMap = new HashMap<EPredefinedKeys, Map<String, PairEntry<EKeyValueType, char[]>>>();

	/**
	 * Constant Value of key node
	 */
	private static final PairEntry<EKeyValueType, char[]> KEY_NODE = new PairEntry<EKeyValueType, char[]>(
			EKeyValueType.fromInt(0), null);

	/**
	 * Base address
	 */
	private static final long BASE_HANDLE = 0xDDDDD;
	private static int offset = 0;

	/**
	 * Generate a new handle to the opened key.
	 * 
	 * @return A value of the opened key hanle.
	 */
	public static long generateNewHandle() {
		return BASE_HANDLE + (offset++);
	}

	/**
	 * Add a new key handle
	 * 
	 * @param newKey
	 *            A pointer to a variable that receives a handle to the opened
	 *            key.
	 * 
	 * @param hKey
	 *            A handle to an open registry key. This handle is returned by
	 *            the RegCreateKeyEx or RegOpenKeyEx function, or it can be one
	 *            of the following predefined keys: HKEY_CLASSES_ROOT
	 *            HKEY_CURRENT_CONFIG HKEY_CURRENT_USER HKEY_LOCAL_MACHINE
	 *            HKEY_USERS
	 * 
	 * @param subKey
	 *            The name of the registry key to be opened. This key must be a
	 *            subkey of the key identified by the hKey parameter. Key names
	 *            are not case sensitive. If this parameter is NULL or a pointer
	 *            to an empty string, the function returns the same handle that
	 *            was passed in.
	 */
	public static void addHandle(HKEY newKey, long hKey, String subKey, long REGSAM) {
		if (newKey != null) {
			addHandle(Pointer.nativeValue(newKey.getPointer()), hKey, subKey,
					ERegKeySecuritynAccessRights.fromLong(REGSAM));
		}
	}

	public static void addHandle(long newKey, long hKey, String subKey, ERegKeySecuritynAccessRights REGSAM) {
		if (newKey != 0L) {
			subKey = subKey.toUpperCase();
			handleMap.put(newKey, new TriadEntry<EPredefinedKeys, String, ERegKeySecuritynAccessRights> //
					(EPredefinedKeys.fromLong(hKey), subKey, REGSAM));
			setRegValue(newKey, "", 0, null);
		}
	}

	/**
	 * Remove the handle from map (after it had been closed)
	 * 
	 * @param hKey
	 *            A pointer to a variable that handle to the opened key.
	 */
	public static void removeHandle(HKEY hKey) {
		if (hKey != null) {
			handleMap.remove(Pointer.nativeValue(hKey.getPointer()));
		}
	}

	/**
	 * Get key entry (contains PredefinedKeys and value) by the variable that
	 * handle to the opened key. (undefined object)
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @return KeyTriadEntry if exists, otherwise NULL.
	 */
	private static TriadEntry<EPredefinedKeys, String, ERegKeySecuritynAccessRights> getKeyPairEntry(Object hKey) {
		if (hKey == null)
			return null;

		if (hKey instanceof HKEY) {
			return getKeyPairEntry(Pointer.nativeValue(((HKEY) hKey).getPointer()));
		} else if (hKey instanceof Long) {
			return getKeyPairEntry(((Long) hKey).longValue());
		} else {
			return null;
		}
	}

	/**
	 * Get key entry (contains PredefinedKeys, value and Security&AcessRight) by
	 * the variable that handle to the opened key. (defined object)
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @return KeyTriadEntry if exists, otherwise NULL.
	 */
	private static TriadEntry<EPredefinedKeys, String, ERegKeySecuritynAccessRights> getKeyPairEntry(long hKey) {
		return handleMap.get(hKey);
	}

	/**
	 * Set existed registry value or put a new registry value.
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @param name
	 *            The name of registry want to affect.
	 * 
	 * @param value
	 *            The new value of affected registry.
	 */
	public static void setRegValue(Object hKey, String name, long type, char[] value) {
		TriadEntry<EPredefinedKeys, String, ERegKeySecuritynAccessRights> entryKey = getKeyPairEntry(hKey);

		if (entryKey == null)
			return;
		
		name = name.toUpperCase();
		EPredefinedKeys key = entryKey.getFirst();
		String path = String.format("%s\\%s", entryKey.getSecond(), name);

		Map<String, PairEntry<EKeyValueType, char[]>> regDataMap = registryMap.get(key);

		if (regDataMap == null) {
			regDataMap = new HashMap<String, PairEntry<EKeyValueType, char[]>>();
			registryMap.put(key, regDataMap);
		}

		if (regDataMap != null) {
			if (type == 0 && value == null) {
				// Put new key
				regDataMap.put(path, KEY_NODE);
			} else {
				// Put value
				regDataMap.put(path, new PairEntry<EKeyValueType, char[]>(EKeyValueType.fromInt((int) type), value));
			}
			
			// Construct key tree
			String[] split = path.split("\\\\");
			StringBuffer pathBuilder = new StringBuffer();
			for (int i = 0; i < (split.length - 1); i++) {
				pathBuilder.append(split[i]);
				pathBuilder.append('\\');

				regDataMap.put(pathBuilder.toString(), KEY_NODE);
			}
		}
	}

	/**
	 * Query registry value in virtual registry management.
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @param name
	 *            The name of registry want to take value.
	 * 
	 * @return char type array of existed value, or NULL if not exists.
	 */
	public static PairEntry<EKeyValueType, char[]> queryRegValue(Object hKey, String name) {
		TriadEntry<EPredefinedKeys, String, ERegKeySecuritynAccessRights> entryKey = getKeyPairEntry(hKey);

		if (entryKey == null)
			return null;

		name = name.toUpperCase();
		EPredefinedKeys key = entryKey.getFirst();
		String path = String.format("%s\\%s", entryKey.getSecond(), name);

		Map<String, PairEntry<EKeyValueType, char[]>> regMap = registryMap.get(key);

		if (regMap != null)
			return regMap.get(path);

		return null;
	}

	/**
	 * Check if a registry key is existing in virtual system
	 * 
	 * @param predefinedKey
	 *            A handle to an open registry key. This handle is returned by
	 *            the RegCreateKeyEx or RegOpenKeyEx function, or it can be one
	 *            of the following predefined keys: HKEY_CLASSES_ROOT
	 *            HKEY_CURRENT_CONFIG HKEY_CURRENT_USER HKEY_LOCAL_MACHINE
	 *            HKEY_USERS
	 * 
	 * @param name
	 *            The name of the registry key to be opened.
	 * 
	 * @return TRUE if exist, otherwise FALSE
	 */
	public static boolean isKeyExist(long predefinedKey, String name) {
		EPredefinedKeys key = EPredefinedKeys.fromLong(predefinedKey);
		if (key != EPredefinedKeys.NONE) {
			Map<String, PairEntry<EKeyValueType, char[]>> regMap = registryMap.get(key);
			if (regMap != null) {
				name = name.toUpperCase();
				PairEntry<EKeyValueType, char[]> value = regMap.get(String.format("%s\\", name));

				if (value != null) {
					return true;
				}
			}
		}
		return false;
	}
}
