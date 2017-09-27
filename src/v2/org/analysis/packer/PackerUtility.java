package v2.org.analysis.packer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jakstab.Program;

import v2.org.analysis.statistics.FileProcess;

public class PackerUtility {

	public static void setToLogFirst (Program prog, String detectViaHeader)
	{
//		FileProcess packerResultFile = prog.getPackerResultFile();
	
		String fileName = prog.getFileName();
		String viaHeader = detectViaHeader;
		
		String resultFirst = fileName + "\t" + viaHeader + "\t";
		
//		packerResultFile.appendFile(resultFirst);
	}
	
	public static void setToLog (Program prog, String backupDetectionState, String backupDetectionCountState)
	{
		String curDetectionFile = prog.getFileName();
		
//		FileProcess packerResultFile = prog.getPackerResultFile();
//		String packerResultFileName = Program.getPackerResultFileName();
//		String content = backupDetectionState;
//		writeFileUpdate(curDetectionFile, packerResultFile, packerResultFileName, content);
//		
//		FileProcess packerResultCountFile = prog.getPackerResultCountFile();
//		String packerResultCountFileName = Program.getPackerResultCountFileName();
//		String contentC = backupDetectionCountState;
//		writeFileUpdate(curDetectionFile, packerResultCountFile, packerResultCountFileName, contentC);
	}
	
	
	public static void writeFileUpdate (String curDetectionFile, FileProcess file, 
			String fileName, String content)
	{		
		ArrayList<String> resultString = new ArrayList<String>();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));

		    try {
		    	String lastLine = "", curLine = "";
		    	
				while ((curLine = input.readLine()) != null) {
					resultString.add(curLine);
				    lastLine = curLine;
				}
				
				input.close();
				
				String curFile = curDetectionFile;
				
				if (!lastLine.contains(curFile))
				{
					file.appendFile(content);
				}
				else 
				{
					resultString.remove(resultString.size() - 1);
					resultString.add(content);
					PrintWriter pResFileTemp = new PrintWriter(fileName);
					pResFileTemp.close();
					
					PrintWriter pResFile = new PrintWriter(fileName);
					for (String line : resultString) {
						pResFile.println(line);
					}
				    pResFile.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
