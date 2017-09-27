package org.analysis.complement;

public class Other {
	public static boolean checkLocation(String fileName, String location) {
		return (fileName.equals("Virus.Win32.Aztec.01") && (location.equals("0x0040106c")
				|| location.equals("0x00401078") || location.equals("0x00401084") || location.equals("0x0040108a")
				|| location.equals("0x00401099") || location.equals("0x004010c7") || location.equals("0x00401142")
				|| location.equals("0x004013d6") || location.equals("0x004013dc") || location.equals("0x004012d6")
				|| location.equals("0x004013eb") || location.equals("0x004013f1") || location.equals("0x004012bd")
				|| location.equals("0x004012d0") || location.equals("0x004013ff") || location.equals("0x00401405")
				|| location.equals("0x004012a5") || location.equals("0x0040111f") || location.equals("0x004013b4")
				|| location.equals("0x004013c0") || location.equals("0x004013c6") || location.equals("0x00401135")
				|| location.equals("0x004012b1") || location.equals("0x004010b1")
		// || location.equals("0x004011b4")
		))

				|| (fileName.equals("sysCall.exe")
						&& ((location.equals("0x00401021") || location.equals("0x00401015")))

						|| (fileName.equals("Virus.Win32.Htrip.d") && ((location.equals("0x00401242") || location
								.equals("0x00401015"))))

						|| (fileName.equals("Virus.Win32.Htrip.a") && ((location.equals("0x0040122a") || location
								.equals("0x00401015"))))
						|| (fileName.equals("Virus.Win32.Htrip.b") && ((location.equals("0x00401227") || location
								.equals("0x00401015"))))
						|| (fileName.equals("Virus.Win32.Htrip.c") && ((location.equals("0x004011e7") || location
								.equals("0x00401015"))))
						|| (fileName.equals("Virus.Win32.Wit.a") && ((location.equals("0x00401250") || location
								.equals("0x00401015"))))
						|| (fileName.equals("Virus.Win32.Wit.b") && ((location.equals("0x00401276") || location
								.equals("0x00401015"))))
						|| (fileName.equals("Virus.Win32.Seppuku.4827") && ((location.equals("0x010177b3")
								|| location.equals("0x01017453") || location.equals("0x010173b4"))))

				// || (fileName.equals("Virus.Win32.Eva.g") &&
				// (location.equals("0x00401007") ||
				// location.equals("0x00401015")))

				// || (fileName.equals("loop1.exe") &&
				// (location.equals("0x00401034")))

				|| (fileName.equals("seh.exe") && location.equals("0x00401033")));
	}
}
