/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.transition_rule
 * File name: X86InstructionMapGenerator.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.transition_rule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Yen Nguyen
 *
 */
public class XMLMapGenerator {
	private static BufferedWriter bufferedWriter;

	private static void println(String packageName, String asmName, String fileName) throws IOException {
		String str = "\t\t<API assemblyName=\"" + asmName.toLowerCase() + "\" className=\"" + packageName + fileName
				+ "\" />";
		// System.out.println(str);
		bufferedWriter.write(str);
		bufferedWriter.write("\r\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 0;
		String workingDir = System.getProperty("user.dir");
		String path = workingDir + "\\src\\" + XMLMapGenerator.class.getPackage().getName().replace(".", "\\");

		try {
			File XMLfile = new File(path + "\\X86AssemblyMap.xml");

			// if file doesn't exists, then create it
			if (!XMLfile.exists()) {
				XMLfile.createNewFile();
			}

			FileWriter fw = new FileWriter(XMLfile.getAbsoluteFile());
			bufferedWriter = new BufferedWriter(fw);

			bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<X86AssemlyMap>");
			bufferedWriter.write("\r\n");
			File directory = new File(path);
			for (File file : directory.listFiles()) {
				if (file.isDirectory() && !file.getName().equals("stub")) {
					bufferedWriter.write("\t<GROUP name=\"" + file.getName() + "\">");
					bufferedWriter.write("\r\n");

					File subDir = new File(file.getAbsolutePath()/*
																 * +
																 * "\\functions"
																 */);
					for (File api : subDir.listFiles()) {
						String packageName = XMLMapGenerator.class.getPackage().getName() + "." + file.getName() + ".";
						// + ".functions.";
						String asmName = api.getName().replace(".java", "");
						String fileName = asmName;
						
						/***********************************************
						 * SPECIAL NAME THAT JAVA DONT ALLOW TO DEFINE *
						 ***********************************************/
						if (asmName.charAt(0) == '_') {
							asmName = asmName.substring(1, asmName.length());
						}

						// System.out.println("\t\t" + funcName);

						println(packageName, asmName, fileName);
						count++;
					}

					bufferedWriter.write("\t</GROUP>");
					bufferedWriter.write("\r\n");
				}
			}

			bufferedWriter.write("</X86AssemlyMap>");
			bufferedWriter.write("\r\n");
			bufferedWriter.close();
			System.out.println("Total: " + count);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
