/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi
 * File name: Test.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.winapi;

import org.apache.log4j.Logger;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.RTL_CRITICAL_SECTION;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.ATOM;
import com.sun.jna.platform.win32.WinDef.LONG;

//import com.sun.jna.platform.win32.WinBase.STARTUPINFO;

/**
 * @author SEGFRY
 *
 */
public class Test {

	private static final Logger LOGGER = Logger.getLogger(Test.class);

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

//		LPVOID lpAddress = new LPVOID(0);
//		SIZE_T dwSize = new SIZE_T(0x400000L);
//		DWORD flAllocationType = new DWORD(0x2000L);
//		DWORD flProtect = new DWORD(0x1);
//		LPVOID virtualAlloc = Kernel32DLL.INSTANCE.VirtualAlloc(lpAddress, dwSize, flAllocationType, flProtect);
//
//		System.out.println(virtualAlloc);
//		System.out.println(Kernel32.INSTANCE.GetLastError());
		
//		MSVCRTDLL.INSTANCE._assert(1);
		
		char[] buffer = new char[255];
		int atom = Kernel32DLL.INSTANCE.AddAtom("Hello");
		Kernel32DLL.INSTANCE.GetAtomName(new ATOM(atom), buffer, 255);
		System.out.println(new String(buffer));
		
		System.out.println("OUT");
	}

	// static long funcOpen() {
	// FILE f =
	// MSVCRTDLL.INSTANCE.fopen("C:\\Workspace\\BE-PUMv2\\Storage\\C\\WINDOWS\\system32\\drivers\\etc\\hosts",
	// "a");
	// System.out.println("Error: " + Kernel32.INSTANCE.GetLastError());
	// Kernel32.INSTANCE.SetLastError(0);
	// long pointer = Pointer.nativeValue(f.getPointer());
	// return pointer;
	// }
}

interface Listener {
	void run();
}

class A {
	public static int a = 2;

	public void print() {
		System.out.println(a);
	}

	public A() {
	}

	public int foo1() {
		Kernel32.INSTANCE.GetModuleHandle("");
		int rett = Kernel32.INSTANCE.GetLastError();
		System.out.println("ID : " + Thread.currentThread().getId());
		return rett;
	}

	public int foo2() {
		int ret = Kernel32.INSTANCE.GetLastError();
		System.out.println("ID : " + Thread.currentThread().getId());
		return ret;
	}
}

class B extends A {
	public B() {
		a = 200;
	}
}

class NewThread1 extends Thread {
	private RTL_CRITICAL_SECTION _lpCriticalSection;

	public NewThread1(RTL_CRITICAL_SECTION lpCriticalSection) {
		this._lpCriticalSection = lpCriticalSection;
		start(); // báº¯t Ä‘áº§u thread
	}

	// This is the entry point for the Thread.
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1: " + (new A()).foo1());
		this._lpCriticalSection.LockCount = new LONG(100);
	}
}

class NewThread2 extends Thread {
	private RTL_CRITICAL_SECTION _lpCriticalSection;

	public NewThread2(RTL_CRITICAL_SECTION lpCriticalSection) {
		this._lpCriticalSection = lpCriticalSection;
		start(); // báº¯t Ä‘áº§u thread
	}

	// This is the entry point for the Thread.
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2: " + (new A()).foo2());
		this._lpCriticalSection.LockCount = new LONG(200);
	}
}