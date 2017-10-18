/*
 * Main.java - This file is part of the Jakstab project.
 * Copyright 2007-2012 Johannes Kinder <jk@jakstab.org>
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, see <http://www.gnu.org/licenses/>.
 */

package v2.org.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.UIManager;

import org.jakstab.Algorithm;
import org.jakstab.Options;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.loader.BinaryParseException;
import org.jakstab.loader.DefaultHarness;
import org.jakstab.loader.HeuristicHarness;
import org.jakstab.ssl.Architecture;
import org.jakstab.util.Characters;
import org.jakstab.util.Logger;

import antlr.ANTLRException;
import v2.org.analysis.algorithm.OTFModelGeneration;
import v2.org.analysis.algorithm.OTFThreadManager;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.deeplearning.AdjMatrixManager;
import v2.org.analysis.interactive.AnalysisModel;
import v2.org.analysis.json.JSONManager;
import v2.org.analysis.packer.PackerManager;
import v2.org.analysis.statistics.FileProcess;

public class Main {
	static {
		// Yen Nguyen: With so many System.out.print... calls,
		// the console will not able to show all of informations you want.
		// Set isLog true and them will be saved into Log.log file for you.
		// boolean isLog = true;
		boolean isLog = false;
		if (isLog) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
			Date date = new Date();
			String logFile = "Log-" + dateFormat.format(date) + ".log";
			setLogToFile(logFile);
		}
	}

	private static void setLogToFile(String logFile) {
		try {
			System.out.println("================== LOG TO FILE ==================");
			PrintStream out = new PrintStream(new FileOutputStream(logFile));
			System.setOut(out);
			logger = Logger.getLogger(Main.class);
			System.out.println("================== DEBUG ==================");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Logger logger = Logger.getLogger(Main.class);
	private final static String version = "2.0";
	private static volatile Algorithm activeAlgorithm;
	private static volatile Thread mainThread;
	public static boolean isGui = false; 
	private static boolean isCompareOlly = false, isExport = false, isExtractOEP = false;
	private static int memStart = 0x401000, memEnd = 0x401010, numStack = 0x10, timeOut = 0;  
	private static long pos = -1;

	public static String[] SplitUsingTokenizer(String Subject, String Delimiters) {
		StringTokenizer StrTkn = new StringTokenizer(Subject, Delimiters);
		ArrayList<String> ArrLis = new ArrayList<>(Subject.length());
		while (StrTkn.hasMoreTokens()) {
			ArrLis.add(StrTkn.nextToken());
		}
		return ArrLis.toArray(new String[0]);
	}

	/*
	 * public static void main(String[] args) { //Main m = new Main(); String
	 * fileName = "asm/sefm14/pony1.exe"; Main.process("-m " + fileName); }
	 */

	public static void main(String[] args) {
		mainThread = Thread.currentThread();

		// StatsTracker stats = StatsTracker.getInstance();

		// Parse command line
//		System.out.print("Enter file name:");
		String in = "";
		String pathVirus = "";
		// Path Virus
		pathVirus = "asm/virus/";
		in = "hostname.exe";

		String path = pathVirus + in;
		isGui = false;
		// YenNguyen: For jar file export		
		if (!Main.class.getResource("Main.class").toString().startsWith("file")) {
			if (args.length > 0) {
				for (String input : args) {					
					if (input.charAt(0) == '-') {
						input = input.toLowerCase();
						if (input.equals("-gui")) {
							try {
								isGui = true;
								// Set System L&F
								UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							} catch (Exception e) {
								// handle exception
							}
							new MainWindows();
						} else if (input.contains("-buffer")) {
							String size = input.substring(7);
							try {
								int buffer = Integer.valueOf(size);
								OTFThreadManager.getInstance().setSizeThreadBuffer(buffer);
							} catch (Exception e) {
								System.out.println(e.toString());
							}
						} else if (input.contains("-multithread")) {
							OTFThreadManager.getInstance().setMultiThread(true);							
						} else if (input.contains("-detectpacker")) {
							PackerManager.getInstance().setDetectPacker(true);							
						} else if (input.contains("-detectbinaryonly")) {
							PackerManager.getInstance().setDetectBinaryOnly(true);							
						} else if (input.contains("-detectsemanticonly")) {
//							PackerManager.getInstance().setDetectPacker(true);
							PackerManager.getInstance().setDetectSemanticOnly(true);							
						} else if (input.contains("-extractfrequency")) {
							PackerManager.getInstance().setDetectPacker(true);
							PackerManager.getInstance().setExtractFrequency(true);							
						} else if (input.contains("-compareolly")) {
							isCompareOlly = true;							
						} else if (input.contains("-export")) {
							isExport = true;							
						} else if (input.contains("-adjmatrix")) {
							AdjMatrixManager.getInstance().setExportImg(true);
						} else if (input.contains("-memstart")) {
							String size = input.substring(9);
							try {
								memStart = Integer.valueOf(size);								
							} catch (Exception e) {
								System.out.println(e.toString());
							}
						} else if (input.contains("-memend")) {
							String size = input.substring(7);
							try {
								memEnd = Integer.valueOf(size);								
							} catch (Exception e) {
								System.out.println(e.toString());
							}
						} else if (input.contains("-stack")) {
							String size = input.substring(6);
							try {
								numStack = Integer.valueOf(size);								
							} catch (Exception e) {
								System.out.println(e.toString());
							}
						} else if (input.contains("-timeout")) {
							String time = input.substring(8);
							try {
								//Convert from Second to Milisecond
								timeOut = Integer.valueOf(time) * 1000;								
							} catch (Exception e) {
								System.out.println(e.toString());
							}
						}
					} else {
						path = input;
					}
				}
			} else {
				@SuppressWarnings("resource")
				Scanner user_input = new Scanner(System.in);
				path = user_input.next();
			}
		}
		
		loadConfig("data/data/config.ini");
		System.out.print("Enter file name:");
		if (!isGui) {
			System.out.println(path);
			analyzeFile(path);
		}
	}
	
	private static void loadConfig(String file) {
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(file);
			// load a properties file
			prop.load(input);
			
			if (prop.getProperty("isAutoGenerateSymbolic") != null && prop.getProperty("isAutoGenerateSymbolic").toLowerCase().equals("true")) {
				AnalysisModel.getInstance().setAutoGenerateSymbolic(true);						
			}			
						
			if (prop.getProperty("gui") != null && prop.getProperty("gui").toLowerCase().equals("true")) {
				try {
					isGui = true;
					// Set System L&F
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					// handle exception
				}
				new MainWindows();
			} 
			
			if (prop.getProperty("isLogInstr") != null && prop.getProperty("isLogInstr").toLowerCase().equals("true")) {
				AnalysisModel.getInstance().setLogInstr(true);						
			} 
			
			if (prop.getProperty("isLogAPI") != null && prop.getProperty("isLogAPI").toLowerCase().equals("true")) {
				AnalysisModel.getInstance().setLogAPI(true);						
			} 
			
			if (prop.getProperty("multithread") != null && prop.getProperty("multithread").toLowerCase().equals("true")) {
				OTFThreadManager.getInstance().setMultiThread(true);							
			} 
			
			if (prop.getProperty("detectpacker") != null && prop.getProperty("detectpacker").toLowerCase().equals("true")) {
				PackerManager.getInstance().setDetectPacker(true);							
			} 
			
			if (prop.getProperty("detectbinaryonly") != null && prop.getProperty("detectbinaryonly").toLowerCase().equals("true")) {
				PackerManager.getInstance().setDetectBinaryOnly(true);							
			} 
			
			if (prop.getProperty("detectsemanticonly") != null && prop.getProperty("detectsemanticonly").toLowerCase().equals("true")) {
//				PackerManager.getInstance().setDetectPacker(true);
				PackerManager.getInstance().setDetectSemanticOnly(true);							
			} 
			
			if (prop.getProperty("exportjson") != null && prop.getProperty("exportjson").toLowerCase().equals("true")) {
//				PackerManager.getInstance().setDetectPacker(true);
				JSONManager.getInstance().setExportJSON(true);							
			} 
			
			if (prop.getProperty("extractfrequency") != null && prop.getProperty("extractfrequency").toLowerCase().equals("true")) {
				PackerManager.getInstance().setDetectPacker(true);
				PackerManager.getInstance().setExtractFrequency(true);							
			} 
			
			if (prop.getProperty("compareolly") != null && prop.getProperty("compareolly").toLowerCase().equals("true")) {
				isCompareOlly = true;							
			} 
			
			if (prop.getProperty("isExtractOEP") != null && prop.getProperty("isExtractOEP").toLowerCase().equals("true")) {				
				isExtractOEP = true;
//				System.out.println("ExtractOEP:" + isExtractOEP);
			} 
			
			if (prop.getProperty("export") != null && prop.getProperty("export").toLowerCase().equals("true")) {
				isExport = true;
				if (prop.getProperty("memstart") != null) {
					String temp = prop.getProperty("memstart");
					try {
						//Convert from Second to Milisecond
						memStart = Integer.valueOf(temp);								
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				
				if (prop.getProperty("memend") != null) {
					String temp = prop.getProperty("memend");
					try {
						//Convert from Second to Milisecond
						memEnd = Integer.valueOf(temp);								
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				
				if (prop.getProperty("position") != null) {
					String temp = prop.getProperty("position");
					try {
						//Convert from Second to Milisecond
						pos = Long.valueOf(temp);								
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				
				if (prop.getProperty("stack") != null) {
					String temp = prop.getProperty("stack");
					try {
						numStack = Integer.valueOf(temp);								
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			} 
			
			if (prop.getProperty("adjmatrix") != null && prop.getProperty("adjmatrix").toLowerCase().equals("true")) {
				AdjMatrixManager.getInstance().setExportImg(true);
				System.out.println("Export CFG to image:" + true);
			} 
			
			if (prop.getProperty("timeout") != null) {
				String time = prop.getProperty("timeout");
				try {
					//Convert from Second to Milisecond
					timeOut = Integer.valueOf(time) * 1000;		
//					System.out.println("Set Timeout:" + timeOut);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void runAlgorithm(Algorithm a) {
		activeAlgorithm = a;
		a.run();
		activeAlgorithm = null;
	}

	private static String getBaseFileName(File file) {
		String baseFileName = file.getAbsolutePath();
		// Get name of the analyzed file without file extension if it has one
		if (file.getName().contains(".")) {
			int dotIndex = file.getPath().lastIndexOf('.');
			if (dotIndex > 0) {
				// baseFileName = file.getPath().substring(0, dotIndex);
			}
		}
		return baseFileName;
	}

	public static void analyzeFile(String file) {
		// TODO Auto-generated method stub
		String fName = "";
		if (file.contains("\\")) {
			fName = file.substring(file.lastIndexOf("\\") + 1, file.length());
		} else if (file.contains("/")) {
			fName = file.substring(file.lastIndexOf("/") + 1, file.length());
		} else {
			fName = file;		
		}
		String logFile = "Log-" + fName + ".log";
		setLogToFile(logFile);
		
		mainThread = Thread.currentThread();

		String[] arg = SplitUsingTokenizer("-m " + file, " ");
		// String[] arg = SplitUsingTokenizer(pathFile, " ");
		// String[] arg = SplitUsingTokenizer("-s -m asm/chosenFile/processing/"
		// + in, " ");
		Options.parseOptions(arg);
		// Options.parseOptions(in.next().split(" "));

		logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);
		logger.info("                         BE-PUM " + version);
		logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);

		logger.error("Start analyzing file with BE-PUM");

		// ///////////////////////
		// Parse SSL file

		Architecture arch;
		try {
			arch = new Architecture(Options.sslFilename.getValue());
			// arch = new Architecture(fName.next());
		} catch (IOException e) {
			logger.fatal("Unable to open SSL file!", e);
			return;
		} catch (ANTLRException e) {
			logger.fatal("Error parsing SSL file!", e);
			return;
		}

		// ///////////////////////
		// Parse executable
		Program program = Program.createProgram(arch);
		program.setDebugLevel(3);

		File mainFile = new File(Options.mainFilename).getAbsoluteFile();

		String baseFileName = null;
		long overallStartTime = System.currentTimeMillis();
		try {
			// Load additional modules
			for (String moduleName : Options.moduleFilenames) {
				logger.fatal("Parsing " + moduleName + "...");
				File moduleFile = new File(moduleName).getAbsoluteFile();
				program.loadModule(moduleFile);

				// If we are processing drivers, use the driver's name as base
				// name
				if (Options.wdm.getValue() && moduleFile.getName().toLowerCase().endsWith(".sys")) {
					baseFileName = getBaseFileName(moduleFile);
				}
			}
			// Load main module last
			logger.error("Parsing main module " + Options.mainFilename + "...");
			program.loadMainModule(mainFile);

			// Use main module as base name if we have none yet
			// reserved for drivers, ignore
			if (baseFileName == null) {
				baseFileName = getBaseFileName(mainFile);
			}
			program.setAbsolutePathFile(baseFileName);
		} catch (FileNotFoundException e) {
			logger.fatal("File not found: " + e.getMessage());
			return;
		} catch (IOException e) {
			logger.fatal("IOException while parsing executable!", e);
			// e.printStackTrace();
			return;
		} catch (BinaryParseException e) {
			logger.fatal("Error during parsing!", e);
			// e.printStackTrace();
			return;
		}
		logger.info("Finished parsing executable.");

		// Change entry point if requested
		if (Options.startAddress.getValue() > 0) {
			logger.verbose("Setting start address to 0x" + Long.toHexString(Options.startAddress.getValue()));
			program.setEntryAddress(new AbsoluteAddress(Options.startAddress.getValue()));
		}

		// Add surrounding "%DF := 1; call entrypoint; halt;"
		program.installHarness(Options.heuristicEntryPoints.getValue() ? new HeuristicHarness() : new DefaultHarness());

		// stats.record(baseFileName.substring(slashIdx));
		// stats.record(version);

		// StatsPlotter.create(baseFileName + "_states.dat");
		// StatsPlotter.plot("#Time(ms)\tStates\tInstructions\tGC Time\tSpeed(st/s)");

		// Catches control-c and System.exit
		Thread shutdownThread = new Thread() {
			@Override
			public void run() {
				if (mainThread.isAlive() && activeAlgorithm != null) {
					// stop = true; // Used for CFI checks
					activeAlgorithm.stop();
					try {
						mainThread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Runtime.getRuntime().addShutdownHook(shutdownThread);

		// Add shutdown on return pressed for eclipse
		if (!Options.background.getValue() && System.console() == null) {
			logger.info("No console detected (eclipse?). Press return to terminate analysis and print statistics.");
			Thread eclipseShutdownThread = new Thread() {
				@Override
				public void run() {
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.exit(1);
				}
			};
			// yices.dll blocks for input on load for some reason, so load it
			// before we start reading from System.in
			// If you are having problems with that, uncomment the next line
			// org.jakstab.solver.yices.YicesWrapper.getVersion();
			eclipseShutdownThread.start();
		}

		// Necessary to stop shutdown thread on exceptions being thrown
		try {

			// ///////////////////////
			// On-the-fly Model Generation
			OTFModelGeneration otfMG = new OTFModelGeneration(program);
			otfMG.isCompareOlly(isCompareOlly, memStart, memEnd, numStack);
			otfMG.isExport(isExport, pos, memStart, memEnd, numStack);
			otfMG.setTimeOut(timeOut);
			otfMG.setExtractOEP(isExtractOEP);
			// long customTime = System.currentTimeMillis();
			// Execute the algorithm
			try {
				runAlgorithm(otfMG);
			} catch (RuntimeException r) {
				logger.error("!! Runtime exception during Control Flow Reconstruction! Trying to shut down gracefully.");
				r.printStackTrace();
			}
			// long overallEndTime = System.currentTimeMillis();
			// ProgramGraphWriter graphWriter = new ProgramGraphWriter(program);

			if (!otfMG.isCompleted()) {
				System.out.println(Characters.starredBox("WARNING: Analysis interrupted, CFG might be incomplete!"));
			} else {
				System.out.println(Characters.starredBox("Analysis finished, CFG is complete!"));
			}

			if (!otfMG.isSound()) {
				logger.error(Characters.starredBox("WARNING: Analysis was unsound!"));
			}
//			program.setEndDate(System.currentTimeMillis());
			program.generageCFG("/asm/cfg/" + program.getFileName());
			BPCFG cfg = program.getBPCFG();		
			
			// Update and export JSON file
			JSONManager.getInstance().exportData(program.getFileName());
			// Update and export IMAGE file
			AdjMatrixManager.getInstance().exportImage(program.getFileName(), AdjMatrixManager.MAX_SIZE);
//			AdjMatrixManager.getInstance().exportImage(program.getFileName());

			long overallEndTime = System.currentTimeMillis();
			System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
			System.out.println("   Statistics for On-The-Fly Model Generation of BE-PUM");
			System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
			System.out.println("   Filename:                    " + program.getFileName());
			System.out.println("   Runtime:                     "
					+ String.format("%8dms", (overallEndTime - overallStartTime)));
			System.out.println("   Instructions:                "
					+ String.format("%8d", cfg.getInstructionCount()));
			System.out.println("   Nodes:                       " + String.format("%8d", cfg.getVertexCount()));
			System.out.println("   Edges:                       " + String.format("%8d", cfg.getEdgeCount()));
			if (PackerManager.getInstance().isPacked() || PackerManager.getInstance().isDetectPacker()) {
				System.out.println("   Packer Identified:           " + PackerManager.getInstance().getDetectionResult());
			}
			
			// FileProcess fullResultFile = program.getFullResultFile();

			// Kernel32DLL.INSTANCE.SetCurrentDirectory(new
			// WString(System.getProperty("user.dir")));
			// fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
			// fullResultFile.appendFile("   Statistics for On-The-Fly Model Generation of BE-PUM");
			// fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
			// fullResultFile.appendFile("   Filename:                     " +
			// program.getFileName());
			// fullResultFile.appendFile("   Runtime:                     "
			// + String.format("%8dms", (overallEndTime - overallStartTime)));
			// fullResultFile.appendFile("   Instructions:                        "
			// + String.format("%8d", cfg.getInstructionCount()));
			// fullResultFile.appendFile("   Nodes:                        " +
			// String.format("%8d", cfg.getVertexCount()));
			// fullResultFile.appendFile("   Edges:                        " +
			// String.format("%8d", cfg.getEdgeCount()));

			FileProcess resultFile = program.getResultFile();
			resultFile.appendFile(program.getFileName() + "\t"
					+ String.format("%8dms", (overallEndTime - overallStartTime)) + "\t"
					+ String.format("%8d", cfg.getVertexCount()) + "\t" + String.format("%8d", cfg.getEdgeCount())
					+ "\t" + String.format("%b", otfMG.isCompleted()) 
					+ "\t" + String.format("%s", PackerManager.getInstance().getDetectionResult())
//					+ "\t" + String.format("%b", OTFThreadManager.getInstance().isMultiThread()) 
//					+ "\t" + String.format("%8d", OTFThreadManager.getInstance().getSizeThreadBuffer())
//					+ "\t" + String.format("%8d", OTFThreadManager.getInstance().getNumConflict())
//					+ "\t" + String.format("%8d", OTFThreadManager.getInstance().getTotalWaitingTime())
					);
			//
			// program.getResultFileTemp().appendInLine(program.getDetailTechnique());
			// program.getResultFileTemp().appendInLine(
			// '\t' + String.format("%8dms", (overallEndTime -
			// overallStartTime)) + "\t"
			// + String.format("%8d", cfg.getVertexCount()) + "\t"
			// + String.format("%8d", cfg.getEdgeCount()));

			// Comparison between IDA and BE-PUM
			// String f = program.generatePathFileName(baseFileName);
			// new DOTComparison().exportComparison(baseFileName);

			// PHONG: 20150508
			// DETECT VIA OTF IF COMPLETED
			// ////////////////////////////////////////////////////////////////////
			// program.SetAnalyzingTime(overallEndTime - overallStartTime);
			// program.getDetection().updateBackupDetectionState(program,
			// otfMG);
			// program.getDetection().setToLog(program);
			if (PackerManager.getInstance().isDetectPacker()) {
				PackerManager.getInstance().outputToFile(program.getFileName());
			}

			try {
				Runtime.getRuntime().removeShutdownHook(shutdownThread);
				// YenNguyen: Start GUI from this class
				if (!isGui) {
					System.exit(0);
				}
			} catch (IllegalStateException e) {
				// Happens when shutdown has already been initiated by Ctrl-C or
				// Return
				// e.printStackTrace();
			} finally {
			}
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}
	}

}
