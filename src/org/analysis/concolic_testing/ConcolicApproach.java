package org.analysis.concolic_testing;

import org.analysis.cfg.IntraCFG;
import org.analysis.concrete_execution.ConcreteExecution;
import org.analysis.symbolic_execution.SymbolicExecution;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import v2.org.analysis.cfg.AddressList;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.system.SystemHandle;

import java.util.List;

public class ConcolicApproach {
	private Program program;
	AbsoluteAddress indirectTarget;
	final String indirectFile = "indirectFile.txt";
	final String indirectResolvedFile = "indirectResolvedFile.txt";
	final String processedFileList = "processedListFile.txt";

	// Map<AbsoluteAddress, AbsoluteAddress> preservedExecutionMap;

	public boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public ConcolicApproach(Program program, AbsoluteAddress target) {
		this.program = program;
		this.indirectTarget = target;
		// this.preservedExecutionMap = map;
	}

	//
	public AbsoluteAddress resolveIndirectJump() {
		AbsoluteAddress result = null, temp = null;
		String data = new FileProcess(this.processedFileList).getLastLine();
		FileProcess x = new FileProcess(this.indirectFile);
		if (!x.contain(data))
			x.appendFile(data);
		SystemHandle system = new SystemHandle();
		// if (this.indirectTarget.toString().equals("0x00401142"))
		// System.out.println("Debug");
		IntraCFG cfg = new IntraCFG(program, system);
		// cfg.prinCFG();
		// if (this.indirectTarget.toString().equals("0x004013b4"))
		// System.out.println("Debug");
		/*
		 * //cfg.prinCFG(); CFGVertex v =
		 * cfg.getCFGVertices().getVertex(this.indirectTarget);
		 * v.printAddrList_IndirectJump(); }
		 */

		cfg.setTypeIndirectJump(this.indirectTarget);
		// EnvSemiRing sr = new EnvSemiRing();

		List<AddressList> traceList = cfg.getTraceList(this.indirectTarget);
		if (traceList == null)
			return null;
		for (int i = 0; i < traceList.size(); i++) {
			SystemHandle system1 = new SystemHandle();
			AddressList trace = traceList.get(i);
			AddressList concreteTrace = trace.clone();
			// System.out.print("Original Trace:");
			trace.printInfo();
			TestCaseValue sv = new TestCaseValue();
			// BackwardSlicingMethod.slicingAlgorithm(trace,
			// this.indirectTarget,
			// program.getAssemblyMap(), sv);

			// SymbolicExecution se = new SymbolicExecution(this.indirectTarget,
			// trace, cfg.getNegConditionList(), sv, program, system);
			SymbolicExecution se = new SymbolicExecution(this.indirectTarget, trace.clone(), cfg, sv, program, system1);
			// se.setSr(sr);
			// se.printInfor();
			if (!se.execute()) {
				continue;
			}

			if (se.getEvaluateValue() != Long.MIN_VALUE) {
				result = new AbsoluteAddress(se.getEvaluateValue());
				// AbsoluteAddress a = new AbsoluteAddress(result);
				System.out.println("Result of Evaluate: " + result.getValue() + " Hex value:" + result.toString());
				if (program.getAssemblyMap().containsKey(result))
					System.out
							.println("This is not new area: " + result.getValue() + " Hex value:" + result.toString());
				else {
					// result = temp;
					System.out.println("The new area of Hybrid Approach from Symbolic Execution Part:"
							+ result.getValue() + " Hex value:" + result);
					System.out.println("**********************************************");
					/*
					 * if (!indirectTarget.toString().equals("0x0040111f"))
					 * break; else
					 * System.out.println("Debug Concrete Execution " +
					 * indirectTarget.toString());
					 */
				}
				break;
			}
			// sv.printString();
			// se.printResult();
			SystemHandle system2 = new SystemHandle();
			ConcreteExecution ce = new ConcreteExecution(this.indirectTarget, concreteTrace.clone(), cfg, sv, program,
					system2);
			temp = ce.execute();
			// result = ce.execute();

			/*
			 * else { // sv.add("ebx", 2130567168); // sv.add("ebx", 0); //
			 * sv.add("counter", 0); // sv.add("eax", 2000565080); //
			 * sv.add("eax", 0); // sv.add("al", 0);
			 * 
			 * XMLParser xmlparser = new XMLParser(cfg, sv);
			 * xmlparser.WriteXMLFile();
			 * 
			 * // if (indirectTarget.toString().equals("0x00401033")) { try {
			 * Process p = Runtime.getRuntime().exec(
			 * "cmd /c start /wait run_script.bat");
			 * System.out.println("Running script PAT..."); int exitCode =
			 * p.waitFor();
			 * 
			 * System.out.println("Done script. ExitCode:" + exitCode); } catch
			 * (Throwable e) { System.out.flush(); e.printStackTrace(); //
			 * Runtime.getRuntime().removeShutdownHook(shutdownThread); // Kills
			 * eclipse shutdown thread // System.exit(1); return -1; }
			 * 
			 * // } String indirect = xmlparser.readXML("result.xml"); if
			 * (isNumeric(indirect)) result = Long.parseLong(indirect); }
			 */

			// AbsoluteAddress a = new AbsoluteAddress(result);
			if (temp == null)
				continue;

			if (program.getAssemblyMap().containsKey(temp))
				System.out.println("This is not new area: " + temp.getValue() + " Hex value:" + temp.toString());
			else {
				result = temp;
				System.out.println("The new area of Hybrid Approach from Concrete Execution Part:" + result.getValue()
						+ " Hex value:" + result);
				System.out.println("**********************************************");
				break;

			}
			// break;
			// System.out.println();
			// if (indirectTarget.toString().equals("0x00401033"))
			// return 4198408;
			//
			// if (indirectTarget.toString().equals("0x00401041")) {
			// // cfg.prinCFG();
			// return 4198467;
			// }
			// if (indirect.)
			// program.get

			// else
			// return -1;

		}
		if (result == null)
			result = temp;

		if (result != null) {
			System.out.println("Chosen Result (from " + this.indirectTarget.toString() + ") of Hybrid Approach:"
					+ result.getValue() + " Hex value:" + result);

			FileProcess t = new FileProcess(this.indirectResolvedFile);
			if (!t.contain(data))
				t.appendFile(data);
		}
		return result;
	}
}
