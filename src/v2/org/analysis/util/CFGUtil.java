package v2.org.analysis.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.PCRelativeAddress;
import org.jakstab.asm.x86.X86ArithmeticInstruction;
import org.jakstab.asm.x86.X86CallInstruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MoveInstruction;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.value.LongValue;

public class CFGUtil {
	
	private static String jsonCFGPath = "asm/cfg/";
	
	@SuppressWarnings("unchecked")
	public void GenerateJSONbyCFG(Program prog)
	{
		JSONObject model = new JSONObject();
		
		// NODE
		JSONArray nodes = new JSONArray();
		List<BPVertex> vList = prog.getBPCFG().getVertecesList();
		for (BPVertex vertex : vList) {
			if (vertex.getAddress() == null) continue;
			JSONObject node = new JSONObject();
			node.put("loc", vertex.getAddress().toString());
			node.put("inst", generateString(vertex.getInstruction(), vertex));
			nodes.add(node);
		}
		
		// EDGE
		JSONArray edges = new JSONArray();
		List<BPEdge> vEdge = prog.getBPCFG().getEdgesList();
		for (int i = 0; i < vEdge.size(); i++) {
			AbsoluteAddress srcAddr = vEdge.get(i).getSourceVertex().getAddress();
			AbsoluteAddress destAddr = vEdge.get(i).getDestVertex().getAddress();
			JSONObject edge = new JSONObject();
			if (destAddr == null)
			{
				if (i == vEdge.size() - 1) break;
				edge.put("src", srcAddr.toString());
				edge.put("dest", vEdge.get(++i).getDestVertex().getAddress().toString());
			}
			else {
				edge.put("src", srcAddr.toString());
				edge.put("dest", destAddr.toString());
			}
			edges.add(edge);
		}
		
		model.put("nodes", nodes);
		model.put("edges", edges);
		
		FileWriter file;
		try {
			file = new FileWriter(jsonCFGPath + prog.getFileName() + ".json");
			file.write(model.toJSONString());
			System.out.println("Successfully generated CFG by JSON !!!");
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String generateString (Instruction instr, BPVertex vertex)
	{
		if (instr == null)
			return "null";

		String r = "";
		if (instr instanceof X86Instruction) {
			X86Instruction i = (X86Instruction) instr;
			if (i.hasPrefixREPZ())
				r += "rep ";
			else if (i.hasPrefixREPNZ())
				r += "repn ";
		} else if (instr instanceof X86ArithmeticInstruction) {
			X86ArithmeticInstruction i = (X86ArithmeticInstruction) instr;
			if (i.hasPrefixREPZ())
				r += "rep ";
			else if (i.hasPrefixREPNZ())
				r += "repn ";
		} else if (instr instanceof X86MoveInstruction) {
			X86MoveInstruction i = (X86MoveInstruction) instr;
			if (i.hasPrefixREPZ())
				r += "rep ";
			else if (i.hasPrefixREPNZ())
				r += "repn ";
		} else if (instr instanceof X86CallInstruction) {
			X86CallInstruction i = (X86CallInstruction) instr;
			if (i.hasPrefixREPZ())
				r += "rep ";
			else if (i.hasPrefixREPNZ())
				r += "repn ";
		} else if (instr instanceof X86CondJmpInstruction) {
			X86CondJmpInstruction i = (X86CondJmpInstruction) instr;
			if (i.hasPrefixREPZ())
				r += "rep ";
			else if (i.hasPrefixREPNZ())
				r += "repn ";
		}

		r += instr.getName();
		if (!vertex.getProperty().contains("@")) {
			int i = instr.getOperandCount();
			if (i == 1) {
				if (instr.getOperand(0) instanceof PCRelativeAddress)
				{
					r += " " + addressEvaluation(vertex.getAddress(), (PCRelativeAddress)instr.getOperand(0));
				}
				else 
				{
					r += " " + instr.getOperand(0).toString();
				}
			} else if (i == 2)
				r += " " + instr.getOperand(0).toString() + ", " + instr.getOperand(1).toString();
			else if (i == 3)
				r += " " + instr.getOperand(0).toString() + ", " + instr.getOperand(1).toString() + ", "
						+ instr.getOperand(2).toString();

		} else {
			r += " " + vertex.getProperty();
		}
		return r;
	}
	
	private String addressEvaluation (AbsoluteAddress iAddr, PCRelativeAddress rAddr)
	{
		long addr = (new LongValue(((X86PCRelativeAddress) rAddr).getEffectiveValue(iAddr.getValue()))).getValue();
		return (new AbsoluteAddress(addr)).toString();
	}
	
}