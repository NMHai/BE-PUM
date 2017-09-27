package v2.org.analysis.apihandle.winapi.advapi32;

import java.nio.Buffer;

import v2.org.analysis.apihandle.structures.ENUM_SERVICE_STATUS;
import v2.org.analysis.apihandle.structures.EVENT_INSTANCE_INFO;
import v2.org.analysis.apihandle.structures.HW_PROFILE_INFO;
import v2.org.analysis.apihandle.structures.QUERY_SERVICE_CONFIG;
import v2.org.analysis.apihandle.structures.QUERY_SERVICE_LOCK_STATUS;
import v2.org.analysis.apihandle.structures.VALENT;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinNT.ACL;
import com.sun.jna.platform.win32.WinNT.GENERIC_MAPPING;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.WinNT.LUID;
import com.sun.jna.platform.win32.WinNT.PRIVILEGE_SET;
import com.sun.jna.platform.win32.WinNT.PSID;
import com.sun.jna.platform.win32.WinNT.SECURITY_IMPERSONATION_LEVEL;
import com.sun.jna.platform.win32.WinNT.TOKEN_PRIVILEGES;
import com.sun.jna.platform.win32.WinNT.TOKEN_TYPE;
import com.sun.jna.platform.win32.WinNT.WELL_KNOWN_SID_TYPE;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.platform.win32.WinReg.HKEYByReference;
import com.sun.jna.platform.win32.Winsvc.SC_HANDLE;
import com.sun.jna.platform.win32.Winsvc.SC_STATUS_TYPE;
import com.sun.jna.platform.win32.Winsvc.SERVICE_STATUS;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 */
public interface Advapi32DLL extends StdCallLibrary {
	Advapi32DLL INSTANCE = (Advapi32DLL) Native.loadLibrary("advapi32", Advapi32DLL.class,
			W32APIOptions.DEFAULT_OPTIONS);
	Advapi32DLL SYNC_INSTANCE = (Advapi32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * The CryptAcquireContext function is used to acquire a handle to a
	 * particular key container within a particular cryptographic service
	 * provider (CSP). This returned handle is used in calls to CryptoAPI
	 * functions that use the selected CSP..
	 *
	 * @param phProv
	 * 		A pointer to a handle of a CSP. When you have finished using the CSP, release the handle by calling the CryptReleaseContext function.
	 * @param pszContainer
	 * 		The key container name. This is a null-terminated string that identifies the key container to the CSP. This name is independent of the method used to store the keys. Some
	 * 		CSPs store their key containers internally (in hardware), some use the system registry, and others use the file system. In most cases, when dwFlags is set to
	 * 		CRYPT_VERIFYCONTEXT, pszContainer must be set to NULL. However, for hardware-based CSPs, such as a smart card CSP, can be access publically available information in the
	 * 		specfied container.
	 * @param pszProvider
	 * 		A null-terminated string that contains the name of the CSP to be used.
	 * @param dwProvType
	 * 		Specifies the type of provider to acquire. Defined provider types are discussed in Cryptographic Provider Types.
	 * @param dwFlags
	 * 		Flag values. This parameter is usually set to zero, but some applications set one or more of the following flags.
	 *
	 * @return If the function succeeds, the function returns nonzero (TRUE). If the function fails, it returns zero (FALSE). For extended error information, call GetLastError.
	 */
	BOOL CryptAcquireContext(Pointer phProv, /* _In_ */Pointer pszContainer, /* _In_ */
	                         Pointer pszProvider, /* _In_ */int dwProvType, /* _In_ */int dwFlags);

	/**
	 * The CryptHashData function adds data to a specified hash object. This
	 * function and CryptHashSessionKey can be called multiple times to compute
	 * the hash of long or discontinuous data streams.
	 *
	 * @param hHash
	 * 		Handle of the hash object.
	 * @param pbData
	 * 		A pointer to a buffer that contains the data to be added to the hash object.
	 * @param dwDataLen
	 * 		Number of bytes of data to be added. This must be zero if the CRYPT_USERDATA flag is set.
	 * @param dwFlags
	 * 		The following flag values are defined.
	 *
	 * @return If the function succeeds, the return value is TRUE. If the function fails, the return value is FALSE. For extended error information, call GetLastError.
	 */
	BOOL CryptHashData(/* _In_ *//* HCRYPTHASH */HANDLE hHash, /* _In_ */Pointer pbData, /* _In_ */
	                   int dwDataLen, /* _In_ */int dwFlags);

	/**
	 * The CryptDeriveKey function generates cryptographic session keys derived
	 * from a base data value. This function guarantees that when the same
	 * cryptographic service provider (CSP) and algorithms are used, the keys
	 * generated from the same base data are identical. The base data can be a
	 * password or any other user data.
	 *
	 * @param hProv
	 * 		A HCRYPTPROV handle of a CSP created by a call to CryptAcquireContext.
	 * @param Algid
	 * 		An ALG_ID structure that identifies the symmetric encryption algorithm for which the key is to be generated. The algorithms available will most likely be different for
	 * 		each CSP. For more information about which algorithm identifier is used by the different providers for the key specs AT_KEYEXCHANGE and AT_SIGNATURE, see ALG_ID.
	 * @param hBaseData
	 * 		A handle to a hash object that has been fed the exact base data.
	 * @param dwFlags
	 * 		Specifies the type of key generated.
	 * @param phKey
	 * 		A pointer to a HCRYPTKEY variable to receive the address of the handle of the newly generated key. When you have finished using the key, release the handle by calling the
	 * 		CryptDestroyKey function.
	 *
	 * @return If the function succeeds, the function returns nonzero (TRUE). If the function fails, it returns zero (FALSE). For extended error information, call GetLastError.
	 */
	BOOL CryptDeriveKey(/* _In_ *//* HCRYPTPROV */ULONG_PTR hProv, /* _In_ *//* ALG_ID */UINT Algid, /* _In_ *//* HCRYPTHASH */
	                    ULONG_PTR hBaseData, /* _In_ */DWORD dwFlags, /* _Inout_ *//* HCRYPTKEY */ULONG_PTRByReference phKey);

	/**
	 * The CryptDestroyHash function destroys the hash object referenced by the
	 * hHash parameter. After a hash object has been destroyed, it can no longer
	 * be used.
	 *
	 * @param hHash
	 * 		The handle of the hash object to be destroyed.
	 *
	 * @return If the function succeeds, the return value is nonzero. If the function fails, the return value is zero. For extended error information, call GetLastError.
	 */
	BOOL CryptDestroyHash(/* _In_ *//* HCRYPTHASH */ULONG_PTR hHash);

	/**
	 * The CryptCreateHash function initiates the hashing of a stream of data.
	 * It creates and returns to the calling application a handle to a
	 * cryptographic service provider (CSP) hash object. This handle is used in
	 * subsequent calls to CryptHashData and CryptHashSessionKey to hash session
	 * keys and other streams of data.
	 *
	 * @param hProv
	 * 		A handle to a CSP created by a call to CryptAcquireContext.
	 * @param Algid
	 * 		An ALG_ID value that identifies the hash algorithm to use.
	 * @param hKey
	 * 		If the type of hash algorithm is a keyed hash, such as the Hash-Based Message Authentication Code (HMAC) or Message Authentication Code (MAC) algorithm, the key for the
	 * 		hash is passed in this parameter. For nonkeyed algorithms, this parameter must be set to zero.
	 * @param dwFlags
	 * @param phHash
	 * 		The address to which the function copies a handle to the new hash object. When you have finished using the hash object, release the handle by calling the CryptDestroyHash
	 * 		function.
	 *
	 * @return If the function succeeds, the function returns TRUE. If the function fails, it returns FALSE. For extended error information, call GetLastError.
	 */
	BOOL CryptCreateHash(/* _In_ *//* HCRYPTPROV */HANDLE hProv, /* _In_ *//* ALG_ID */UINT Algid, /* _In_ *//* HCRYPTKEY */
	                     HANDLE hKey, /* _In_ */int dwFlags, /* _Out_ *//* HCRYPTHASH */Pointer phHash);

	/**
	 * The CryptDecrypt function decrypts data previously encrypted by using the
	 * CryptEncrypt function.
	 *
	 * @param hKey
	 * 		A handle to the key to use for the decryption. An application obtains this handle by using either the CryptGenKey or CryptImportKey function.
	 * @param hHash
	 * 		A handle to a hash object. If data is to be decrypted and hashed simultaneously, a handle to a hash object is passed in this parameter. The hash value is updated with the
	 * 		decrypted plaintext. This option is useful when simultaneously decrypting and verifying a signature.
	 * @param Final
	 * 		A Boolean value that specifies whether this is the last section in a series being decrypted. This value is TRUE if this is the last or only block. If this is not the last
	 * 		block, this value is FALSE. For more information, see Remarks.
	 * @param dwFlags
	 * 		The following flag values are defined. CRYPT_OAEP 0x00000040 || CRYPT_DECRYPT_RSA_NO_PADDING_CHECK 0x00000020
	 * @param pbData
	 * 		A pointer to a buffer that contains the data to be decrypted. After the decryption has been performed, the plaintext is placed back into this same buffer.
	 * @param pdwDataLen
	 * 		A pointer to a DWORD value that indicates the length of the pbData buffer. Before calling this function, the calling application sets the DWORD value to the number of
	 * 		bytes to be decrypted. Upon return, the DWORD value contains the number of bytes of the decrypted plaintext.
	 *
	 * @return If the function succeeds, the function returns nonzero (TRUE). If the function fails, it returns zero (FALSE). For extended error information, call GetLastError.
	 */
	BOOL CryptDecrypt(/* _In_ *//* HCRYPTKEY */ULONG_PTR hKey, /* _In_ *//* HCRYPTHASH */ULONG_PTR hHash, /* _In_ */
	                  BOOL Final, /* _In_ */DWORD dwFlags, /* _Inout_ */ByteByReference pbData, /* _Inout_ */
	                  DWORDByReference pdwDataLen);

	/**
	 * The CryptDestroyKey function releases the handle referenced by the hKey
	 * parameter. After a key handle has been released, it is no longer valid
	 * and cannot be used again.
	 *
	 * @param hKey
	 * 		The handle of the key to be destroyed.
	 *
	 * @return If the function succeeds, the return value is nonzero. If the function fails, the return value is zero. For extended error information, call GetLastError.
	 */
	BOOL CryptDestroyKey(/* _In_ *//* HCRYPTKEY */ULONG_PTR hKey);

	/**
	 * The CryptReleaseContext function releases the handle of a cryptographic
	 * service provider (CSP) and a key container. At each call to this
	 * function, the reference count on the CSP is reduced by one. When the
	 * reference count reaches zero, the context is fully released and it can no
	 * longer be used by any function in the application.
	 *
	 * @param hProv
	 * 		Handle of a cryptographic service provider (CSP) created by a call to CryptAcquireContext.
	 * @param dwFlags
	 * 		Reserved for future use and must be zero. If dwFlags is not set to zero, this function returns FALSE but the CSP is released.
	 *
	 * @return If the function succeeds, the return value is nonzero (TRUE). If the function fails, the return value is zero (FALSE). For extended error information, call
	 * GetLastError.
	 */
	BOOL CryptReleaseContext(/* _In_ *//* HCRYPTPROV */ULONG_PTR hProv, /* _In_ */DWORD dwFlags);

	/**
	 * Opens the specified registry key.
	 *
	 * @param hKey
	 * 		A handle to an open registry key. This handle is returned by the RegCreateKeyEx or RegOpenKeyEx function, or it can be one of the following predefined keys:
	 * 		HKEY_CLASSES_ROOT HKEY_CURRENT_CONFIG HKEY_CURRENT_USER HKEY_LOCAL_MACHINE HKEY_USERS
	 * @param lpSubKey
	 * 		The name of the registry key to be opened. This key must be a subkey of the key identified by the hKey parameter. Key names are not case sensitive. If this parameter is
	 * 		NULL or a pointer to an empty string, the function returns the same handle that was passed in.
	 * @param phkResult
	 * 		A pointer to a variable that receives a handle to the opened key. If the key is not one of the predefined registry keys, call the RegCloseKey function after you have
	 * 		finished using the handle.
	 *
	 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the function fails, the return value is a nonzero error code defined in Winerror.h. You can use the
	 * FormatMessage function with the FORMAT_MESSAGE_FROM_SYSTEM flag to get a generic description of the error.
	 */
	LONG RegOpenKey(/* _In_ */HKEY hKey, /* _In_opt_ */String lpSubKey, /* _Out_ */HKEYByReference phkResult);

	/**
	 * Retrieves the type and data for the specified value name associated with
	 * an open registry key.
	 *
	 * @param hKey
	 * 		A handle to an open registry key. The key must have been opened with the KEY_QUERY_VALUE access right. For more information, see Registry Key Security and Access Rights.
	 * @param lpValueName
	 * 		The name of the registry value.
	 * @param lpReserved
	 * 		This parameter is reserved and must be NULL.
	 * @param lpType
	 * 		A pointer to a variable that receives a code indicating the type of data stored in the specified value. For a list of the possible type codes, see Registry Value Types.
	 * 		The lpType parameter can be NULL if the type code is not required.
	 * @param lpData
	 * 		A pointer to a buffer that receives the value's data. This parameter can be NULL if the data is not required.
	 * @param lpcbData
	 * 		A pointer to a variable that specifies the size of the buffer pointed to by the lpData parameter, in bytes. When the function returns, this variable contains the size of
	 * 		the data copied to lpData. The lpcbData parameter can be NULL only if lpData is NULL.
	 *
	 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the function fails, the return value is a system error code. If the lpData buffer is too small to
	 * receive the data, the function returns ERROR_MORE_DATA. If the lpValueName registry value does not exist, the function returns ERROR_FILE_NOT_FOUND.
	 */
	LONG RegQueryValueEx(
	/* _In_ */HKEY hKey,
	/* _In_opt_ */String lpValueName,
	/* _Reserved_ */DWORDByReference lpReserved,
	/* _Out_opt_ */DWORDByReference lpType,
	/* _Out_opt_ */Buffer lpData,
	/* _Inout_opt_ */DWORDByReference lpcbData);

	/**
	 * Creates the specified registry key. If the key already exists, the
	 * function opens it. Note that key names are not case sensitive.
	 *
	 * @param hKey
	 * 		A handle to an open registry key. The calling process must have KEY_CREATE_SUB_KEY access to the key. For more information, see Registry Key Security and Access Rights.
	 * @param lpSubKey
	 * 		The name of a subkey that this function opens or creates. The subkey specified must be a subkey of the key identified by the hKey parameter; it can be up to 32 levels deep
	 * 		in the registry tree. For more information on key names, see Structure of the Registry.
	 * @param Reserved
	 * 		This parameter is reserved and must be zero.
	 * @param lpClass
	 * 		The user-defined class type of this key. This parameter may be ignored. This parameter can be NULL.
	 * @param dwOptions
	 * 		This parameter can be one of the following values.
	 * 		<p>
	 * 		REG_OPTION_BACKUP_RESTORE 0x00000004L If this flag is set, the function ignores the samDesired parameter and attempts to open the key with the access required to backup or
	 * 		restore the key. If the calling thread has the SE_BACKUP_NAME privilege enabled, the key is opened with the ACCESS_SYSTEM_SECURITY and KEY_READ access rights. If the
	 * 		calling thread has the SE_RESTORE_NAME privilege enabled, beginning with Windows Vista, the key is opened with the ACCESS_SYSTEM_SECURITY, DELETE and KEY_WRITE access
	 * 		rights. If both privileges are enabled, the key has the combined access rights for both privileges. For more information, see Running with Special Privileges.
	 * 		<p>
	 * 		REG_OPTION_CREATE_LINK 0x00000002L Note Registry symbolic links should only be used for for application compatibility when absolutely necessary. This key is a symbolic
	 * 		link. The target path is assigned to the L"SymbolicLinkValue" value of the key. The target path must be an absolute registry path.
	 * 		<p>
	 * 		REG_OPTION_NON_VOLATILE 0x00000000L This key is not volatile; this is the default. The information is stored in a file and is preserved when the system is restarted. The
	 * 		RegSaveKey function saves keys that are not volatile.
	 * 		<p>
	 * 		REG_OPTION_VOLATILE 0x00000001L All keys created by the function are volatile. The information is stored in memory and is not preserved when the corresponding registry
	 * 		hive is unloaded. For HKEY_LOCAL_MACHINE, this occurs only when the system initiates a full shutdown. For registry keys loaded by the RegLoadKey function, this occurs when
	 * 		the corresponding RegUnLoadKey is performed. The RegSaveKey function does not save volatile keys. This flag is ignored for keys that already exist. Note On a user selected
	 * 		shutdown, a fast startup shutdown is the default behavior for the system.
	 * @param samDesired
	 * 		A mask that specifies the access rights for the key to be created. For more information, see Registry Key Security and Access Rights.
	 * @param lpSecurityAttributes
	 * 		A pointer to a SECURITY_ATTRIBUTES structure that determines whether the returned handle can be inherited by child processes. If lpSecurityAttributes is NULL, the handle
	 * 		cannot be inherited.
	 * @param phkResult
	 * 		A pointer to a variable that receives a handle to the opened or created key. If the key is not one of the predefined registry keys, call the RegCloseKey function after you
	 * 		have finished using the handle.
	 * @param lpdwDisposition
	 * 		A pointer to a variable that receives one of the following disposition values.
	 *
	 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the function fails, the return value is a nonzero error code defined in Winerror.h. You can use the
	 * FormatMessage function with the FORMAT_MESSAGE_FROM_SYSTEM flag to get a generic description of the error.
	 */
	LONG RegCreateKeyEx(
	/* _In_ */HKEY hKey,
	/* _In_ */String lpSubKey,
	/* _Reserved_ */DWORD Reserved,
	/* _In_opt_ */String lpClass,
	/* _In_ */DWORD dwOptions,
	/* _In_ *//* REGSAM */WORD samDesired,
	/* _In_opt_ */SECURITY_ATTRIBUTES lpSecurityAttributes,
	/* _Out_ */HKEY phkResult,
	/* _Out_opt_ */DWORD lpdwDisposition);

	/**
	 * The IsValidAcl function validates an access control list (ACL).
	 *
	 * @param pAcl
	 * 		A pointer to an ACL structure validated by this function. This value must not be NULL.
	 *
	 * @return If the ACL is valid, the function returns nonzero. If the ACL is not valid, the function returns zero. There is no extended error information for this function; do
	 * not call GetLastError.
	 */
	BOOL IsValidAcl(/* _In_ */ACL pAcl);

	/**
	 * The OpenProcessToken function opens the access token associated with a
	 * process.
	 *
	 * @param ProcessHandle
	 * 		A handle to the process whose access token is opened. The process must have the PROCESS_QUERY_INFORMATION access permission.
	 * @param DesiredAccess
	 * 		dAccess [in] Specifies an access mask that specifies the requested types of access to the access token. These requested access types are compared with the discretionary
	 * 		access control list (DACL) of the token to determine which accesses are granted or denied. For a list of access rights for access tokens, see Access Rights for
	 * 		Access-Token Objects.
	 * @param TokenHandle
	 * 		A pointer to a handle that identifies the newly opened access token when the function returns.
	 *
	 * @return If the function succeeds, the return value is nonzero. If the function fails, the return value is zero. To get extended error information, call GetLastError.
	 */
	BOOL OpenProcessToken(
	/* _In_ */HANDLE ProcessHandle,
	/* _In_ */DWORD DesiredAccess,
	/* _Out_ */HANDLEByReference TokenHandle);

	/**
	 * The AdjustTokenPrivileges function enables or disables privileges in the
	 * specified access token. Enabling or disabling privileges in an access
	 * token requires TOKEN_ADJUST_PRIVILEGES access.
	 *
	 * @param TokenHandle
	 * 		A handle to the access token that contains the privileges to be modified. The handle must have TOKEN_ADJUST_PRIVILEGES access to the token. If the PreviousState parameter
	 * 		is not NULL, the handle must also have TOKEN_QUERY access.
	 * @param DisableAllPrivileges
	 * 		Specifies whether the function disables all of the token's privileges. If this value is TRUE, the function disables all privileges and ignores the NewState parameter. If
	 * 		it is FALSE, the function modifies privileges based on the information pointed to by the NewState parameter.
	 * @param NewState
	 * 		A pointer to a TOKEN_PRIVILEGES structure that specifies an array of privileges and their attributes. If the DisableAllPrivileges parameter is FALSE, the
	 * 		AdjustTokenPrivileges function enables, disables, or removes these privileges for the token. The following table describes the action taken by the AdjustTokenPrivileges
	 * 		function, based on the privilege attribute.
	 * @param BufferLength
	 * 		Specifies the size, in bytes, of the buffer pointed to by the PreviousState parameter. This parameter can be zero if the PreviousState parameter is NULL.
	 * @param PreviousState
	 * 		A pointer to a buffer that the function fills with a TOKEN_PRIVILEGES structure that contains the previous state of any privileges that the function modifies. That is, if
	 * 		a privilege has been modified by this function, the privilege and its previous state are contained in the TOKEN_PRIVILEGES structure referenced by PreviousState. If the
	 * 		PrivilegeCount member of TOKEN_PRIVILEGES is zero, then no privileges have been changed by this function. This parameter can be NULL. If you specify a buffer that is too
	 * 		small to receive the complete list of modified privileges, the function fails and does not adjust any privileges. In this case, the function sets the variable pointed to
	 * 		by the ReturnLength parameter to the number of bytes required to hold the complete list of modified privileges.
	 * @param ReturnLength
	 * 		A pointer to a variable that receives the required size, in bytes, of the buffer pointed to by the PreviousState parameter. This parameter can be NULL if PreviousState is
	 * 		NULL.
	 *
	 * @return If the function succeeds, the return value is nonzero. To determine whether the function adjusted all of the specified privileges, call GetLastError, which returns
	 * one of the following values when the function succeeds:
	 */
	BOOL AdjustTokenPrivileges(
	/* _In_ */HANDLE TokenHandle,
	/* _In_ */BOOL DisableAllPrivileges,
	/* _In_opt_ */TOKEN_PRIVILEGES NewState,
	/* _In_ */DWORD BufferLength,
	/* _Out_opt_ */TOKEN_PRIVILEGES PreviousState,
	/* _Out_opt_ */DWORDByReference ReturnLength);

	/**
	 * The LookupPrivilegeValue function retrieves the locally unique identifier
	 * (LUID) used on a specified system to locally represent the specified
	 * privilege name.
	 *
	 * @param lpSystemName
	 * 		A pointer to a null-terminated string that specifies the name of the system on which the privilege name is retrieved. If a null string is specified, the function attempts
	 * 		to find the privilege name on the local system.
	 * @param lpName
	 * 		A pointer to a null-terminated string that specifies the name of the privilege, as defined in the Winnt.h header file. For example, this parameter could specify the
	 * 		constant, SE_SECURITY_NAME, or its corresponding string, "SeSecurityPrivilege".
	 * @param lpLuid
	 * 		A pointer to a variable that receives the LUID by which the privilege is known on the system specified by the lpSystemName parameter.
	 *
	 * @return If the function succeeds, the function returns nonzero. If the function fails, it returns zero. To get extended error information, call GetLastError.
	 */
	BOOL LookupPrivilegeValue(
	/* _In_opt_ */String lpSystemName,
	/* _In_ */String lpName,
	/* _Out_ */LUID lpLuid);

	/**
	 * Stops a system shutdown that has been initiated.
	 *
	 * @param lpMachineName
	 * 		[in, optional] The network name of the computer where the shutdown is to be stopped. If lpMachineName is NULL or an empty string, the function stops the shutdown on the
	 * 		local computer.
	 *
	 * @return If the function succeeds, the return value is nonzero. If the function fails, the return value is zero. To get extended error information, call GetLastError.
	 */
	BOOL AbortSystemShutdown(
	/* _In_opt_ */String lpMachineName);

	// API's Interfaces
//		int AbortSystemShutdown ( String lpMachineName );

	int AccessCheck(Pointer pSecurityDescriptor, HANDLE ClientToken, DWORD DesiredAccess, GENERIC_MAPPING GenericMapping, PRIVILEGE_SET PrivilegeSet, IntByReference PrivilegeSetLength, IntByReference GrantedAccess, IntByReference AccessStatus);

	BOOL AdjustTokenPrivileges(HANDLE TokenHandle, BOOL DisableAllPrivileges, TOKEN_PRIVILEGES NewState, DWORD BufferLength, TOKEN_PRIVILEGES PreviousState, int[] ReturnLength);

	int BackupEventLog(HANDLE hEventLog, String lpBackupFileName);

	int ChangeServiceConfig(SC_HANDLE hService, DWORD dwServiceType, DWORD dwStartType, DWORD dwErrorControl, String lpBinaryPathName, String lpLoadOrderGroup, IntByReference lpdwTagId, char[] lpDependencies, String lpServiceStartName, String lpPassword, String lpDisplayName);

	int ClearEventLog(HANDLE hEventLog, String lpBackupFileName);

	int OpenEncryptedFileRaw(String lpFileName, ULONG ulFlags, PointerByReference pvContext);

	int CloseEventLog(HANDLE hEventLog);

	int CloseServiceHandle(SC_HANDLE hSCObject);

	int ControlService(SC_HANDLE hService, DWORD dwControl, SERVICE_STATUS lpServiceStatus);

	int ConvertSidToStringSid(PSID Sid, PointerByReference StringSid);

	int ConvertStringSidToSid(String StringSid, WinNT.PSIDByReference Sid);

	int CreateService(SC_HANDLE hSCManager, String lpServiceName, String lpDisplayName, DWORD dwDesiredAccess, DWORD dwServiceType, DWORD dwStartType, DWORD dwErrorControl, String lpBinaryPathName, String lpLoadOrderGroup, IntByReference lpdwTagId, char[] lpDependencies, String lpServiceStartName, String lpPassword);

	int CreateTraceInstanceId(HANDLE RegHandle, EVENT_INSTANCE_INFO pInstInfo);

	int CreateWellKnownSid(WELL_KNOWN_SID_TYPE WellKnownSidType, PSID DomainSid, PSID pSid, IntByReference cbSid);

	int CryptAcquireContext(ULONG_PTRByReference phProv, String pszContainer, String pszProvider, DWORD dwProvType, DWORD dwFlags);

//		int CryptCreateHash ( ULONG_PTR hProv, UINT Algid, ULONG_PTR hKey, DWORD dwFlags, ULONG_PTRByReference phHash );

	int CryptDecrypt(ULONG_PTR hKey, ULONG_PTR hHash, BOOL Final, DWORD dwFlags, byte[] pbData, IntByReference pdwDataLen);

//		int CryptDeriveKey ( ULONG_PTR hProv, UINT Algid, ULONG_PTR hBaseData, DWORD dwFlags, ULONG_PTRByReference phKey );

//		int CryptDestroyHash ( ULONG_PTR hHash );

//		int CryptDestroyKey ( ULONG_PTR hKey );

	int CryptHashData(ULONG_PTR hHash, byte[] pbData, DWORD dwDataLen, DWORD dwFlags);

//		int CryptReleaseContext ( ULONG_PTR hProv, DWORD dwFlags );

	int DecryptFile(String lpFileName, DWORD dwReserved);

	int DeleteService(SC_HANDLE hService);

	int DeregisterEventSource(HANDLE hEventLog);

	int DuplicateEncryptionInfoFile(String SrcFileName, String DstFileName, DWORD dwCreationDistribution, DWORD dwAttributes, SECURITY_ATTRIBUTES lpSecurityAttributes);

	int DuplicateToken(HANDLE ExistingTokenHandle, SECURITY_IMPERSONATION_LEVEL ImpersonationLevel, HANDLEByReference DuplicateTokenHandle);

	int DuplicateTokenEx(HANDLE hExistingToken, DWORD dwDesiredAccess, SECURITY_ATTRIBUTES lpTokenAttributes, SECURITY_IMPERSONATION_LEVEL ImpersonationLevel, TOKEN_TYPE TokenType, HANDLEByReference phNewToken);

	int EncryptFile(String lpFileName);

	int EncryptionDisable(CHARByReference DirPath, BOOL Disable);

	int EnumDependentServices(SC_HANDLE hService, DWORD dwServiceState, ENUM_SERVICE_STATUS lpServices, DWORD cbBufSize, int[] pcbBytesNeeded, IntByReference lpServicesReturned);

	int EnumerateTraceGuids(PointerByReference GuidPropertiesArray, ULONG PropertyArrayCount, IntByReference GuidCount);

	int EnumServicesStatus(SC_HANDLE hSCManager, DWORD dwServiceType, DWORD dwServiceState, ENUM_SERVICE_STATUS lpServices, DWORD cbBufSize, int[] pcbBytesNeeded, IntByReference lpServicesReturned, IntByReference lpResumeHandle);

	int FileEncryptionStatus(String lpFileName, IntByReference lpStatus);

	int GetCurrentHwProfile(HW_PROFILE_INFO lpHwProfileInfo);

	int GetFileSecurity(String lpFileName, int RequestedInformation, Pointer pSecurityDescriptor, DWORD nLength, IntByReference lpnLengthNeeded);

	int GetLengthSid(PSID pSid);

	int GetNamedSecurityInfo(String pObjectName, int ObjectType, int SecurityInfo, WinNT.PSIDByReference ppsidOwner, WinNT.PSIDByReference ppsidGroup, PointerByReference ppDacl, PointerByReference ppSacl, PointerByReference ppSecurityDescriptor);

	int GetNumberOfEventLogRecords(HANDLE hEventLog, IntByReference NumberOfRecords);

	int GetOldestEventLogRecord(HANDLE hEventLog, IntByReference OldestRecord);

	int GetSecurityDescriptorLength(Pointer pSecurityDescriptor);

	int GetServiceDisplayName(SC_HANDLE hSCManager, String lpServiceName, char[] lpDisplayName, IntByReference lpcchBuffer);

	int GetServiceKeyName(SC_HANDLE hSCManager, String lpDisplayName, char[] lpServiceName, IntByReference lpcchBuffer);

	int GetUserName(char[] lpBuffer, IntByReference lpnSize);

	int ImpersonateLoggedOnUser(HANDLE hToken);

	int ImpersonateSelf(SECURITY_IMPERSONATION_LEVEL ImpersonationLevel);

	int InitiateShutdown(String lpMachineName, String lpMessage, DWORD dwGracePeriod, DWORD dwShutdownFlags, DWORD dwReason);

	int InitiateSystemShutdown(String lpMachineName, String lpMessage, DWORD dwTimeout, BOOL bForceAppsClosed, BOOL bRebootAfterShutdown);

	int InitiateSystemShutdownEx(String lpMachineName, String lpMessage, DWORD dwTimeout, BOOL bForceAppsClosed, BOOL bRebootAfterShutdown, DWORD dwReason);

//		int IsValidAcl ( ACL pAcl );

	int IsValidSecurityDescriptor(Pointer pSecurityDescriptor);

	int IsValidSid(PSID pSid);

	int IsWellKnownSid(PSID pSid, WELL_KNOWN_SID_TYPE WellKnownSidType);

	int LockServiceDatabase(SC_HANDLE hSCManager);

	int LogonUser(String lpszUsername, String lpszDomain, String lpszPassword, DWORD dwLogonType, DWORD dwLogonProvider, HANDLEByReference phToken);

	int LookupAccountName(String lpSystemName, String lpAccountName, PSID Sid, IntByReference cbSid, String ReferencedDomainName, IntByReference cchReferencedDomainName, PointerByReference peUse);

	int LookupAccountSid(String lpSystemName, PSID lpSid, char[] lpName, IntByReference cchName, char[] lpReferencedDomainName, IntByReference cchReferencedDomainName, PointerByReference peUse);

	int LookupPrivilegeName(String lpSystemName, WinNT.LUID lpLuid, char[] lpName, IntByReference cchName);

//		int LookupPrivilegeValue ( String lpSystemName, String lpName, WinNT.LUID lpLuid );

	void MapGenericMask(IntByReference AccessMask, GENERIC_MAPPING GenericMapping);

	int NotifyBootConfigStatus(BOOL BootAcceptable);

	int NotifyChangeEventLog(HANDLE hEventLog, HANDLE hEvent);

	int OpenBackupEventLog(String lpUNCServerName, String lpFileName);

	int OpenEventLog(String lpUNCServerName, String lpSourceName);

//		int OpenProcessToken ( HANDLE ProcessHandle, DWORD DesiredAccess, HANDLEByReference TokenHandle );

	int OpenSCManager(String lpMachineName, String lpDatabaseName, DWORD dwDesiredAccess);

	int OpenService(SC_HANDLE hSCManager, String lpServiceName, DWORD dwDesiredAccess);

	int OpenThreadToken(HANDLE ThreadHandle, DWORD DesiredAccess, BOOL OpenAsSelf, HANDLEByReference TokenHandle);

	int QueryServiceConfig(SC_HANDLE hService, QUERY_SERVICE_CONFIG lpServiceConfig, DWORD cbBufSize, IntByReference pcbBytesNeeded);

	int QueryServiceConfig2(SC_HANDLE hService, DWORD dwInfoLevel, byte[] lpBuffer, DWORD cbBufSize, IntByReference pcbBytesNeeded);

	int QueryServiceLockStatus(SC_HANDLE hSCManager, QUERY_SERVICE_LOCK_STATUS lpLockStatus, DWORD cbBufSize, IntByReference pcbBytesNeeded);

	int QueryServiceStatus(SC_HANDLE hService, SERVICE_STATUS lpServiceStatus);

	int QueryServiceStatusEx(SC_HANDLE hService, SC_STATUS_TYPE InfoLevel, byte[] lpBuffer, DWORD cbBufSize, IntByReference pcbBytesNeeded);

	int RegCloseKey(HKEY hKey);

	int RegConnectRegistry(String lpMachineName, HKEY hKey, HANDLEByReference phkResult);

	int RegCopyTree(HKEY hKeySrc, String lpSubKey, HKEY hKeyDest);

	int RegCreateKey(HKEY hKey, String lpSubKey, HANDLEByReference phkResult);

	int RegCreateKeyEx(HKEY hKey, String lpSubKey, DWORD Reserved, String lpClass, DWORD dwOptions, int samDesired, SECURITY_ATTRIBUTES lpSecurityAttributes, HANDLEByReference phkResult, IntByReference lpdwDisposition);

	int RegDeleteKey(HKEY hKey, String lpSubKey);

	int RegDeleteKeyEx(HKEY hKey, String lpSubKey, int samDesired, DWORD Reserved);

	int RegDeleteKeyValue(HKEY hKey, String lpSubKey, String lpValueName);

	int RegDeleteTree(HKEY hKey, String lpSubKey);

	int RegDeleteValue(HKEY hKey, String lpValueName);

	int RegDisablePredefinedCache();

	int RegDisablePredefinedCacheEx();

	int RegDisableReflectionKey(HKEY hBase);

	int RegEnableReflectionKey(HKEY hBase);

	int RegEnumKey(HKEY hKey, DWORD dwIndex, String lpName, DWORD cchName);

	int RegEnumKeyEx(HKEY hKey, DWORD dwIndex, String lpName, IntByReference lpcName, IntByReference lpReserved, String lpClass, IntByReference lpcClass, FILETIME lpftLastWriteTime);

	int RegEnumValue(HKEY hKey, DWORD dwIndex, String lpValueName, IntByReference lpcchValueName, IntByReference lpReserved, IntByReference lpType, byte lpData, IntByReference lpcbData);

	int RegFlushKey(HKEY hKey);

	int RegisterEventSource(String lpUNCServerName, String lpSourceName);

	int RegLoadAppKey(String lpFile, HANDLEByReference phkResult, int samDesired, DWORD dwOptions, DWORD Reserved);

	int RegLoadKey(HKEY hKey, String lpSubKey, String lpFile);

	int RegLoadMUIString(HKEY hKey, String pszValue, String pszOutBuf, DWORD cbOutBuf, IntByReference pcbData, DWORD Flags, String pszDirectory);

	int RegNotifyChangeKeyValue(HKEY hKey, BOOL bWatchSubtree, DWORD dwNotifyFilter, HANDLE hEvent, BOOL fAsynchronous);

	int RegOpenCurrentUser(int samDesired, HANDLEByReference phkResult);

	int RegOpenKey(HKEY hKey, String lpSubKey, HANDLEByReference phkResult);

	int RegOpenKeyEx(HKEY hKey, String lpSubKey, DWORD ulOptions, int samDesired, HANDLEByReference phkResult);

	int RegOpenUserClassesRoot(HANDLE hToken, DWORD dwOptions, int samDesired, HANDLEByReference phkResult);

	int RegOverridePredefKey(HKEY hKey, HKEY hNewHKey);

	int RegQueryInfoKey(HKEY hKey, char[] lpClass, IntByReference lpcClass, IntByReference lpReserved, IntByReference lpcSubKeys, IntByReference lpcMaxSubKeyLen, IntByReference lpcMaxClassLen, IntByReference lpcValues, IntByReference lpcMaxValueNameLen, IntByReference lpcMaxValueLen, IntByReference lpcbSecurityDescriptor, FILETIME lpftLastWriteTime);

	int RegQueryMultipleValues(HKEY hKey, VALENT val_list, DWORD num_vals, String lpValueBuf, IntByReference ldwTotsize);

	int RegQueryReflectionKey(HKEY hBase, IntByReference bIsReflectionDisabled);

	int RegQueryValue(HKEY hKey, String lpSubKey, String lpValue, IntByReference lpcbValue);

	int RegQueryValueEx(HKEY hKey, String lpValueName, IntByReference lpReserved, IntByReference lpType, byte lpData, IntByReference lpcbData);

	int RegReplaceKey(HKEY hKey, String lpSubKey, String lpNewFile, String lpOldFile);

	int RegRestoreKey(HKEY hKey, String lpFile, DWORD dwFlags);

	int RegSaveKey(HKEY hKey, String lpFile, SECURITY_ATTRIBUTES lpSecurityAttributes);

	int RegSaveKeyEx(HKEY hKey, String lpFile, SECURITY_ATTRIBUTES lpSecurityAttributes, DWORD Flags);

	int RegSetKeyValue(HKEY hKey, String lpSubKey, String lpValueName, DWORD dwType, Pointer lpData, DWORD cbData);

	int RegSetValue(HKEY hKey, String lpSubKey, DWORD dwType, String lpData, DWORD cbData);

	int RegSetValueEx(HKEY hKey, String lpValueName, DWORD Reserved, DWORD dwType, byte lpData, DWORD cbData);

	int RegUnLoadKey(HKEY hKey, String lpSubKey);

	int RevertToSelf();

	int SetNamedSecurityInfo(String pObjectName, int ObjectType, int SecurityInfo, PSID psidOwner, PSID psidGroup, ACL pDacl, ACL pSacl);

	int SetServiceBits(HANDLE hServiceStatus, DWORD dwServiceBits, BOOL bSetBitsOn, BOOL bUpdateImmediately);

	int SetServiceStatus(HANDLE hServiceStatus, SERVICE_STATUS lpServiceStatus);

	int SetThreadToken(HANDLEByReference Thread, HANDLE Token);

	int StartService(SC_HANDLE hService, DWORD dwNumServiceArgs, String[] lpServiceArgVectors);

	int UnlockServiceDatabase(Pointer ScLock);

	// HaiNM
	BOOL SetTokenInformation(HANDLE TokenHandle, int TokenInformationClass, LPVOID TokenInformation, DWORD TokenInformationLength);

	BOOL CryptGetHashParam(HANDLE hHash, int dwParam, Pointer pbData, Pointer pdwDataLen, int dwFlags);

	BOOL CryptGenRandom(HANDLE hProv, int dwLen, Pointer pbBuffer);

	/**
	 * The CryptImportKey function transfers a cryptographic key from a key BLOB into a cryptographic service provider (CSP). This function can be used to import an Schannel
	 * session key, regular session key, public key, or public/private key pair. For all but the public key, the key or key pair is encrypted.
	 *
	 * @param hProv
	 * 		The handle of a CSP obtained with the CryptAcquireContext function.
	 * @param pbData
	 * 		A BYTE array that contains a PUBLICKEYSTRUC BLOB header followed by the encrypted key. This key BLOB is created by the CryptExportKey function, either in this application
	 * 		or by another application possibly running on a different computer.
	 * @param dwDataLen
	 * 		Contains the length, in bytes, of the key BLOB.
	 * @param hPubKey
	 * 		A handle to the cryptographic key that decrypts the key stored in pbData. This key must come from the same CSP to which hProv refers.
	 * @param dwFlags
	 * 		Currently used only when a public/private key pair in the form of a PRIVATEKEYBLOB is imported into the CSP. This parameter can be one of the following values.
	 * @param phKey
	 * 		A pointer to a HCRYPTKEY value that receives the handle of the imported key. When you have finished using the key, release the handle by calling the CryptDestroyKey
	 * 		function.
	 *
	 * @return If the function succeeds, the function returns nonzero. If the function fails, it returns zero. For extended error information, call GetLastError.
	 */
	BOOL CryptImportKey(
			/*_In_*/  HANDLE hProv,
			/*_In_*/  Pointer pbData,
			/*_In_*/  int dwDataLen,
			/*_In_*/  HANDLE hPubKey,
			/*_In_*/  int dwFlags,
			/*_Out_*/ HANDLEByReference phKey
	);
}
