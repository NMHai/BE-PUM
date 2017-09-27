/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet
 * File name: WininetDLL.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet;

import java.nio.ByteBuffer;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 */
public interface WininetDLL extends StdCallLibrary {
    WininetDLL INSTANCE = (WininetDLL) Native.loadLibrary("wininet", WininetDLL.class, W32APIOptions.DEFAULT_OPTIONS);
    WininetDLL SYNC_INSTANCE = (WininetDLL) Native.synchronizedLibrary(INSTANCE);

    /**
     * Sets an Internet option.
     *
     * @param hInternet
     *         Handle on which to set information.
     * @param dwOption
     *         Internet option to be set. This can be one of the Option Flags values.
     * @param lpBuffer
     *         Pointer to a buffer that contains the option setting.
     * @param dwBufferLength
     *         Size of the lpBuffer buffer. If lpBuffer contains a string, the size is in TCHARs. If lpBuffer contains
     *         anything other than a string, the size is in bytes.
     *
     * @return Returns TRUE if successful, or FALSE otherwise. To get a specific error message, call GetLastError.
     */
    BOOL InternetSetOption(
    /* _In_ *//* HINTERNET */HANDLE hInternet,
    /* _In_ */int dwOption,
    /* _In_ */Pointer lpBuffer,
    /* _In_ */int dwBufferLength);

    /**
     * Initializes an application's use of the WinINet functions.
     *
     * @param lpszAgent
     *         Pointer to a null-terminated string that specifies the name of the application or entity calling the
     *         WinINet functions. This name is used as the user agent in the HTTP protocol.
     * @param dwAccessType
     *         Type of access required. This parameter can be one of the following values.
     * @param lpszProxyName
     *         Pointer to a null-terminated string that specifies the name of the proxy server(s) to use when proxy
     *         access is specified by setting dwAccessType to INTERNET_OPEN_TYPE_PROXY. Do not use an empty string,
     *         because InternetOpen will use it as the proxy name. The WinINet functions recognize only CERN type
     *         proxies (HTTP only) and the TIS FTP gateway (FTP only). If Microsoft Internet Explorer is installed,
     *         these functions also support SOCKS proxies. FTP requests can be made through a CERN type proxy either by
     *         changing them to an HTTP request or by using InternetOpenUrl. If dwAccessType is not set to
     *         INTERNET_OPEN_TYPE_PROXY, this parameter is ignored and should be NULL. For more information about
     *         listing proxy servers, see the Listing Proxy Servers section of Enabling Internet Functionality.
     * @param lpszProxyBypass
     *         Pointer to a null-terminated string that specifies an optional list of host names or IP addresses, or
     *         both, that should not be routed through the proxy when dwAccessType is set to INTERNET_OPEN_TYPE_PROXY.
     *         The list can contain wildcards. Do not use an empty string, because InternetOpen will use it as the proxy
     *         bypass list. If this parameter specifies the "<local>" macro, the function bypasses the proxy for any
     *         host name that does not contain a period. By default, WinINet will bypass the proxy for requests that use
     *         the host names "localhost", "loopback", "127.0.0.1", or "[::1]". This behavior exists because a remote
     *         proxy server typically will not resolve these addresses properly.
     * @param dwFlags
     *         Options. This parameter can be a combination of the following values.
     *
     * @return Returns a valid handle that the application passes to subsequent WinINet functions. If InternetOpen
     * fails, it returns NULL. To retrieve a specific error message, call GetLastError.
     */
    HANDLE InternetOpen(
    /* _In_ */String lpszAgent,
	/* _In_ */DWORD dwAccessType,
	/* _In_ */String lpszProxyName,
	/* _In_ */String lpszProxyBypass,
	/* _In_ */DWORD dwFlags);

