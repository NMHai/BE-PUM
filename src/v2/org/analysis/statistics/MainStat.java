package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainStat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Begin to analyze Log file!");
//		logFrequency("asm/log/freq.log", "data/data/freqResult.txt");
		FileProcess.searchInDir("asm/log/", "data/data/unsupportAPI.txt", "Unsupported API: ", "No Handling of this API");
		System.out.println("Finish!");
	}

	public static void logAnalysis(String myDirectoryPath, String result) {
		File dir = new File(myDirectoryPath);
		File[] directoryListing = dir.listFiles();
		FileProcess ret = new FileProcess(result);
		FileProcess interrupt = new FileProcess("data/data/interruptResult.txt");

		if (directoryListing != null) {
			for (File child : directoryListing) {
				// Do something with child
				try {
					// clearContentFile(unprocessedFile);
					BufferedReader br = new BufferedReader(new FileReader(child.getAbsolutePath()));
					// StringBuilder sb = new StringBuilder();
					String line = br.readLine();
					int num = 0;
					String fileName = child.getName().substring(6);
					fileName = fileName.replace(".log", "");
					while (line != null) {

						if (line.contains("START NEW OTF THREAD")) {
							num++;
							// Main.process("-m " + pathVirus + line);
							if (num == 2) {
								System.out.println("MultiThread:" + fileName);
								ret.appendFile(fileName);
							}
						}
						if (line.contains("INTERRUPT OTF THREAD")) {
							System.out.println("Interrupt:" + fileName);
							interrupt.appendFile(fileName);
						}
						line = br.readLine();
						// if (numFile > 0) break;
					}
					br.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}

			}
		} else {
			// Handle the case where dir is not really a directory.
			// Checking dir.isDirectory() above would not be sufficient
			// to avoid race conditions with another process that deletes
			// directories.
		}
	}

	public static void logFrequency(String myPath, String result) {
		FileProcess retsultFile = new FileProcess(result);

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(myPath));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				int[] freq = new int[14];
				for (int i = 0; i < freq.length; i++) {
					freq[i] = 0;
				}

				String[] freqString = line.split("_");
				for (int i = 0; i < freqString.length; i++) {
					if (freqString[i] != null && freqString[i] != "") {
						try {
							freq[Integer.parseInt(freqString[i]) - 1]++;
						} catch (Exception e) {
							System.out.println(e.toString());
						}
					}
				}
				String resultFreq = "";
				for (int i = 0; i < freq.length; i++) {
					resultFreq += freq[i] + "\t";
				}

				retsultFile.appendFile(resultFreq);
				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
