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

import antlr.ANTLRException;
import org.jakstab.Algorithm;
import org.jakstab.Options;
import org.jakstab.Program;
import org.jakstab.StatsTracker;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.loader.BinaryParseException;
import org.jakstab.loader.DefaultHarness;
import org.jakstab.loader.HeuristicHarness;
import org.jakstab.ssl.Architecture;
import org.jakstab.util.Characters;
import org.jakstab.util.Logger;
import v2.org.analysis.algorithm.OTFModelGeneration;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.statistics.FileProcess;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_AutomaticDetection {

	private static Logger logger = Logger.getLogger(Main_AutomaticDetection.class);

	private final static String version = "0.8.3";

	private static volatile Algorithm activeAlgorithm;
	private static volatile Thread mainThread;

	public static String[] SplitUsingTokenizer(String Subject, String Delimiters) {
		StringTokenizer StrTkn = new StringTokenizer(Subject, Delimiters);
		ArrayList<String> ArrLis = new ArrayList<String>(Subject.length());
		while (StrTkn.hasMoreTokens()) {
			ArrLis.add(StrTkn.nextToken());
		}
		return ArrLis.toArray(new String[0]);
	}

	/*
	 * public static void main(String[] args) { //Main m = new Main(); String
	 * fileName = "asm/sefm14/pony1.exe"; Main.process("-m " + fileName); }
	 */

	/*
	 * String path = "asm/vx.netlux.org/";
	 * 
	 * //in = "Backdoor.Win32.Gargamel.a"; in = "Worm.Win32.Chainsaw.a"; in =
	 * "Worm.Win32.Chainsaw.b"; in = "Worm.Win32.Banwor.a"; in =
	 * "Worm.Win32.Banwor.b"; in = "Worm.Win32.Banwor.d"; in =
	 * "Worm.Win32.Banwor.e"; in = "Worm.Win32.Banwor.f"; in =
	 * "Virus.Win9x.ZRam.a"; in = "Virus.Win9x.ZRam.b"; in =
	 * "Virus.DOS.Yong-Ga-Ri.1867"; in = "Virus.DOS.Tile.748"; in =
	 * "Virus.DOS.Tetris.633"; in = "Virus.DOS.Spooky.571.a"; in =
	 * "HackTool.Linux.SVScan.a"; in = "Flooder.Win32"; in =
	 * "Virus.DOS.Deicide.666.a"; in = "Virus.DOS.Deicide.666.b"; in =
	 * "Exploit.Win32.MS04-031.a"; in = "Exploit.Win32.MS04-031.b"; in =
	 * "Exploit.Win32.MS04-031.c";
	 * 
	 * in = "Virus.DOS.Holiday.3000"; in = "Virus.DOS.Hysterya.2209"; in =
	 * "Virus.DOS.Nono.1510"; in = "Virus.DOS.Ku.334.b"; in =
	 * "Virus.DOS.Dead.1362"; in = "Virus.DOS.Demon3b.4313"; in =
	 * "Virus.DOS.HLLP.RedArc.Twix" ; //Not finished in =
	 * "Trojan-PSW.Win32.QQRob.16.s"; in = "Virus.DOS.Boys.500"; in =
	 * "Virus.DOS.Flat.1000.a"; in = "Virus.DOS.Flat.1000.b"; in =
	 * "Virus.DOS.Flat.1000.c"; in = "Virus.DOS.HLLO.10842"; in =
	 * "Virus.DOS.Shish.1002"; in = "Virus.DOS.Shish.1142"; in =
	 * "Virus.DOS.BadBoy.1001.a"; in = "Virus.DOS.BadBoy.1001.b"; in =
	 * "Email-Worm.Win32.Bagle.a"; in = "Exploit.Linux.Local.c";
	 */
	// final static String targetPath = "C:/Software/Virus/API/";
	final static String targetPath = "asm/vx.netlux.org/";
	final static String targetListTXT = "data/data/listFile.txt";
	// final static String targetFileList = "UnresolveTargetJmp.txt";
	// final static String targetFileList = "UnresolveTargetFiles.txt";
	final static String processedFileListTXT = "data/data/processedListFile.txt";

	public static void main(String[] args) {

		// FileProcess targetList = new FileProcess(targetListTXT);
		// targetList.clearContentFile();
		// targetList.listFileInDir(targetPath);

		FileProcess processedFileList = new FileProcess(processedFileListTXT);
		// FileProcess tFileList = new FileProcess(targetFileList);

		try {
			BufferedReader br = new BufferedReader(new FileReader(targetListTXT));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				if (!processedFileList.contain(line) && checkTargetFile(line)) {
					// && tFileList.containFN(line)
					System.out.println("Process file: " + line);
					processedFileList.appendFile(line);
					mainThread = Thread.currentThread();

					StatsTracker stats = StatsTracker.getInstance();

					// Parse command line
					System.out.print("Enter file name:");
					System.out.println(line);

					String[] arg = SplitUsingTokenizer("-m " + targetPath + line, " ");

					Options.parseOptions(arg);

					logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);
					logger.info("                         Jakstab " + version);
					logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);

					logger.error("Start analyzing with Jakstab!");

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

					long overallStartTime = System.currentTimeMillis();

					// ///////////////////////
					// Parse executable

					Program program = Program.createProgram(arch);
					program.setDebugLevel(0);

					File mainFile = new File(Options.mainFilename).getAbsoluteFile();

					String baseFileName = null;

					try {
						// Load additional modules
						for (String moduleName : Options.moduleFilenames) {
							logger.fatal("Parsing " + moduleName + "...");
							File moduleFile = new File(moduleName).getAbsoluteFile();
							program.loadModule(moduleFile);

							// If we are processing drivers, use the driver's
							// name as base
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
						if (baseFileName == null)
							baseFileName = getBaseFileName(mainFile);

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
						logger.verbose("Setting start address to 0x"
								+ Long.toHexString(Options.startAddress.getValue()));
						program.setEntryAddress(new AbsoluteAddress(Options.startAddress.getValue()));
					}

					// Add surrounding "%DF := 1; call entrypoint; halt;"
					program.installHarness(Options.heuristicEntryPoints.getValue() ? new HeuristicHarness()
							: new DefaultHarness());

					int slashIdx = baseFileName.lastIndexOf('\\');
					if (slashIdx < 0)
						slashIdx = baseFileName.lastIndexOf('/');
					if (slashIdx < 0)
						slashIdx = -1;
					slashIdx++;
					stats.record(baseFileName.substring(slashIdx));
					stats.record(version);

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
							public void run() {
								try {
									System.in.read();
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.exit(1);
							}
						};
						// yices.dll blocks for input on load for some reason,
						// so load it
						// before we start reading from System.in
						// If you are having problems with that, uncomment the
						// next line
						// org.jakstab.solver.yices.YicesWrapper.getVersion();
						eclipseShutdownThread.start();
					}

					// Necessary to stop shutdown thread on exceptions being
					// thrown
					try {

						// ///////////////////////
						// On-the-fly Model Generation
						OTFModelGeneration otfMG = new OTFModelGeneration(program);
						// long customTime = System.currentTimeMillis();
						// Execute the algorithm
						try {
							runAlgorithm(otfMG);
						} catch (RuntimeException r) {
							program.getLogResultFile()
									.appendFile(
											"!! Runtime exception during Control Flow Reconstruction! Trying to shut down gracefully.");
							logger.error("!! Runtime exception during Control Flow Reconstruction! Trying to shut down gracefully.");
							r.printStackTrace();
						}
						// long overallEndTime = System.currentTimeMillis();
						// ProgramGraphWriter graphWriter = new
						// ProgramGraphWriter(program);

						if (!otfMG.isCompleted()) {
							program.getLogResultFile().appendFile(
									Characters.starredBox("WARNING: Analysis interrupted, CFG might be incomplete!"));
							System.out.println(Characters
									.starredBox("WARNING: Analysis interrupted, CFG might be incomplete!"));
						} else {
							program.getLogResultFile().appendFile(
									Characters.starredBox("Analysis finished, CFG is complete!"));
							System.out.println(Characters.starredBox("Analysis finished, CFG is complete!"));
						}

						if (!otfMG.isSound()) {
							program.getLogResultFile().appendFile(
									Characters.starredBox("WARNING: Analysis was unsound!"));
							logger.error(Characters.starredBox("WARNING: Analysis was unsound!"));
						}
						//program.generageCFG(getExtractBaseFileName(baseFileName));
						program.generageCFG("/asm/cfg/" + program.getFileName());

						BPCFG cfg = program.getBPCFG();

						long overallEndTime = System.currentTimeMillis();
						System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
						System.out.println("   Statistics for On-The-Fly Model Generation of BE-PUM");
						System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
						System.out.println("   Filename:                     " + program.getFileName());
						System.out.println("   Runtime:                     "
								+ String.format("%8dms", (overallEndTime - overallStartTime)));
						System.out.println("   Instructions:                        "
								+ String.format("%8d", cfg.getInstructionCount()));
						System.out.println("   Nodes:                        "
								+ String.format("%8d", cfg.getVertexCount()));
						System.out.println("   Edges:                        "
								+ String.format("%8d", cfg.getEdgeCount()));
						FileProcess fullResultFile = program.getLogResultFile();

						fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
						fullResultFile.appendFile("   Statistics for On-The-Fly Model Generation of BE-PUM");
						fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
						fullResultFile.appendFile("   Filename:                     " + program.getFileName());
						fullResultFile.appendFile("   Runtime:                     "
								+ String.format("%8dms", (overallEndTime - overallStartTime)));
						fullResultFile.appendFile("   Instructions:                        "
								+ String.format("%8d", cfg.getInstructionCount()));
						fullResultFile.appendFile("   Nodes:                        "
								+ String.format("%8d", cfg.getVertexCount()));
						fullResultFile.appendFile("   Edges:                        "
								+ String.format("%8d", cfg.getEdgeCount()));

						FileProcess resultFile = program.getResultFile();
						resultFile.appendFile(program.getFileName() + "\t"
								+ String.format("%8dms", (overallEndTime - overallStartTime)) + "\t"
								+ String.format("%8d", cfg.getVertexCount()) + "\t"
								+ String.format("%8d", cfg.getEdgeCount()) + "\t" + program.getTechnique() + "\t"
								+ program.getDetailTechnique());

						program.getResultFileTemp().appendInLine(program.getDetailTechnique());
						program.getResultFileTemp().appendInLine(
								'\t' + String.format("%8dms", (overallEndTime - overallStartTime)) + "\t"
										+ String.format("%8d", cfg.getVertexCount()) + "\t"
										+ String.format("%8d", cfg.getEdgeCount()));

						// new DOTComparison().exportComparison(baseFileName);
						// System.out.println("Vertex List:");
						// System.out.println(Program.getProgram().getBPCFG().getVertecesList().toString());
						// System.out.println("Edges List:");
						// System.out.println(Program.getProgram().getBPCFG().getEdgesList().toString());
						try {
							Runtime.getRuntime().removeShutdownHook(shutdownThread);
							System.exit(0);
						} catch (IllegalStateException e) {
							// Happens when shutdown has already been initiated
							// by Ctrl-C or
							// Return
						}
					} catch (Throwable e) {
						System.out.flush();
						e.printStackTrace();
						Runtime.getRuntime().removeShutdownHook(shutdownThread);
						// Kills eclipse shutdown thread
						System.exit(1);
					}

				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {

		}

	}

	private static boolean checkTargetFile(String line) {
		// TODO Auto-generated method stub
		if (line.contains("null") || line.endsWith(".asm") || line.endsWith(".dot"))
			return false;
		return true;
	}

	private static void runAlgorithm(Algorithm a) {
		activeAlgorithm = a;
		a.run();
		activeAlgorithm = null;
	}

	@SuppressWarnings("unused")
	private static final void appendToFile(String filename, String text) {
		try {
			FileWriter statsFile = new FileWriter(filename, true);
			statsFile.append(text);
			statsFile.close();
		} catch (Exception e) {
			logger.error("Cannot write to outputfile!", e);
		}
	}

	private static String getBaseFileName(File file) {
		String baseFileName = file.getAbsolutePath();
		// Get name of the analyzed file without file extension if it has one
		if (file.getName().contains(".")) {
			int dotIndex = file.getPath().lastIndexOf('.');
			if (dotIndex > 0) {
				// baseFileName = file.getPath().substring(0, dotIndex);
				baseFileName = file.getPath();
			}
		}
		return baseFileName;
	}

	private static String getExtractBaseFileName(String baseFileName) {
		// TODO Auto-generated method stub

		String r = baseFileName.replace("vx.netlux.org", "cfg");

		return r;
	}
}
