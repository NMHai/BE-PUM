/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: AdjustTokenPrivileges.java
 * Created date: Mar 4, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.LUID;
import com.sun.jna.platform.win32.WinNT.LUID_AND_ATTRIBUTES;
import com.sun.jna.platform.win32.WinNT.TOKEN_PRIVILEGES;

/**
 * The AdjustTokenPrivileges function enables or disables privileges in the
 * specified access token. Enabling or disabling privileges in an access token
 * requires TOKEN_ADJUST_PRIVILEGES access.
 * 
 * @param TokenHandle
 *            A handle to the access token that contains the privileges to be
 *            modified. The handle must have TOKEN_ADJUST_PRIVILEGES access to
 *            the token. If the PreviousState parameter is not NULL, the handle
 *            must also have TOKEN_QUERY access.
 * 
 * @param DisableAllPrivileges
 *            Specifies whether the function disables all of the token's
 *            privileges. If this value is TRUE, the function disables all
 *            privileges and ignores the NewState parameter. If it is FALSE, the
 *            function modifies privileges based on the information pointed to
 *            by the NewState parameter.
 * 
 * @param NewState
 *            A pointer to a TOKEN_PRIVILEGES structure that specifies an array
 *            of privileges and their attributes. If the DisableAllPrivileges
 *            parameter is FALSE, the AdjustTokenPrivileges function enables,
 *            disables, or removes these privileges for the token. The following
 *            table describes the action taken by the AdjustTokenPrivileges
 *            function, based on the privilege attribute.
 * 
 * @param BufferLength
 *            Specifies the size, in bytes, of the buffer pointed to by the
 *            PreviousState parameter. This parameter can be zero if the
 *            PreviousState parameter is NULL.
 * 
 * @param PreviousState
 *            A pointer to a buffer that the function fills with a
 *            TOKEN_PRIVILEGES structure that contains the previous state of any
 *            privileges that the function modifies. That is, if a privilege has
 *            been modified by this function, the privilege and its previous
 *            state are contained in the TOKEN_PRIVILEGES structure referenced
 *            by PreviousState. If the PrivilegeCount member of TOKEN_PRIVILEGES
 *            is zero, then no privileges have been changed by this function.
 *            This parameter can be NULL. If you specify a buffer that is too
 *            small to receive the complete list of modified privileges, the
 *            function fails and does not adjust any privileges. In this case,
 *            the function sets the variable pointed to by the ReturnLength
 *            parameter to the number of bytes required to hold the complete
 *            list of modified privileges.
 * 
 * @param ReturnLength
 *            A pointer to a variable that receives the required size, in bytes,
 *            of the buffer pointed to by the PreviousState parameter. This
 *            parameter can be NULL if PreviousState is NULL.
 * 
 * @return If the function succeeds, the return value is nonzero. To determine
 *         whether the function adjusted all of the specified privileges, call
 *         GetLastError, which returns one of the following values when the
 *         function succeeds:
 * 
 * @author Yen Nguyen
 *
 */
public class AdjustTokenPrivileges extends Advapi32API {

	public AdjustTokenPrivileges() {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);

		HANDLE TokenHandle = new HANDLE(new Pointer(t1));
		BOOL DisableAllPrivileges = new BOOL(t2);
		TOKEN_PRIVILEGES NewState = null;
		DWORD BufferLength = new DWORD(t4);
		TOKEN_PRIVILEGES PreviousState = (t5 == 0L) ? null : new TOKEN_PRIVILEGES();
		DWORDByReference ReturnLength = (t6 == 0L) ? null : new DWORDByReference();

		if (t3 != 0L) {
			int PrivilegeCount = (int) ((LongValue) memory.getDoubleWordMemoryValue(t3)).getValue();
			NewState = new TOKEN_PRIVILEGES(PrivilegeCount);

			int offset = 4;
			for (int i = 0; i < PrivilegeCount; i++) {
				LUID_AND_ATTRIBUTES attributes = new LUID_AND_ATTRIBUTES();

				long LowPart = ((LongValue) memory.getDoubleWordMemoryValue(t3 + offset)).getValue();
				offset += 4;
				long HighPart = ((LongValue) memory.getDoubleWordMemoryValue(t3 + offset)).getValue();
				offset += 4;

				attributes.Luid = new LUID();
				attributes.Luid.LowPart = (int) LowPart;
				attributes.Luid.HighPart = (int) HighPart;

				long Attributes = ((LongValue) memory.getDoubleWordMemoryValue(t3 + offset)).getValue();
				offset += 4;
				attributes.Attributes = new DWORD(Attributes);

				NewState.Privileges[i] = attributes;
			}
		}

		BOOL ret = Advapi32DLL.INSTANCE.AdjustTokenPrivileges(TokenHandle, DisableAllPrivileges, NewState,
				BufferLength, PreviousState, ReturnLength);

		register.mov("eax", new LongValue(ret.longValue()));

		if (PreviousState != null) {
			int offset = 0;

			int PrivilegeCount = PreviousState.PrivilegeCount.intValue();
			memory.setDoubleWordMemoryValue(t5 + offset, new LongValue(PrivilegeCount));
			offset += 4;

			for (int i = 0; i < PrivilegeCount; i++) {
				LUID_AND_ATTRIBUTES attributes = PreviousState.Privileges[i];

				memory.setDoubleWordMemoryValue(t5 + offset, new LongValue(attributes.Luid.LowPart));
				offset += 4;
				memory.setDoubleWordMemoryValue(t5 + offset, new LongValue(attributes.Luid.HighPart));
				offset += 4;
				memory.setDoubleWordMemoryValue(t5 + offset, new LongValue(attributes.Attributes.longValue()));
				offset += 4;
			}
		}

		if (ReturnLength != null) {
			memory.setDoubleWordMemoryValue(t6, new LongValue(ReturnLength.getValue().longValue()));
		}
	}
}