    /**
     * Opens a resource specified by a complete FTP or HTTP URL.
     *
     * @param hInternet
     *         The handle to the current Internet session. The handle must have been returned by a previous call to
     *         InternetOpen.
     * @param lpszUrl
     *         A pointer to a null-terminated string variable that specifies the URL to begin reading. Only URLs
     *         beginning with ftp:, http:, or https: are supported.
     * @param lpszHeaders
     *         A pointer to a null-terminated string that specifies the headers to be sent to the HTTP server. For more
     *         information, see the description of the lpszHeaders parameter in the HttpSendRequest function.
     * @param dwHeadersLength
     *         The size of the additional headers, in TCHARs. If this parameter is -1L and lpszHeaders is not NULL,
     *         lpszHeaders is assumed to be zero-terminated (ASCIIZ) and the length is calculated.
     * @param dwFlags
     *         This parameter can be one of the following values.
     * @param dwContext
     *         A pointer to a variable that specifies the application-defined value that is passed, along with the
     *         returned handle, to any callback functions.
     *
     * @return Returns a valid handle to the URL if the connection is successfully established, or NULL if the
     * connection fails. To retrieve a specific error message, call GetLastError. To determine why access to the service
     * was denied, call InternetGetLastResponseInfo.
     */
    HANDLE InternetOpenUrl(
	/* _In_ */HANDLE hInternet,
	/* _In_ */String lpszUrl,
	/* _In_ */String lpszHeaders,
	/* _In_ */DWORD dwHeadersLength,
	/* _In_ */DWORD dwFlags,
	/* _In_ */DWORD_PTR dwContext);

    /**
     * Reads data from a handle opened by the InternetOpenUrl, FtpOpenFile, or
     * HttpOpenRequest function.
     *
     * @param hFile
     *         Handle returned from a previous call to InternetOpenUrl, FtpOpenFile, or HttpOpenRequest.
     * @param lpBuffer
     *         Pointer to a buffer that receives the data.
     * @param dwNumberOfBytesToRead
     *         Number of bytes to be read.
     * @param lpdwNumberOfBytesRead
     *         Pointer to a variable that receives the number of bytes read. InternetReadFile sets this value to zero
     *         before doing any work or error checking.
     *
     * @return Returns TRUE if successful, or FALSE otherwise. To get extended error information, call GetLastError. An
     * application can also use InternetGetLastResponseInfo when necessary.
     */
    BOOL InternetReadFile(
	/* _In_ */HANDLE hFile,
	/* _Out_ */ByteBuffer lpBuffer,
	/* _In_ */int dwNumberOfBytesToRead,
	/* _Out_ */DWORDByReference lpdwNumberOfBytesRead);

    /**
     * Closes a single Internet handle.
     *
     * @param hInternet
     *         Handle to be closed.
     *
     * @return Returns TRUE if the handle is successfully closed, or FALSE otherwise. To get extended error information,
     * call GetLastError.
     */
    BOOL InternetCloseHandle(
	/* _In_ */HANDLE hInternet);

    /**
     * Opens an File Transfer Protocol (FTP) or HTTP session for a given site.
     *
     * @param hInternet
     *         Handle returned by a previous call to InternetOpen.
     * @param lpszServerName
     *         Pointer to a null-terminated string that specifies the host name of an Internet server. Alternately, the
     *         string can contain the IP number of the site, in ASCII dotted-decimal format (for example, 11.0.1.45).
     * @param nServerPort
     *         Transmission Control Protocol/Internet Protocol (TCP/IP) port on the server. These flags set only the
     *         port that is used. The service is set by the value of dwService. This parameter can be one of the
     *         following values.
     * @param lpszUsername
     *         Pointer to a null-terminated string that specifies the name of the user to log on. If this parameter is
     *         NULL, the function uses an appropriate default. For the FTP protocol, the default is "anonymous".
     * @param lpszPassword
     *         Pointer to a null-terminated string that contains the password to use to log on. If both lpszPassword and
     *         lpszUsername are NULL, the function uses the default "anonymous" password. In the case of FTP, the
     *         default password is the user's email name. If lpszPassword is NULL, but lpszUsername is not NULL, the
     *         function uses a blank password.
     * @param dwService
     *         Type of service to access. This parameter can be one of the following values.
     * @param dwFlags
     *         Options specific to the service used. If dwService is INTERNET_SERVICE_FTP, INTERNET_FLAG_PASSIVE causes
     *         the application to use passive FTP semantics.
     * @param dwContext
     *         Pointer to a variable that contains an application-defined value that is used to identify the application
     *         context for the returned handle in callbacks.
     *
     * @return Returns a valid handle to the session if the connection is successful, or NULL otherwise. To retrieve
     * extended error information, call GetLastError. An application can also use InternetGetLastResponseInfo to
     * determine why access to the service was denied.
     */
    HANDLE InternetConnect(
	/* _In_ */HANDLE hInternet,
	/* _In_ */String lpszServerName,
	/* _In_ */short nServerPort,
	/* _In_ */String lpszUsername,
	/* _In_ */String lpszPassword,
	/* _In_ */int dwService,
	/* _In_ */int dwFlags,
	/* _In_ */DWORD_PTR dwContext);

