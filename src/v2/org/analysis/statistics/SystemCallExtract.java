/**
 * Collect data of System Call 
 * 
 * @author Nguyen Minh Hai
 */

package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SystemCallExtract {
	// static String pathFile = "asm/test";
	// static String pathFile = "asm/ExtractFile"; // Store the generated
	// results
	static String pathFile = "G:/Downloads/Sharing/Virus-Analyze/Result/BE-PUM/";
	static String pathResult = "data/data/systemCall";
	// static String pathExcel = "data/data/systemCallResult.excel";
	static String path = "data/data";

	public void extract() {
		File folder = new File(pathFile);
		FileProcess fp = new FileProcess("listFile_1700.txt");
		System.out.println("Begin to collect data of Systam Call in:" + pathFile);
		SystemCallList sList = new SystemCallList();
		for (File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()
			// && fileEntry.getName().endsWith("exe")
			) {
				// if (!check(fileEntry.getName())) {
				String t = fileEntry.getName();
				if (t.contains("_jak.asm") && fp.containFN(t))
					checkFile(t, sList);

			}
		}
		// sList.printExcelFile(pathExcel);
		sList.printResult(pathResult);
		System.out.println("End");
	}

	private void checkFile(String file, SystemCallList sList) {
		// TODO Auto-generated method stub
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(pathFile + "/" + file));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (line.contains("kernel32.dll") || line.contains("user32.dll")) {
					System.out.println(file);
					String t[] = line.split("\t");
					for (int i = 0; i < t.length; i++) {
						if (t[i].contains("kernel32.dll") || t[i].contains("user32.dll")) {
							String x = t[i];
							if (x.contains(",")) {
								String[] y = x.split(",");
								x = y[0];
							}
							sList.insert(x, file);
						}
					}
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		new SystemCallExtract().extract();
	}
}
