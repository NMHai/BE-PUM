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
		pathVirus = "asm/packer/";
		// pathVirus = "asm/packer/";
		// in = "api_testv2.exe"; // 2064 2151 26s ?
		in = "api_test.exe"; // 158 160 0.1s x
		in = "api_test_upx_N.exe"; // 323 353 6s x
//		in = "api_test_fsg.exe"; // 244 268 3s x
//		in = "demo1_fsg.exe"; // 244 268 3s x
//		in = "api_testv2_fsg.exe"; // 244 268 3s x
//		in = "api_test_pecompact.exe"; // 1127 1178 28s x
//		in = "api_testv2_pecompact.exe";
//		in = "bof_aspack.exe";
//		in = "hostname_upx.exe";
//		in = "api_test_npack.exe"; // 602 639 10s x
		// in = "api_test_yoda.1.2.exe"; // 625 659 173s x
//		in = "api_test_yoda.1.3.exe"; // 924 960 163s x
		// in = "api_test_petite_2.3.exe"; // 1569 1637 86s x
//		in = "api_test_aspack.exe"; // 1047 1112 73s x
		// in = "api_test_mpress.exe"; // 459 489 103 x
		// in = "api_test_wwpack32.exe"; // 329 360 4s x
		// in = "api_test_exepack.exe"; // 323 353 6s x
		// in = "api_test_WinUpack.exe"; // 443 490 19s
		// in = "api_test_telock.exe"; // x
		// in = "api_test_RLPack.exe"; // 467 501 212s x
//		in = "api_test_upack.exe"; // 443 490 21s x
		// in = "api_test_mew.exe"; // 265 291 5s
		// in = "api_test_BJFNT.exe"; // x
		// in = "api_test_ExeStealth.exe"; // 735 770 220s x
		// in = "api_test_eXPressor.exe"; // 1172 1233 85s x
		// in = "api_test_NoodleCrypt.exe"; // 706 757 34s x

		// Undone
		// in = "api_test_EXEfog.exe";
		// in = "api_test_EXEJonier.exe";
		// in = "api_test_KKrunchy.exe"; // Disassembled at: 0x003f11ab
		// in = "api_test_Enigma.exe"; // Run long time
		// in = "api_test_ALEXProtector.exe"; // Error VirtualAlloc
		// in = "api_test_ACProtect.exe"; // Error Call Memory Run OllyDbg later
		// in = "protected_CRYPToCRACk.exe"; // Error file
		// in = "api_test_diet.exe"; // Error parse file
		// in = "api_test_PolyCryptPE.exe";
		// in = "api_test_Yodaprotect.exe";
		// in = "api_test_zprotect.exe"; // Run long time
		// in = "api_test_sercure.exe"; // 5328 5625 270s
		// in = "api_test_MSLRH.exe"; // Error with Int3
		// in = "UnPackMe_MSLRH0.32a.exe"; // RDTSC problem
		// in = "api_test_pespin.exe";
		// in = "demo1_fastpack.exe"; // 47 49 98s
		// in = "api_test_armadillo.exe";
		// in = "api_test_asprotect.exe";
		// in = "api_test_thermida.exe";
		// in = "api_test_vmprotect.exe"; // 488 532 456s
		// in = "api_test_pelock.exe"; // Run too long
		// in = "api_test_asprotect.exe";
		// in = "api_test_pebundle.exe";
		// in = "api_test_polycryptpe_1.exe"; // 106 109
		// in = "api_test_LZEXE.EXE"; // Error parse file
		// in = "api_test_PE_Ninja.exe";
		// in = "api_test_Morphine_3.5.exe";

		// in = "demo1.exe"; // 13405 13404 14s x
		// in = "demo1_upx.exe"; // 13563 13583 465 x
		// in = "demo1_fsg.exe"; // 13493 13510 13s 0111001010111110-NONE
		// in = "demo1_pecompact.exe"; // 788 828 225s
		// in = "demo1_npack.exe"; // 13851 13881 685s x
		// in = "demo1_yoda.exe";
		// in = "demo1_petite.exe"; // Error
		// in = "demo1_aspack.exe"; // 14278 14334 907s x
		// in = "demo1_fastpack.exe";

		// pathVirus = "asm/virus/";
		// pathVirus = "C:/Work/Virus/viruses-20070914/vx.netlux.org/";
		// in = "Email-Worm.Win32.Mydoom.az"; // 641 651
		// in =
		// "003ba46362d1c2643a690cd7e912441b0ee04ee0f8026789f677b873389c0361";

		pathVirus = "asm/packer/";
