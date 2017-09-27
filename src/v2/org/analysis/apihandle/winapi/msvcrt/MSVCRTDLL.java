package v2.org.analysis.apihandle.winapi.msvcrt;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.USHORT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.win32.StdCallLibrary;

import v2.org.analysis.apihandle.winapi.structures.Internal._startupinfo;
import v2.org.analysis.apihandle.winapi.structures.Stdio.FILE;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.CONTEXT;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.EXCEPTION_RECORD;

/**
 * 
 * @author Yen Nguyen
 *
 */
public interface MSVCRTDLL extends StdCallLibrary {
	MSVCRTDLL INSTANCE = (MSVCRTDLL) Native.loadLibrary("msvcrt", MSVCRTDLL.class);
	MSVCRTDLL SYNC_INSTANCE = (MSVCRTDLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Resets the floating-point package.
	 */
	void _fpreset();

	/**
	 * Invokes command-line parsing and copies the arguments to main() back
	 * through the passed pointers.
	 * 
	 * @param _Argc
	 *            An integer that contains the number of arguments that follow
	 *            in argv. The argc parameter is always greater than or equal to
	 *            1.
	 * 
	 * @param _Argv
	 *            An array of null-terminated strings representing command-line
	 *            arguments entered by the user of the program. By convention,
	 *            argv[0] is the command with which the program is invoked,
	 *            argv[1] is the first command-line argument, and so on, until
	 *            argv[argc], which is always NULL. The first command-line
	 *            argument is always argv[1] and the last one is argv[argc 1].
	 * 
	 * @param _Env
	 *            An array of strings that represent the variables set in the
	 *            user's environment. This array is terminated by a NULL entry.
	 * 
	 * @param _DoWildCard
	 *            An integer that if set to 1 expands the wildcards in the
	 *            command line arguments, or if set to 0 does nothing.
	 * 
	 * @param _StartInfo
	 *            Other information to be passed to the CRT DLL.
	 * 
	 * @return 0 if successful; a negative value if unsuccessful.
	 */
	int __getmainargs(IntByReference _Argc, Pointer _Argv, Memory _Env, int _DoWildCard, _startupinfo _StartInfo);

	int __wgetmainargs(IntByReference _Argc, Pointer _Argv, Memory _Env, int _DoWildCard, _startupinfo _StartInfo);

	int __p__environ();

	Pointer __p___initenv();

	/**
	 * Set function to be executed on exit The function pointed by func is
	 * automatically called without arguments when the program terminates
	 * normally.
	 * 
	 * If more than one atexit function has been specified by different calls to
	 * this function, they are all executed in reverse order as a stack (i.e.
	 * the last function specified is the first to be executed at exit).
	 * 
	 * A single function can be registered to be executed at exit more than
	 * once.
	 * 
	 * If atexit is called after exit, the call may or may not succeed depending
	 * on the particular system and library implementation (unspecified
	 * behavior).
	 * 
	 * If a function registered with atexit throws an exception for which it
	 * does not provide a handler when called on termination, terminate is
	 * automatically called (C++).
	 * 
	 * Particular library implementations may impose a limit on the number of
	 * functions call that can be registered with atexit, but this cannot be
	 * less than 32 function calls.
	 * 
	 * @param func
	 *            Function to be called. The function shall return no value and
	 *            take no arguments.
	 * 
	 * @return A zero value is returned if the function was successfully
	 *         registered. If it failed, a non-zero value is returned.
	 */
	int atexit(/* void (*func)(void) */Pointer func);

	/**
	 * 
	 * Internal methods that walk a table of function pointers and initialize
	 * them. The first pointer is the starting location in the table and the
	 * second pointer is the ending location.
	 * 
	 * @param param1
	 *            param2
	 * 
	 * @return A zero value is returned if the function was successfully
	 *         registered. If it failed, a non-zero value is returned.
	 */
	int _initterm(/* void (*func)(void) */Pointer param1, Pointer param2);

	int _initterm_e(/* void (*func)(void) */Pointer param1, Pointer param2);

	/**
	 * Fill block of memory
	 * 
	 * Sets the first num bytes of the block of memory pointed by ptr to the
	 * specified value (interpreted as an unsigned char).
	 * 
	 * @param ptr
	 *            Pointer to the block of memory to fill.
	 * 
	 * @param value
	 *            Value to be set. The value is passed as an int, but the
	 *            function fills the block of memory using the unsigned char
	 *            conversion of this value.
	 * 
	 * @param num
	 *            Number of bytes to be set to the value. size_t is an unsigned
	 *            integral type.
	 * 
	 * @return ptr is returned.
	 */
	Pointer memset(byte[] ptr, byte value, SIZE_T num);

	/**
	 * Locate substring
	 * 
	 * Returns a pointer to the first occurrence of str2 in str1, or a null
	 * pointer if str2 is not part of str1.
	 * 
	 * The matching process does not include the terminating null-characters,
	 * but it stops there.
	 * 
	 * @param str1
	 *            C string to be scanned.
	 * 
	 * @param str2
	 *            C string containing the sequence of characters to match.
	 * 
	 * @return A pointer to the first occurrence in str1 of the entire sequence
	 *         of characters specified in str2, or a null pointer if the
	 *         sequence is not present in str1.
	 */
	Pointer strstr(char[] str1, char[] str2);

	/**
	 * Get current time
	 * 
	 * Get the current calendar time as a value of type time_t.
	 * 
	 * The function returns this value, and if the argument is not a null
	 * pointer, it also sets this value to the object pointed by timer.
	 * 
	 * The value returned generally represents the number of seconds since 00:00
	 * hours, Jan 1, 1970 UTC (i.e., the current unix timestamp). Although
	 * libraries may use a different representation of time: Portable programs
	 * should not use the value returned by this function directly, but always
	 * rely on calls to other elements of the standard library to translate them
	 * to portable types (such as localtime, gmtime or difftime).
	 * 
	 * @param timer
	 *            Pointer to an object of type time_t, where the time value is
	 *            stored. Alternatively, this parameter can be a null pointer,
	 *            in which case the parameter is not used (the function still
	 *            returns a value of type time_t with the result).
	 * 
	 * @return The current calendar time as a time_t object.
	 * 
	 *         If the argument is not a null pointer, the return value is the
	 *         same as the one stored in the location pointed by argument timer.
	 * 
	 *         If the function could not retrieve the calendar time, it returns
	 *         a value of -1.
	 * 
	 *         time_t is an alias of a fundamental arithmetic type capable of
	 *         representing times.
	 */
	long time(LongByReference timer);

	/**
	 * Initialize random number generator
	 * 
	 * The pseudo-random number generator is initialized using the argument
	 * passed as seed.
	 * 
	 * For every different seed value used in a call to srand, the pseudo-random
	 * number generator can be expected to generate a different succession of
	 * results in the subsequent calls to rand.
	 * 
	 * Two different initializations with the same seed will generate the same
	 * succession of results in subsequent calls to rand.
	 * 
	 * If seed is set to 1, the generator is reinitialized to its initial value
	 * and produces the same values as before any call to rand or srand.
	 * 
	 * In order to generate random-like numbers, srand is usually initialized to
	 * some distinctive runtime value, like the value returned by function time
	 * (declared in header <ctime>). This is distinctive enough for most trivial
	 * randomization needs.
	 * 
	 * @param seed
	 *            An integer value to be used as seed by the pseudo-random
	 *            number generator algorithm.
	 */
	int srand(int seed);

	/**
	 * Sets the current application type.
	 * 
	 * @param at
	 *            A value that indicates the application type. The possible
	 *            values are:
	 * 
	 *            _UNKNOWN_APP Unknown application type.
	 * 
	 *            _CONSOLE_APP Console (command-line) application.
	 * 
	 *            _GUI_APP GUI (Windows) application.
	 */
	void __set_app_type(int at);

	/**
	 * Points to the _fmode global variable, which specifies the default file
	 * translation mode for file I/O operations.
	 * 
	 * @return Pointer to the _fmode global variable.
	 */
	IntByReference __p__fmode();

	/**
	 * Sets the file translation mode.
	 * 
	 * @param fd
	 *            File descriptor.
	 * 
	 * @param mode
	 *            New translation mode.
	 * 
	 * @return If successful, returns the previous translation mode. If invalid
	 *         parameters are passed to this function, the invalid-parameter
	 *         handler is invoked, as described in Parameter Validation. If
	 *         execution is allowed to continue, this function returns 1 and
	 *         sets errno to either EBADF, which indicates an invalid file
	 *         descriptor, or EINVAL, which indicates an invalid mode argument.
	 */
	int _setmode(int fd, int mode);

	/**
	 * Allocate memory block
	 * 
	 * Allocates a block of size bytes of memory, returning a pointer to the
	 * beginning of the block.
	 * 
	 * The content of the newly allocated block of memory is not initialized,
	 * remaining with indeterminate values.
	 * 
	 * If size is zero, the return value depends on the particular library
	 * implementation (it may or may not be a null pointer), but the returned
	 * pointer shall not be dereferenced.
	 * 
	 * @param size
	 *            Size of the memory block, in bytes. size_t is an unsigned
	 *            integral type.
	 * 
	 * @return On success, a pointer to the memory block allocated by the
	 *         function. The type of this pointer is always void*, which can be
	 *         cast to the desired type of data pointer in order to be
	 *         dereferenceable. If the function failed to allocate the requested
	 *         block of memory, a null pointer is returned.
	 */
	Pointer malloc(SIZE_T size);

	/**
	 * Performs cleanup operations and returns without terminating the process.
	 */
	void _cexit();

	/**
	 * Points to the _commode global variable, which specifies the default file
	 * commit mode for file I/O operations.
	 * 
	 * @return Pointer to the _commode global variable.
	 */
	IntByReference __p__commode();

	/**
	 * Gets and sets the floating-point control word. A more secure version of
	 * _controlfp is available; see _controlfp_s.
	 * 
	 * @param nnew
	 *            New control-word bit values.
	 * 
	 * @param mask
	 *            Mask for new control-word bits to set.
	 * 
	 * @return For _control87 and _controlfp, the bits in the value returned
	 *         indicate the floating-point control state. For a complete
	 *         definition of the bits that are returned by _control87, see
	 *         FLOAT.H.
	 */
	UINT _controlfp(UINT nnew, UINT mask);

	/**
	 * Converts an integer to a string. More secure versions of these functions
	 * are available
	 * 
	 * @param value
	 *            Number to be converted.
	 * 
	 * @param str
	 *            String result.
	 * 
	 * @param radix
	 *            Base of value; which must be in the range 236.
	 * 
	 * @return Each of these functions returns a pointer to str. There is no
	 *         error return.
	 */
	WString _itow(int value, WString str, int radix);

	/**
	 * Converts an integer to a string. More secure versions of these functions
	 * are available
	 * 
	 * @param value
	 *            Number to be converted.
	 * 
	 * @param str
	 *            String result.
	 * 
	 * @param radix
	 *            Base of value; which must be in the range 2�36.
	 * 
	 * @return Each of these functions returns a pointer to str. There is no
	 *         error return.
	 */
	String _itoa(int value, String str, int radix);

	/**
	 * Internal CRT function. Used by a framework to find the appropriate
	 * exception handler to process the current exception..
	 * 
	 * @param exception_record
	 *            [in] Information about the specific exception.
	 * 
	 * @param registration
	 *            [in] The record that indicates which scope table should be
	 *            used to find the exception handler.
	 * 
	 * @param context
	 *            [in] Reserved.
	 * 
	 * @param dispatcher
	 *            [in] Reserved.
	 * 
	 * @return If an exception should be dismissed, returns DISPOSITION_DISMISS.
	 *         If the exception should be passed up a level to the encapsulating
	 *         exception handlers, returns DISPOSITION_CONTINUE_SEARCH.
	 */
	int _except_handler3(EXCEPTION_RECORD exception_record, /* PEXCEPTION_REGISTRATION */Pointer registration,
			CONTEXT context, /* PEXCEPTION_REGISTRATION */Pointer dispatcher);

	/**
	 * Copy block of memory
	 * 
	 * Copies the values of num bytes from the location pointed to by source
	 * directly to the memory block pointed to by destination.
	 * 
	 * The underlying type of the objects pointed to by both the source and
	 * destination pointers are irrelevant for this function; The result is a
	 * binary copy of the data.
	 * 
	 * The function does not check for any terminating null character in source
	 * - it always copies exactly num bytes.
	 * 
	 * To avoid overflows, the size of the arrays pointed to by both the
	 * destination and source parameters, shall be at least num bytes, and
	 * should not overlap (for overlapping memory blocks, memmove is a safer
	 * approach).
	 * 
	 * @param destination
	 *            Pointer to the destination array where the content is to be
	 *            copied, type-casted to a pointer of type void*.
	 * 
	 * @param source
	 *            Pointer to the source of data to be copied, type-casted to a
	 *            pointer of type const void*.
	 * 
	 * @param num
	 *            Number of bytes to copy. size_t is an unsigned integral type.
	 *
	 * @return destination is returned.
	 */
	Pointer memcpy(Pointer destination, Pointer source, SIZE_T num);

	/**
	 * Converts a long integer to a string. More secure versions of these
	 * functions are available
	 * 
	 * @param value
	 *            Number to be converted.
	 * 
	 * @param str
	 *            String result.
	 * 
	 * @param radix
	 *            Base of value.
	 * 
	 * @return Each of these functions returns a pointer to str. There is no
	 *         error return.
	 */
	Pointer _ltoa(long value, char[] str, int radix);

	/**
	 * Performs a case-insensitive comparison of strings.
	 * 
	 * @param _Str1
	 *            Null-terminated strings to compare.
	 * 
	 * @param _Str2
	 *            Null-terminated strings to compare.
	 * 
	 * @return The return value indicates the relation of string1 to string2 as
	 *         follows.
	 * 
	 *         < 0 string1 less than string2
	 * 
	 *         0 string1 identical to string2
	 * 
	 *         > 0 string1 greater than string2
	 */
	int _strcmpi(String _Str1, String _Str2);

	/**
	 * Check if character is alphabetic
	 * 
	 * Checks whether c is an alphabetic letter.
	 * 
	 * Notice that what is considered a letter depends on the locale being used;
	 * In the default "C" locale, what constitutes a letter is only what returns
	 * true by either isupper or islower.
	 * 
	 * @param c
	 *            Character to be checked, casted to an int, or EOF.
	 * 
	 * @return A value different from zero (i.e., true) if indeed c is an
	 *         alphabetic letter. Zero (i.e., false) otherwise.
	 */
	int isalpha(int c);

	/**
	 * 
	 * @param _C
	 * @param _Type
	 * @return
	 */
	int is_wctype(/* _In_ */USHORT _C, /* _In_ */USHORT _Type);

	/**
	 * Open file
	 * 
	 * Opens the file whose name is specified in the parameter filename and
	 * associates it with a stream that can be identified in future operations
	 * by the FILE pointer returned.
	 * 
	 * The operations that are allowed on the stream and how these are performed
	 * are defined by the mode parameter.
	 * 
	 * The returned stream is fully buffered by default if it is known to not
	 * refer to an interactive device (see setbuf).
	 * 
	 * The returned pointer can be disassociated from the file by calling fclose
	 * or freopen. All opened files are automatically closed on normal program
	 * termination.
	 * 
	 * The running environment supports at least FOPEN_MAX files open
	 * simultaneously.
	 * 
	 * @param filename
	 *            Open file Opens the file whose name is specified in the
	 *            parameter filename and associates it with a stream that can be
	 *            identified in future operations by the FILE pointer returned.
	 * 
	 *            The operations that are allowed on the stream and how these
	 *            are performed are defined by the mode parameter.
	 * 
	 *            The returned stream is fully buffered by default if it is
	 *            known to not refer to an interactive device (see setbuf).
	 * 
	 *            The returned pointer can be disassociated from the file by
	 *            calling fclose or freopen. All opened files are automatically
	 *            closed on normal program termination.
	 * 
	 *            The running environment supports at least FOPEN_MAX files open
	 *            simultaneously.
	 * 
	 * @param mode
	 *            C string containing a file access mode. It can be:
	 * 
	 *            "r" read: Open file for input operations. The file must exist.
	 * 
	 *            "w" write: Create an empty file for output operations. If a
	 *            file with the same name already exists, its contents are
	 *            discarded and the file is treated as a new empty file.
	 * 
	 *            "a" append: Open file for output at the end of a file. Output
	 *            operations always write data at the end of the file, expanding
	 *            it. Repositioning operations (fseek, fsetpos, rewind) are
	 *            ignored. The file is created if it does not exist.
	 * 
	 *            "r+" read/update: Open a file for update (both for input and
	 *            output). The file must exist.
	 * 
	 *            "w+" write/update: Create an empty file and open it for update
	 *            (both for input and output). If a file with the same name
	 *            already exists its contents are discarded and the file is
	 *            treated as a new empty file.
	 * 
	 *            "a+" append/update: Open a file for update (both for input and
	 *            output) with all output operations writing data at the end of
	 *            the file. Repositioning operations (fseek, fsetpos, rewind)
	 *            affects the next input operations, but output operations move
	 *            the position back to the end of file. The file is created if
	 *            it does not exist.
	 * 
	 * @return If the file is successfully opened, the function returns a
	 *         pointer to a FILE object that can be used to identify the stream
	 *         on future operations. Otherwise, a null pointer is returned. On
	 *         most library implementations, the errno variable is also set to a
	 *         system-specific error code on failure.
	 */
	FILE fopen(String filename, String mode);

	int fopen_s(Pointer pFile, String filename, String mode);

	/**
	 * Read block of data from stream
	 * 
	 * Reads an array of count elements, each one with a size of size bytes,
	 * from the stream and stores them in the block of memory specified by ptr.
	 * 
	 * The position indicator of the stream is advanced by the total amount of
	 * bytes read.
	 * 
	 * The total amount of bytes read if successful is (size*count).
	 * 
	 * @param ptr
	 *            Pointer to a block of memory with a size of at least
	 *            (size*count) bytes, converted to a void*.
	 * 
	 * @param size
	 *            Size, in bytes, of each element to be read. size_t is an
	 *            unsigned integral type.
	 * 
	 * @param count
	 *            Number of elements, each one with a size of size bytes. size_t
	 *            is an unsigned integral type.
	 * 
	 * @param stream
	 *            Pointer to a FILE object that specifies an input stream.
	 * 
	 * @return The total number of elements successfully read is returned. If
	 *         this number differs from the count parameter, either a reading
	 *         error occurred or the end-of-file was reached while reading. In
	 *         both cases, the proper indicator is set, which can be checked
	 *         with ferror and feof, respectively. If either size or count is
	 *         zero, the function returns zero and both the stream state and the
	 *         content pointed by ptr remain unchanged. size_t is an unsigned
	 *         integral type.
	 */
	UINT fread(Buffer ptr, UINT size, UINT count, FILE stream);

	/**
	 * Write block of data to stream
	 * 
	 * Writes an array of count elements, each one with a size of size bytes,
	 * from the block of memory pointed by ptr to the current position in the
	 * stream.
	 * 
	 * The position indicator of the stream is advanced by the total number of
	 * bytes written.
	 * 
	 * Internally, the function interprets the block pointed by ptr as if it was
	 * an array of (size*count) elements of type unsigned char, and writes them
	 * sequentially to stream as if fputc was called for each byte.
	 * 
	 * @param ptr
	 *            Pointer to the array of elements to be written, converted to a
	 *            const void*.
	 * 
	 * @param size
	 *            Size in bytes of each element to be written. size_t is an
	 *            unsigned integral type.
	 * 
	 * @param count
	 *            Number of elements, each one with a size of size bytes. size_t
	 *            is an unsigned integral type.
	 * 
	 * @param stream
	 *            Pointer to a FILE object that specifies an output stream.
	 * 
	 * @return The total number of elements successfully written is returned. If
	 *         this number differs from the count parameter, a writing error
	 *         prevented the function from completing. In this case, the error
	 *         indicator (ferror) will be set for the stream. If either size or
	 *         count is zero, the function returns zero and the error indicator
	 *         remains unchanged. size_t is an unsigned integral type.
	 */
	SIZE_T fwrite(byte[] ptr, SIZE_T size, SIZE_T count, FILE stream);

	void system(String command);

	int fputs(String content, FILE fp);

	String fgets(Memory memory, int size, FILE fp);

	/**
	 * Deallocate memory block
	 * 
	 * A block of memory previously allocated by a call to malloc, calloc or
	 * realloc is deallocated, making it available again for further
	 * allocations.
	 * 
	 * If ptr does not point to a block of memory allocated with the above
	 * functions, it causes undefined behavior.
	 * 
	 * If ptr is a null pointer, the function does nothing.
	 * 
	 * Notice that this function does not change the value of ptr itself, hence
	 * it still points to the same (now invalid) location.
	 * 
	 * @param ptr
	 *            Pointer to a memory block previously allocated with malloc,
	 *            calloc or realloc.
	 */
	void free(Pointer ptr);

	/**
	 * Remove file
	 * 
	 * Deletes the file whose name is specified in filename.
	 * 
	 * This is an operation performed directly on a file identified by its
	 * filename; No streams are involved in the operation.
	 * 
	 * Proper file access shall be available.
	 * 
	 * @param filename
	 *            C string containing the name of the file to be deleted. Its
	 *            value shall follow the file name specifications of the running
	 *            environment and can include a path (if supported by the
	 *            system).
	 * 
	 * @return If the file is successfully deleted, a zero value is returned. On
	 *         failure, a nonzero value is returned. On most library
	 *         implementations, the errno variable is also set to a
	 *         system-specific error code on failure.
	 */
	int remove(String filename);

	/**
	 * Compare characters of two strings
	 * 
	 * Compares up to num characters of the C string str1 to those of the C
	 * string str2. This function starts comparing the first character of each
	 * string. If they are equal to each other, it continues with the following
	 * pairs until the characters differ, until a terminating null-character is
	 * reached, or until num characters match in both strings, whichever happens
	 * first.
	 * 
	 * @param str1
	 *            C string to be compared.
	 * 
	 * @param str2
	 *            C string to be compared.
	 * 
	 * @param num
	 *            Maximum number of characters to compare. size_t is an unsigned
	 *            integral type.
	 * 
	 * @return Returns an integral value indicating the relationship between the
	 *         strings:
	 * 
	 *         return value indicates
	 * 
	 *         <0 the first character that does not match has a lower value in
	 *         str1 than in str2
	 * 
	 *         0 the contents of both strings are equal
	 * 
	 *         >0 the first character that does not match has a greater value in
	 *         str1 than in str2
	 */
	int strncmp(String str1, String str2, int num);

	/**
	 * Close file
	 * 
	 * Closes the file associated with the stream and disassociates it.
	 * 
	 * All internal buffers associated with the stream are disassociated from it
	 * and flushed: the content of any unwritten output buffer is written and
	 * the content of any unread input buffer is discarded.
	 * 
	 * Even if the call fails, the stream passed as parameter will no longer be
	 * associated with the file nor its buffers.
	 * 
	 * @param stream
	 *            Pointer to a FILE object that specifies the stream to be
	 *            closed.
	 * 
	 * @return If the stream is successfully closed, a zero value is returned.
	 *         On failure, EOF is returned.
	 */
	int fclose(FILE stream);

	/**
	 * Write formatted data to stream
	 * 
	 * Writes the C string pointed by format to the stream. If format includes
	 * format specifiers (subsequences beginning with %), the additional
	 * arguments following format are formatted and inserted in the resulting
	 * string replacing their respective specifiers.
	 * 
	 * After the format parameter, the function expects at least as many
	 * additional arguments as specified by format.
	 * 
	 * @param stream
	 *            Pointer to a FILE object that identifies an output stream.
	 * 
	 * @param format
	 *            C string that contains the text to be written to the stream.
	 *            It can optionally contain embedded format specifiers that are
	 *            replaced by the values specified in subsequent additional
	 *            arguments and formatted as requested.
	 * 
	 * @return On success, the total number of characters written is returned.
	 * 
	 *         If a writing error occurs, the error indicator (ferror) is set
	 *         and a negative number is returned.
	 * 
	 *         If a multibyte character encoding error occurs while writing wide
	 *         characters, errno is set to EILSEQ and a negative number is
	 *         returned.
	 */
	int fprintf(FILE stream, String... format);

	/**
	 * Allocate and zero-initialize array
	 * 
	 * Allocates a block of memory for an array of num elements, each of them
	 * size bytes long, and initializes all its bits to zero.
	 * 
	 * The effective result is the allocation of a zero-initialized memory block
	 * of (num*size) bytes.
	 * 
	 * If size is zero, the return value depends on the particular library
	 * implementation (it may or may not be a null pointer), but the returned
	 * pointer shall not be dereferenced.
	 * 
	 * @param num
	 *            Number of elements to allocate.
	 * 
	 * @param size
	 *            Size of each element.
	 * 
	 * @return On success, a pointer to the memory block allocated by the
	 *         function. The type of this pointer is always void*, which can be
	 *         cast to the desired type of data pointer in order to be
	 *         dereferenceable. If the function failed to allocate the requested
	 *         block of memory, a null pointer is returned.
	 */
	Pointer calloc(int num, int size);

	int sprintf(char[] str, String... format);

	/**
	 * Write string to stdout
	 * 
	 * Writes the C string pointed by str to the standard output (stdout) and
	 * appends a newline character ('\n').
	 * 
	 * The function begins copying from the address specified (str) until it
	 * reaches the terminating null character ('\0'). This terminating
	 * null-character is not copied to the stream.
	 * 
	 * Notice that puts not only differs from fputs in that it uses stdout as
	 * destination, but it also appends a newline character at the end
	 * automatically (which fputs does not).
	 * 
	 * @param str
	 *            C string to be printed.
	 * 
	 * @return On success, a non-negative value is returned. On error, the
	 *         function returns EOF and sets the error indicator (ferror).
	 */
	int puts(String str);

	/**
	 * 
	 * @param expression
	 */
	void _assert(int expression);

	/**
	 * Get wide string length
	 * 
	 * Returns the length of the C wide string wcs.
	 * 
	 * This is the number of wide characters between wcs and the first null wide
	 * character (without including it).
	 * 
	 * This is the wide character equivalent of strlen (<cstring>).
	 * 
	 * @param wcs
	 *            C wide string.
	 * 
	 * @return The length of C wide string.
	 */
	SIZE_T wcslen(Pointer wcs);

	/**
	 * Generate random number
	 * 
	 * Returns a pseudo-random integral number in the range between 0 and
	 * RAND_MAX.
	 * 
	 * This number is generated by an algorithm that returns a sequence of
	 * apparently non-related numbers each time it is called. This algorithm
	 * uses a seed to generate the series, which should be initialized to some
	 * distinctive value using function srand.
	 * 
	 * @return An integer value between 0 and RAND_MAX.
	 */
	int rand();

	/**
	 * Concatenate wide strings
	 * 
	 * Appends a copy of the source wide string to the destination wide string.
	 * The terminating null wide character in destination is overwritten by the
	 * first character of source, and a null wide character is included at the
	 * end of the new string formed by the concatenation of both in destination.
	 * 
	 * destination and source shall not overlap.
	 * 
	 * This is the wide character equivalent of strcat (<cstring>).
	 * 
	 * @param destination
	 *            Pointer to the destination array, which should contain a C
	 *            wide string, and be large enough to contain the concatenated
	 *            resulting string.
	 * 
	 * @param source
	 *            C wide string to be appended. This should not overlap
	 *            destination.
	 * 
	 * @return destination is returned.
	 */
	Pointer wcscat(Pointer destination, Pointer source);

	/**
	 * Get string length
	 * 
	 * Returns the length of the C string str.
	 * 
	 * The length of a C string is determined by the terminating null-character:
	 * A C string is as long as the number of characters between the beginning
	 * of the string and the terminating null character (without including the
	 * terminating null character itself).
	 * 
	 * @param str
	 *            C string.
	 * 
	 * @return The length of string.
	 */
	SIZE_T strlen(Pointer str);

	Pointer __p___argc();

	/**
	 * Copy string
	 * 
	 * Copies the C string pointed by source into the array pointed by
	 * destination, including the terminating null character (and stopping at
	 * that point).
	 * 
	 * To avoid overflows, the size of the array pointed by destination shall be
	 * long enough to contain the same C string as source (including the
	 * terminating null character), and should not overlap in memory with
	 * source.
	 * 
	 * @param destination
	 *            Pointer to the destination array where the content is to be
	 *            copied.
	 * 
	 * @param source
	 *            C string to be copied.
	 * 
	 * @return destination is returned.
	 */
	Pointer strcpy(Pointer destination, Pointer source);
	// HaiNM
	Pointer _strdup(Pointer source);

	int _open(String filename, int oflag);

	int _filelength(int handle);

	int _read(int handle, ByteBuffer ptr, int count);

	int _close(int handle);

	int _unlink(String filename);

	long _lseek(int handle, long offset, int origin);
}
