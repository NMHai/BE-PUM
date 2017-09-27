package v2.org.analysis.system;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jakstab.asm.AbsoluteAddress;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import v2.org.analysis.system.dll.abstracts.LibImage;

public class LibraryHandle {
	public static final String ADVAPI32PATH = "data/lib/win32/advapi32.dll";
	public static final String USER32PATH = "data/lib/win32/user32.dll";
	public static final String KERNEL32PATH = "data/lib/win32/kernel32.dll";
	public static final String MSVCRTPATH = "data/lib/win32/msvcrt.dll";
	public static final String WS2_32PATH = "data/lib/win32/ws2_32.dll";

	private LibImage kernelHandle, userHandle, advapiHandle, msvcrtHandle, ws2Handle;
	private List<LibAbstract> libraryName;
	private Map<String, Long> apiAddr = null; // Store the address of API which
												// are feed by JSON file
	private static final String FILE_HASH = "data/data/apiValue.json";
	private static final Object API_NAME_TAG = "API_NAME";
	private static final Object ADDR_TAG = "ADDR";

	private Map<String, Long> readJSON() {
		Map<String, Long> ret = new HashMap<String, Long>();

		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(new FileReader(FILE_HASH));
			for (Object o : jArray) {

				JSONObject api = (JSONObject) o;

				String apiName = (String) api.get(API_NAME_TAG);
				Long addr = (long) Long.parseLong((String) api.get(ADDR_TAG), 16);
				ret.put(apiName, addr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;

	}

	public LibAbstract getLibrary(String lib) {
		// TODO Auto-generated method stub
		String libName = lib.toLowerCase();
		if (libName.equals("kernel32.dll") || libName.contains("kernel32")) {
			return kernelHandle;
		}

		if (libName.equals("user32.dll") || libName.contains("user32")) {
			return userHandle;
		}

		if (libName.equals("advapi32.dll") || libName.contains("advapi32")) {
			return advapiHandle;
		}

		if (libName.equals("msvcrt.dll") || libName.contains("msvcrt")) {
			return msvcrtHandle;
		}

		if (libName.equals("ws2_32.dll") || libName.contains("ws2_32")) {
			return ws2Handle;
		}

		for (LibAbstract l : libraryName) {
			if (l.getLibraryName().equals(libName)) {
				return l;
			}
		}
		Library t = new Library(libName);
		libraryName.add(t);

		return t;
	}

	public LibImage getKernel() {
		return kernelHandle;
	}

	//
	// public LibImage getUser32() {
	// return userHandle;
	// }
	//
	// public LibImage getAdvapi32() {
	// return advapiHandle;
	// }
	//
	// public LibImage getMsvcrt() {
	// // TODO Auto-generated method stub
	// return msvcrtHandle;
	// }

	public LibraryHandle() {
		kernelHandle = new LibImage(LibraryHandle.KERNEL32PATH);
		userHandle = new LibImage(LibraryHandle.USER32PATH);
		advapiHandle = new LibImage(LibraryHandle.ADVAPI32PATH);
		msvcrtHandle = new LibImage(LibraryHandle.MSVCRTPATH);
		ws2Handle = new LibImage(LibraryHandle.WS2_32PATH);

		libraryName = new ArrayList<LibAbstract>();

		// libraryName.add(advapiHandle);
		// libraryName.add(kernelHandle);
		// libraryName.add(msvcrtHandle);
		// libraryName.add(userHandle);
		libraryName.add(new Library("mapistub.dll"));
		libraryName.add(new Library("msvcrt.dll"));
		// libraryName.add(new Library("user32.dll"));
		// libraryName.add(new Library("kernel32.dll"));
		apiAddr = readJSON();
	}

	public LibAbstract getLibrary(long libraryHandle) {
		// TODO Auto-generated method stub

		if (kernelHandle.getBaseAddress() == libraryHandle) {
			return kernelHandle;
		}

		if (userHandle.getBaseAddress() == libraryHandle) {
			return userHandle;
		}

		if (advapiHandle.getBaseAddress() == libraryHandle) {
			return advapiHandle;
		}

		if (msvcrtHandle.getBaseAddress() == libraryHandle) {
			return msvcrtHandle;
		}

		if (ws2Handle.getBaseAddress() == libraryHandle) {
			return ws2Handle;
		}

		for (LibAbstract l : libraryName) {
			if (l.getBaseAddress() == libraryHandle) {
				return l;
			}
		}

		return null;
	}

	public String getAPIName(long value) {
		// TODO Auto-generated method stub
		String api = "";
		if (apiAddr != null) {
			for (Entry<String, Long> entry : apiAddr.entrySet()) {
				if (entry.getValue() == value) {
					return entry.getKey();
				}
			}
		}

		if (api == "") {
			api = kernelHandle.getProcName(value);
		}

		if (api == "") {
			api = userHandle.getProcName(value);
		}

		if (api == "") {
			api = advapiHandle.getProcName(value);
		}

		if (api == "") {
			api = msvcrtHandle.getProcName(value);
		}

		if (api == "") {
			api = ws2Handle.getProcName(value);
		}

		for (LibAbstract l : libraryName) {
			String t = l.getProcName(value);
			if (t != "") {
				return t + "@" + l.getLibraryName();
			}
		}

		return api;
	}

	public boolean isInside(AbsoluteAddress addr) {
		// Hai: Special Case when this address is API Address
		// Change later
		if (kernelHandle.isInside(addr)) {
			return true;
		}
		if (userHandle.isInside(addr)) {
			return true;
		}
		if (msvcrtHandle.isInside(addr)) {
			return true;
		}
		if (advapiHandle.isInside(addr)) {
			return true;
		}

		if (ws2Handle.isInside(addr)) {
			return true;
		}

		for (LibAbstract l : libraryName) {
			if (!l.getLibraryName().equals("kernel32.dll") && !l.getLibraryName().equals("user32.dll")
					&& l.getExportTable().containsKey(addr)) {
				return true;
			}
		}

		return false;
	}

	public String insideDLL(AbsoluteAddress address) {
		// TODO Auto-generated method stub
		if (kernelHandle.isInside(address)) {
			return kernelHandle.getLibraryName();
		}

		if (userHandle.isInside(address)) {
			return userHandle.getLibraryName();
		}

		if (advapiHandle.isInside(address)) {
			return advapiHandle.getLibraryName();
		}

		if (msvcrtHandle.isInside(address)) {
			return msvcrtHandle.getLibraryName();
		}

		if (ws2Handle.isInside(address)) {
			return ws2Handle.getLibraryName();
		}

		for (LibAbstract l : libraryName) {
			if (!l.getLibraryName().equals("kernel32.dll") && !l.getLibraryName().equals("user32.dll")
					&& l.getExportTable().containsKey(address)) {
				return l.getLibraryName();
			}
		}

		return null;
	}

	public long readByte(String libName, int address) {
		// TODO Auto-generated method stub
		return getLibrary(libName).readByte(address);
	}

	public long readWord(String libName, int address) {
		// TODO Auto-generated method stub
		return getLibrary(libName).readWord(address);
	}

	public long readDoubleWord(String libName, int address) {
		// TODO Auto-generated method stub
		return getLibrary(libName).readDoubleWord(address);
	}
}
