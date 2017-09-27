package org.analysis;

import java.util.ArrayList;

/**
 * control symbols for symbolic value
 * 
 * @author Binh Ngo
 * 
 */
public class SymbolGenerator {

	private static int symbolIndex = 0;
	private static final String symbolPrefix = "SYM_";
	private static ArrayList<String> generatedSymbols = null;

	/**
	 * Generate a new symbols and add to database Symbol form is SYM_[index]
	 * 
	 * @return new symbol
	 */
	public static String generate() {
		if (generatedSymbols == null) {
			generatedSymbols = new ArrayList<String>();
		}
		String result = symbolPrefix + symbolIndex++;
		generatedSymbols.add(result);
		return result;
	}

	/**
	 * Get the symbol database
	 * 
	 * @return all generated symbols
	 */
	public static ArrayList<String> getSymbolDatabase() {
		return generatedSymbols;
	}
}
