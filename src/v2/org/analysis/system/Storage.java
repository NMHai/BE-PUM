/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.system
 * File name: Storage.java
 * Created date: Apr 3, 2015
 * Description:
 */
package v2.org.analysis.system;

import java.io.File;
import java.util.HashSet;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * @author Yen Nguyen
 * 
 */
public class Storage {
	private static boolean init = false;
	public static final StringBuilder workingDirectory = (new StringBuilder(System.getProperty("user.dir")))
			.append('\\').append("Storage").append('\\');
	public static String CurrentDirectory = null;
	private static HashSet<String> createdPaths = new HashSet<>();

	static {
		char[] lpBuffer = new char[1024];
		int ret = Kernel32DLL.INSTANCE.GetCurrentDirectory(new DWORD(1024), lpBuffer);

		String curDir = new String(lpBuffer);
		CurrentDirectory = curDir.substring(0, ret);
	}

	private static void init() {
		if (init) {
			return;
		}

		// File[] paths = File.listRoots();

		deleteMappingPath(workingDirectory.toString());
		for (char drive = 'A'; drive <= 'H'; drive++) {
			// prints file and directory paths
			String directoryName = workingDirectory + String.valueOf(drive); // path.getPath().replace(":\\",
																				// "");
			createMappingDir(directoryName);
			// Some directory had just existed in old Windows OS
			if (drive == 'C') {
				directoryName += "/My Downloads";
				createMappingDir(directoryName);
			}
			createdPaths.add(String.valueOf(drive) + ":\\");
		}

		init = true;
	}

	private static boolean createMappingDir(String path) {
		File theDir = null;
		theDir = new File(path);

		if (theDir.getParent() != null && !theDir.getParentFile().exists()) {
			createMappingDir(theDir.getParent());
		}

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			try {
				theDir.mkdir();
				System.out.println("Creating directory: " + path);
			} catch (Exception se) {
				System.out.println("Fail to create directory: " + path);
				return false;
			}
		}

		return true;
	}

	private static boolean deleteMappingPath(String path) {
		File file = new File(path);
		return deleteMappingPath(file);
	}

	private static boolean deleteMappingPath(File file) {
		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					// recursive delete
					deleteMappingPath(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}

		} else {
			// if file, then delete it
			file.delete();
		}
		return true;
	}

	/**
	 * Get the mapping path in sandbox storage corresponding to the path in real
	 * system
	 * 
	 * @param path
	 *            The real path
	 * @return The mapping path in sandbox storage
	 */
	public static String getMappingPath(String path) {
		if (path == null || path.length() == 0) {
			return path;
		}

		String originalPath = path;
		init();

		try {
			String mappingPath = null;
			File pathFile = new File(path);
			if (!pathFile.isAbsolute()) {
				if (CurrentDirectory.length() > 1
						&& (CurrentDirectory.charAt(CurrentDirectory.length() - 1) != '\\' && CurrentDirectory
								.charAt(CurrentDirectory.length() - 1) != '/')) {
					CurrentDirectory += '\\';
				}
				pathFile = new File(CurrentDirectory + path);
			}
			path = pathFile.getAbsolutePath();

			mappingPath = (new StringBuilder(Storage.workingDirectory)).append(path.substring(0, path.indexOf(':')))
					.append('\\').append(path.substring(path.indexOf('\\') + 1)).toString();

			File mappingPathFile = new File(mappingPath);
			if (mappingPathFile.exists()) {
				return mappingPath;
			}

			// Preparing parent folder for create file/folder command case
			if (pathFile.getParentFile() != null && pathFile.getParentFile().exists()
					&& !mappingPathFile.getParentFile().exists()) {
				String parentDir = mappingPathFile.getParent();
				createMappingDir(parentDir);
			}

			// If path exists and the virtual storage has not process it
			if (pathFile.exists() && !createdPaths.contains(path)) {

				if (mappingPathFile.isDirectory()) {
					createMappingDir(mappingPath);
				} else {
					Kernel32.INSTANCE.CopyFile(path, mappingPath, false);
				}
				createdPaths.add(path);
			}
			return mappingPath;
		} catch (Exception ex) {
			// Return original path if it is unvalid
			return originalPath;
		}
	}

	public static String getOriginalPath(String path) {
		if (path == null) {
			return null;
		}

		if (path.length() < workingDirectory.length()) {
			return path;
		}

		char driveName = path.charAt(workingDirectory.length());

		return String.format("%c:%s", driveName, path.substring(workingDirectory.length() + 1, path.length()));
	}
}