//		pathVirus = "asm/vx.netlux.org/";
		pathVirus = "asm/testcase/";
//		pathVirus = "asm/virus/";
//		in = "app5win.exe";
//		in = "peb.exe";
//		in = "hostname.exe";
//		// in = "Net-Worm.Win32.Sasser.a";
//		in = "multiThread1.exe";
//		in = "multiThread2.exe";
//		in = "multiThread3_FF.exe";
//		// in = "multiThread3_FFF.exe";
//		// in = "multiThread3_FFFF.exe";
//		in = "Rdws.exe_dc3c90084e8c.bin-unpacked.bin";
		in = "chunk.exe";
		in = "051abc015429449fd0c0d8b7147719ecfd94af186ed3a4861ea6ff331b51bff6";
		in = "ot.exe";
		in = "ws2_32_aspack.exe";
//		in = "api_testv2_aspack.exe";
		in = "helloworld_aspack.exe";
		in = "hostname_aspack.exe";
		in = "hostname_fsg.exe";
		in = "ot.exe";
		in = "fsg_api_test.test";
		in = "AsPack_Email-Worm.Win32.Atak.e";
		in = "telock_api_test.test";
		in = "Petite_asr_ldm.test";
		in = "000bcafbec780321c288fd5ced5bf3c02779eb11407379515e8e2b4a80e546e5";
		in = "00660194d1030bc7fdd720ccc75190375e4154655dc4c196cf2f7e93d9ec7f0e";
		in = "29a1d1e4e9bf469450a9b5d8e05f4e670e6758c75e3503940f1896f08f0ba6a6";
		in = "bt.exe";
//		in = "45ecf1b81bb92d0992e7eb8abb6d0826da15cefa77470e44332e9dcd78bb268c";
//		in = "000bcafbec780321c288fd5ced5bf3c02779eb11407379515e8e2b4a80e546e5";
		in = "01de23a5cb6ee7da7141bf6992f671c791942d4574faec966daa71f7ece3e9f5";
		in = "01ce4d46402f1c15a795b7fe6c3560da1fb36ce09f7a576da076be0355713fca";
		in = "02b3a2aedc127ad8fd066c72c41fa8f9fcdf9e86d5875b0b521aaec9ee04009d";
		in = "02ad410a97caba0d33707822a3b9a50b0fc46037a846b54dd1b760b955e71d42";
		in = "api_test_upx_1.exe";
		in = "03e1cd5daaadc14192c6332e7499f552e502a5bfd2a202b6d5350d5c773f2ae8";
//		in = "Rdws.exe_dc3c90084e8c.upx.bin";
//		in = "Rdws.exe_dc3c90084e8c.bin-unpacked.bin";
		in = "4d8fab790dcd0b1420d3488c70103fc64ea0aef3993340ec9d11d1de1ca9ecd4";
		in = "4d927b8859de65de0c631e88b3b7209a0199b458d34b5ac5cec0bfa1e822d13f";
		in = "api_test_morphnah.exe";
		in = "hostname_mew.exe";
		in = "multiThread7.exe";
		in = "api_test1.exe";
//		in = "getSystemTime.exe";
//		in = "tetcon.exe";
		in = "symbolVal1.exe";
		in = "symbolVal2.exe";
		in = "api_test_petite.exe";
//		in = "api_test_telock.exe";
		in = "PECompact_Email-Worm.Win32.Bagle.fy";
		in = "api_testv2_aspack.exe";
		in = "Upack_cliconfg.test";
		in = "Petite_fsutil.test";
		in = "Petite_grpconv.test";
		in = "Petite_help.test";
		in = "Petite_ftp.test";
		in = "Backdoor.Win32.Rbot.beh";
		in = "Trojan-PSW.Win32.Prostor.g";	
		in = "Backdoor.Win32.Death.27.e";
		in = "api_test_upack.exe";
		in = "PECompact_3.0.0.2_hostname.test";
		in = "Petite_2.2_demo1.test";		
		in = "helloworld.exe";
		in = "ws2_32.exe";
		in = "0ca027edda0e005c91c378e2f2fd3d51855d901e9c173a4a79698b987b2cf5e6";
		in = "ftp.exe";		
		in = "seh.exe";
		in = "jump.exe";
//		in = "api_test_upx.exe";
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