    /**
     * Retrieves a file from the FTP server and stores it under the specified
     * file name, creating a new local file in the process.
     *
     * @param hConnect
     *         Handle to an FTP session.
     * @param lpszRemoteFile
     *         Pointer to a null-terminated string that contains the name of the file to be retrieved.
     * @param lpszNewFile
     *         Pointer to a null-terminated string that contains the name of the file to be created on the local
     *         system.
     * @param fFailIfExists
     *         Indicates whether the function should proceed if a local file of the specified name already exists. If
     *         fFailIfExists is TRUE and the local file exists, FtpGetFile fails.
     * @param dwFlagsAndAttributes
     *         File attributes for the new file. This parameter can be any combination of the FILE_ATTRIBUTE_* flags
     *         used by the CreateFile function.
     * @param dwFlags
     *         Controls how the function will handle the file download. The first set of flag values indicates the
     *         conditions under which the transfer occurs. These transfer type flags can be used in combination with the
     *         second set of flags that control caching.
     * @param dwContext
     *         Pointer to a variable that contains the application-defined value that associates this search with any
     *         application data. This is used only if the application has already called InternetSetStatusCallback to
     *         set up a status callback function.
     *
     * @return Returns TRUE if successful, or FALSE otherwise. To get a specific error message, call GetLastError.
     */
    boolean FtpGetFile(
	/* _In_ */HANDLE hConnect,
	/* _In_ */String lpszRemoteFile,
	/* _In_ */String lpszNewFile,
	/* _In_ */boolean fFailIfExists,
	/* _In_ */int dwFlagsAndAttributes,
	/* _In_ */int dwFlags,
	/* _In_ */DWORD_PTR dwContext);

    /**
     * Creates an HTTP request handle.
     *
     * @param hConnect
     *         A handle to an HTTP session returned by InternetConnect.
     * @param lpszVerb
     *         A pointer to a null-terminated string that contains the HTTP verb to use in the request. If this
     *         parameter is NULL, the function uses GET as the HTTP verb.
     * @param lpszObjectName
     *         A pointer to a null-terminated string that contains the name of the target object of the specified HTTP
     *         verb. This is generally a file name, an executable module, or a search specifier.
     * @param lpszVersion
     *         A pointer to a null-terminated string that contains the HTTP version to use in the request. Settings in
     *         Internet Explorer will override the value specified in this parameter. If this parameter is NULL, the
     *         function uses an HTTP version of 1.1 or 1.0, depending on the value of the Internet Explorer settings.
     * @param lpszReferer
     *         A pointer to a null-terminated string that specifies the URL of the document from which the URL in the
     *         request (lpszObjectName) was obtained. If this parameter is NULL, no referrer is specified.
     * @param lplpszAcceptTypes
     *         A pointer to a null-terminated array of strings that indicates media types accepted by the client. Here
     *         is an example. PCTSTR rgpszAcceptTypes[] = {_T(text/*), NULL}; Failing to properly terminate the array
     *         with a NULL pointer will cause a crash. If this parameter is NULL, no types are accepted by the client.
     *         Servers generally interpret a lack of accept types to indicate that the client accepts only documents of
     *         type "text/*" (that is, only text documents no pictures or other binary files). For more information and
     *         a list of valid media types, see ftp://ftp.isi.edu/in-notes/iana/assignments/media-types/media- types.
     * @param dwFlags
     *         Internet options. This parameter can be any of the following values.
     * @param dwContext
     *         A pointer to a variable that contains the application-defined value that associates this operation with
     *         any application data.
     *
     * @return Returns an HTTP request handle if successful, or NULL otherwise. To retrieve extended error information,
     * call GetLastError.
     */
    HANDLE HttpOpenRequest(
	/* _In_ */HANDLE hConnect,
	/* _In_ */String lpszVerb,
	/* _In_ */String lpszObjectName,
	/* _In_ */String lpszVersion,
	/* _In_ */String lpszReferer,
	/* _In_ */String[] lplpszAcceptTypes,
	/* _In_ */DWORD dwFlags,
	/* _In_ */DWORD_PTR dwContext);

