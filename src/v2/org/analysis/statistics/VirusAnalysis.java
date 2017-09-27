package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.FileReader;

public class VirusAnalysis {
	// final static String pathVirus = "asm/sefm/";
	final static String pathVirus = "asm/vx.netlux.org/";
	final static String fileList = "listFile.txt";
	final static String targetFileList = "UnresolveTargetJmp.txt";
	final static String processedFileList = "processedListFile.txt";

	// final String indirectFile = "indirectFile.txt";

	public static void main(String[] args) {
		FileProcess fList = new FileProcess(fileList);
		fList.clearContentFile();
		fList.listFileInDir(pathVirus);
		FileProcess fPFList = new FileProcess(processedFileList);
		FileProcess tFL = new FileProcess(targetFileList);

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(fileList));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (!fPFList.contain(line) && tFL.containFN(line)) {
					fPFList.appendFile(line);
					// Main.process("-m " + pathVirus + line);
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		System.exit(0);
	}
}
