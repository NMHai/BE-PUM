package org.analysis.concolic_testing;

import java.util.ArrayList;
import java.util.List;

public class TestCaseValue {
	private List<String> varName;
	private List<Long> varValue;
	private int index;

	public TestCaseValue() {
		varName = new ArrayList<String>();
		varValue = new ArrayList<Long>();
		index = 0;
	}

	public void add(String name, long val) {
		if (contain(name))
			varValue.set(varName.indexOf(name), val);
		else {
			varName.add(name);
			varValue.add(val);
			index++;
		}
	}

	public int getNumVar() {
		return index;
	}

	public boolean contain(String name) {
		return varName.contains(name);
	}

	public long getVarValue(String name) {
		// double r = 0;
		if (contain(name))
			return varValue.get(varName.indexOf(name));
		return Long.MIN_VALUE;
		// return 0;
	}

	public String getVarName(int index) {
		return varName.get(index);
	}

	public long getVarValue(int index) {
		return varValue.get(index);
	}

	public void printVarValue(String name) {
		// double r = 0;
		if (contain(name))
			System.out.println(name + " = " + varValue.get(varName.indexOf(name)));
		else
			System.out.println("Variable " + name + " is not found!");
		// return Double.MAX_VALUE;
	}

	public void printString() {
		System.out.println("Print the value of variables");
		for (int i = 0; i < index; i++)
			System.out.print(varName.get(i) + " = " + varValue.get(i) + ", ");
		System.out.println();
		System.out.println("################################");
	}

	public String getStringZ3() {
		String result = "";
		for (String var : varName) {
			// System.out.println(var);
			if (var.contains("ss:-116") || var.contains("-53(%eax)"))
				System.out.print("Debug");
			if (var.contains("Flag")) {
				result += "(" + var + " bool)";
				/*
				 * else if (var.startsWith("%ah") || var.startsWith("%al") ||
				 * var.startsWith("%bh") || var.startsWith("%bl") ||
				 * var.startsWith("%ch") || var.startsWith("%cl") ||
				 * var.startsWith("%dh") || var.startsWith("%dl")) { result +=
				 * "(" + var.substring(1) + " BitVec[8])"; } else if
				 * (var.startsWith("%ax") || var.startsWith("%si") ||
				 * var.startsWith("%bx") || var.startsWith("%di") ||
				 * var.startsWith("%cx") || var.startsWith("%sp") ||
				 * var.startsWith("%dx") || var.startsWith("%bp")) { result +=
				 * "(" + var.substring(1) + " BitVec[16])"; }
				 */} else {
				if (var.startsWith("%") && !var.contains(":")) {
					// %ebp
					var = var.substring(1);
				} else if (var.startsWith("0x")) {
					// 0x8a90000(%eax,%ecx)
					if (var.contains(",") && !var.contains("(,")) {
						int pos1 = var.lastIndexOf("(");
						int pos2 = var.lastIndexOf(",");
						var = "op_addr_base2_disp_" + var.substring(0, pos1) + "_" + var.substring(pos1 + 2, pos2)
								+ "_" + var.substring(pos2 + 2, var.length() - 1);
					} else if (var.contains("(%")) {
						// 0x400005f5(%edi)
						// String t[] = var.split("(");
						int pos = var.lastIndexOf("(");
						var = "op_addr_base_disp_" + var.substring(0, pos) + "_"
								+ var.substring(pos + 2, var.length() - 1);
					} else if (var.contains("(,")) {
						// 0x105(,%ecx)
						var = "op_addr_base3_disp_" + var.substring(0, var.length() - 1);
						var = var.replace("(,", "");
						var = var.replace("%", "_");
					} else
						// 0x57000000
						var = "op_addr_disp_" + var;
				} else if (var.startsWith("(%")) {
					if (var.contains(",,")) {
						// (%ecx,,4)
						int pos = var.lastIndexOf(",,");
						var = "op_addr_base_index_" + var.substring(2, pos) + "_"
								+ var.substring(pos + 2, var.length() - 1);
					} else if (var.contains(",")) {
						// (%eax,%eax)
						int pos = var.lastIndexOf(",");
						var = "op_addr_base_index_" + var.substring(2, pos - 1) + "_"
								+ var.substring(pos + 2, var.length() - 1);
						// (%ebx,%edx,8)
						var = var.replace(",", "_");
						var = var.replace("%", "");
					} else
						// (%edi)
						var = "op_addr_base_" + var.substring(2, var.length() - 1);
				} else if (var.startsWith("%") && var.contains(":(%")) {
					// %es:(%eax)
					int pos = var.lastIndexOf(":");
					var = "op_addr_base_index_disp_" + var.substring(1, pos) + "_"
							+ var.substring(pos + 3, var.length() - 1);
				} else if (var.contains(",") && var.contains("(%")) {
					int pos1 = var.lastIndexOf("(");
					int pos2 = var.lastIndexOf(",");
					var = "op_addr_base_index_base_" + var.substring(0, pos1 - 1) + "_" + var.substring(pos1 + 2, pos2)
							+ "_" + var.substring(pos2 + 2, var.length() - 1);
				} else if (var.contains(":") && var.contains("(%")) {
					// ss:-116(%ebx)
					int pos1 = var.lastIndexOf(":");
					int pos2 = var.lastIndexOf("(");
					var = "op_addr_base_index_base_index_" + var.substring(1, pos1) + "_"
							+ var.substring(pos1 + 1, pos2) + "_" + var.substring(pos2 + 2, var.length() - 1);
				} else if (var.contains("(%") && !var.contains(":") && !var.contains(",")) {
					// -53(%eax)
					int pos = var.lastIndexOf("(");
					var = "op_addr_base_index3_" + var.substring(0, pos) + "_"
							+ var.substring(pos + 2, var.length() - 1);
				} else if (var.contains(":") && var.startsWith("%")) {
					// %es:-1388509968
					int pos = var.lastIndexOf(":");
					var = "op_addr_base_index4_" + var.substring(1, pos) + "_" + var.substring(pos + 1, var.length());
				}

				var = var.replace(",", "_");
				var = var.replace("%", "");
				result += "(" + var + " BitVec[32])";
			}
		}
		return result;
	}
}