    /**
     * Adds one or more HTTP request headers to the HTTP request handle.
     *
     * @param hRequest
     *         A handle returned by a call to the HttpOpenRequest function.
     * @param lpszHeaders
     *         A pointer to a string variable containing the headers to append to the request. Each header must be
     *         terminated by a CR/LF (carriage return/line feed) pair.
     * @param dwHeadersLength
     *         The size of lpszHeaders, in TCHARs. If this parameter is -1L, the function assumes that lpszHeaders is
     *         zero-terminated (ASCIIZ), and the length is computed.
     * @param dwModifiers
     *         A set of modifiers that control the semantics of this function. This parameter can be a combination of
     *         the following values.
     *
     * @return Returns TRUE if successful, or FALSE otherwise. To get extended error information, call GetLastError.
     */
    boolean HttpAddRequestHeaders(
	/* _In_ */HANDLE hRequest,
	/* _In_ */String lpszHeaders,
	/* _In_ */int dwHeadersLength,
	/* _In_ */int dwModifiers);

    /**
     * Sends the specified request to the HTTP server, allowing callers to send
     * extra data beyond what is normally passed to HttpSendRequestEx.
     *
     * @param hRequest
     *         A handle returned by a call to the HttpOpenRequest function.
     * @param lpszHeaders
     *         A pointer to a null-terminated string that contains the additional headers to be appended to the request.
     *         This parameter can be NULL if there are no additional headers to be appended.
     * @param dwHeadersLength
     *         The size of the additional headers, in TCHARs. If this parameter is -1L and lpszHeaders is not NULL, the
     *         function assumes that lpszHeaders is zero-terminated (ASCIIZ), and the length is calculated. See Remarks
     *         for specifics.
     * @param lpOptional
     *         A pointer to a buffer containing any optional data to be sent immediately after the request headers. This
     *         parameter is generally used for POST and PUT operations. The optional data can be the resource or
     *         information being posted to the server. This parameter can be NULL if there is no optional data to send.
     * @param dwOptionalLength
     *         The size of the optional data, in bytes. This parameter can be zero if there is no optional data to
     *         send.
     *
     * @return Returns TRUE if successful, or FALSE otherwise. To get extended error information, call GetLastError.
     */
    boolean HttpSendRequest(
	/* _In_ */HANDLE hRequest,
	/* _In_ */String lpszHeaders,
	/* _In_ */int dwHeadersLength,
	/* _In_ */Pointer lpOptional,
	/* _In_ */int dwOptionalLength);

    /**
     * Retrieves header information associated with an HTTP request.
     *
     * @param hRequest
     *         A handle returned by a call to the HttpOpenRequest or InternetOpenUrl function.
     * @param dwInfoLevel
     *         A combination of an attribute to be retrieved and flags that modify the request. For a list of possible
     *         attribute and modifier values, see Query Info Flags.
     * @param lpvBuffer
     *         A pointer to a buffer to receive the requested information. This parameter must not be NULL.
     * @param lpdwBufferLength
     *         A pointer to a variable that contains, on entry, the size in bytes of the buffer pointed to by lpvBuffer.
     *         When the function returns successfully, this variable contains the number of bytes of information written
     *         to the buffer. In the case of a string, the byte count does not include the string's terminating null
     *         character. When the function fails with an extended error code of ERROR_INSUFFICIENT_BUFFER, the variable
     *         pointed to by lpdwBufferLength contains on exit the size, in bytes, of a buffer large enough to receive
     *         the requested information. The calling application can then allocate a buffer of this size or larger, and
     *         call the function again.
     * @param lpdwIndex
     *         A pointer to a zero-based header index used to enumerate multiple headers with the same name. When
     *         calling the function, this parameter is the index of the specified header to return. When the function
     *         returns, this parameter is the index of the next header. If the next index cannot be found,
     *         ERROR_HTTP_HEADER_NOT_FOUND is returned.
     *
     * @return Returns TRUE if successful, or FALSE otherwise. To get extended error information, call GetLastError.
     */
    BOOL HttpQueryInfo(HANDLE hRequest, int dwInfoLevel, Pointer lpvBuffer, Pointer lpdwBufferLength, Pointer lpdwIndex);
    
    // HaiNM
    /*Retrieves the connected state of the local system.
    Parameters

    lpdwFlags [out]
    		Pointer to a variable that receives the connection description. This parameter may return a valid flag even when the function returns FALSE. 
    				This parameter can be one or more of the following values.
    dwReserved [in]
    This parameter is reserved and must be 0.*/		
	BOOL InternetGetConnectedState(Pointer lpdwFlags, int dwReserved);
}
