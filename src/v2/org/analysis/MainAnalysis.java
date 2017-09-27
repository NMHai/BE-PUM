package v2.org.analysis;

import antlr.ANTLRException;
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
import v2.org.analysis.algorithm.OTFModelGeneration;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.comparison.DOTComparison;
import v2.org.analysis.statistics.FileProcess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainAnalysis {

	private static Logger logger = Logger.getLogger(Main.class);

	private final static String version = "2";

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

	public static void main(String[] args) {
		mainThread = Thread.currentThread();

		// String[] arg = SplitUsingTokenizer("-m " + file, " ");
		// String[] arg = SplitUsingTokenizer(pathFile, " ");
		// String[] arg = SplitUsingTokenizer("-s -m asm/chosenFile/processing/"
		// + in, " ");
		Options.parseOptions(args);
		// Options.parseOptions(in.next().split(" "));

		logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);
		logger.info("                         Jakstab " + version);
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
			if (baseFileName == null)
				baseFileName = getBaseFileName(mainFile);
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
			} else
				System.out.println(Characters.starredBox("Analysis finished, CFG is complete!"));

			if (!otfMG.isSound()) {
				logger.error(Characters.starredBox("WARNING: Analysis was unsound!"));
			}
			program.generageCFG(baseFileName);
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
			System.out.println("   Nodes:                        " + String.format("%8d", cfg.getVertexCount()));
			System.out.println("   Edges:                        " + String.format("%8d", cfg.getEdgeCount()));
			FileProcess fullResultFile = program.getLogResultFile();

			fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
			fullResultFile.appendFile("   Statistics for On-The-Fly Model Generation of BE-PUM");
			fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
			fullResultFile.appendFile("   Filename:                     " + program.getFileName());
			fullResultFile.appendFile("   Runtime:                     "
					+ String.format("%8dms", (overallEndTime - overallStartTime)));
			fullResultFile.appendFile("   Instructions:                        "
					+ String.format("%8d", cfg.getInstructionCount()));
			fullResultFile.appendFile("   Nodes:                        " + String.format("%8d", cfg.getVertexCount()));
			fullResultFile.appendFile("   Edges:                        " + String.format("%8d", cfg.getEdgeCount()));

			FileProcess resultFile = program.getResultFile();
			resultFile.appendFile(program.getFileName() + "\t"
					+ String.format("%8dms", (overallEndTime - overallStartTime)) + "\t"
					+ String.format("%8d", cfg.getVertexCount()) + "\t" + String.format("%8d", cfg.getEdgeCount())
					+ "\t" + program.getTechnique() + "\t" + program.getDetailTechnique());

			program.getResultFileTemp().appendInLine(program.getDetailTechnique());
			program.getResultFileTemp().appendInLine(
					'\t' + String.format("%8dms", (overallEndTime - overallStartTime)) + "\t"
							+ String.format("%8d", cfg.getVertexCount()) + "\t"
							+ String.format("%8d", cfg.getEdgeCount()));

			// Comparison between IDA and BE-PUM
			// String f = program.generatePathFileName(baseFileName);
			new DOTComparison().exportComparison(baseFileName);

			// System.out.println("Vertex List:");
			// System.out.println(Program.getProgram().getBPCFG().getVertecesList().toString());
			// System.out.println("Edges List:");
			// System.out.println(Program.getProgram().getBPCFG().getEdgesList().toString());
			try {
				Runtime.getRuntime().removeShutdownHook(shutdownThread);
				// System.exit(0);
			} catch (IllegalStateException e) {
				// Happens when shutdown has already been initiated by Ctrl-C or
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
		mainThread = Thread.currentThread();

		String[] arg = SplitUsingTokenizer("-m " + file, " ");
		// String[] arg = SplitUsingTokenizer(pathFile, " ");
		// String[] arg = SplitUsingTokenizer("-s -m asm/chosenFile/processing/"
		// + in, " ");
		Options.parseOptions(arg);
		// Options.parseOptions(in.next().split(" "));

		logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);
		logger.info("                         Jakstab " + version);
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
			if (baseFileName == null)
				baseFileName = getBaseFileName(mainFile);
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
			} else
				System.out.println(Characters.starredBox("Analysis finished, CFG is complete!"));

			if (!otfMG.isSound()) {
				logger.error(Characters.starredBox("WARNING: Analysis was unsound!"));
			}
			program.generageCFG(baseFileName);
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
			System.out.println("   Nodes:                        " + String.format("%8d", cfg.getVertexCount()));
			System.out.println("   Edges:                        " + String.format("%8d", cfg.getEdgeCount()));
			FileProcess fullResultFile = program.getLogResultFile();

			fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
			fullResultFile.appendFile("   Statistics for On-The-Fly Model Generation of BE-PUM");
			fullResultFile.appendFile(Characters.DOUBLE_LINE_FULL_WIDTH);
			fullResultFile.appendFile("   Filename:                     " + program.getFileName());
			fullResultFile.appendFile("   Runtime:                     "
					+ String.format("%8dms", (overallEndTime - overallStartTime)));
			fullResultFile.appendFile("   Instructions:                        "
					+ String.format("%8d", cfg.getInstructionCount()));
			fullResultFile.appendFile("   Nodes:                        " + String.format("%8d", cfg.getVertexCount()));
			fullResultFile.appendFile("   Edges:                        " + String.format("%8d", cfg.getEdgeCount()));

			FileProcess resultFile = program.getResultFile();
			resultFile.appendFile(program.getFileName() + "\t"
					+ String.format("%8dms", (overallEndTime - overallStartTime)) + "\t"
					+ String.format("%8d", cfg.getVertexCount()) + "\t" + String.format("%8d", cfg.getEdgeCount())
					+ "\t" + program.getTechnique() + "\t" + program.getDetailTechnique());

			program.getResultFileTemp().appendInLine(program.getDetailTechnique());
			program.getResultFileTemp().appendInLine(
					'\t' + String.format("%8dms", (overallEndTime - overallStartTime)) + "\t"
							+ String.format("%8d", cfg.getVertexCount()) + "\t"
							+ String.format("%8d", cfg.getEdgeCount()));

			// Comparison between IDA and BE-PUM
			// String f = program.generatePathFileName(baseFileName);
			new DOTComparison().exportComparison(baseFileName);

			// System.out.println("Vertex List:");
			// System.out.println(Program.getProgram().getBPCFG().getVertecesList().toString());
			// System.out.println("Edges List:");
			// System.out.println(Program.getProgram().getBPCFG().getEdgesList().toString());
			try {
				Runtime.getRuntime().removeShutdownHook(shutdownThread);
				System.exit(0);
			} catch (IllegalStateException e) {
				// Happens when shutdown has already been initiated by Ctrl-C or
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

}
