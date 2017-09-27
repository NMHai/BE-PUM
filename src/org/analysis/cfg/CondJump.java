package org.analysis.cfg;

public class CondJump {
	// private String [] mean_op;

	public static String getCondJump(String jIns) {
		String result = "";

		if (jIns.startsWith("je") || jIns.startsWith("jz")) {
			result = "==";
			// nc.setConnector("!=");
		} else if (jIns.startsWith("jne") || jIns.startsWith("jnz")) {
			result = "!=";
			// nc.setConnector("=");
		} else if (jIns.startsWith("jb") || jIns.startsWith("jnae") || jIns.startsWith("jc")) {
			result = "<";
			// nc.setConnector(">=");
		} else if (jIns.startsWith("jnb") || jIns.startsWith("jae") || jIns.startsWith("jnc")) {
			result = ">=";
			// nc.setConnector("<");
		} else if (jIns.startsWith("jbe") || jIns.startsWith("jna")) {
			result = "<=";
			// nc.setConnector(">");
		} else if (jIns.startsWith("jle") || jIns.startsWith("jng")) {
			result = "<=";
			// nc.setConnector(">");
		} else if (jIns.startsWith("jl") || jIns.startsWith("jnge")) {
			result = "<";
			// nc.setConnector(">=");
		} else if (jIns.startsWith("ja") || jIns.startsWith("jnbe")) {
			result = ">";
			// nc.setConnector("<=");
		} else if (jIns.startsWith("jge") || jIns.startsWith("jnl")) {
			result = ">=";
			// nc.setConnector("<");
		} else if (jIns.startsWith("jg") || jIns.startsWith("jnle")) {
			result = ">";
			// nc.setConnector("<=");
		}

		return result;
	}

	public static String getReserCondJump(String jIns) {
		String result = "";

		if (jIns.startsWith("je") || jIns.startsWith("jz")) {
			result = "!=";
			// nc.setConnector("!=");
		} else if (jIns.startsWith("jne") || jIns.startsWith("jnz")) {
			result = "==";
			// nc.setConnector("=");
		} else if (jIns.startsWith("jb") || jIns.startsWith("jnae") || jIns.startsWith("jc")) {
			result = ">=";
			// nc.setConnector(">=");
		} else if (jIns.startsWith("jnb") || jIns.startsWith("jae") || jIns.startsWith("jnc")) {
			result = "<";
			// nc.setConnector("<");
		} else if (jIns.startsWith("jbe") || jIns.startsWith("jna")) {
			result = "<=";
			// nc.setConnector(">");
		} else if (jIns.startsWith("jle") || jIns.startsWith("jng")) {
			result = ">";
			// nc.setConnector(">");
		} else if (jIns.startsWith("jl") || jIns.startsWith("jnge")) {
			result = ">=";
			// nc.setConnector(">=");
		} else if (jIns.startsWith("ja") || jIns.startsWith("jnbe")) {
			result = "<=";
			// nc.setConnector("<=");
		} else if (jIns.startsWith("jge") || jIns.startsWith("jnl")) {
			result = "<";
			// nc.setConnector("<");
		} else if (jIns.startsWith("jg") || jIns.startsWith("jnle")) {
			result = "<=";
			// nc.setConnector("<=");
		}

		return result;
	}
}
