package org.analysis;

import org.jakstab.util.Logger;

import java.util.ArrayList;

/**
 * Storage structure for data value
 */
public class SymbolicValue {
	private final static Logger logger = Logger.getLogger(SymbolicValue.class);

	// type
	private int type;
	public static final int UNDEFINED = 0;
	public static final int SYMBOL = 1;
	public static final int INTEGER = 2;
	public static final int FLOAT = 3;
	public static final int BOOLEAN = 4;

	// preserved for symbolic expression
	public static final int SYMBOLIC_EXPRESSION = 5;
	public static final int ADD_EXPR = 0;
	public static final int SUB_EXPR = 1;
	public static final int MUL_EXPR = 11;
	public static final int DIV_EXPR = 12;
	public static final int AND_EXPR = 21;
	public static final int OR_EXPR = 22;

	// normal values
	private long intValue;
	private float floatValue;
	private boolean boolValue;
	private String symbolValue;

	/* symbolic values will be at the form of additions (and) */
	private ArrayList<SymbolicValue> exprValue;
	private int exprType;

	/**
	 * Initiate a new integer symbolic value
	 * 
	 * @param value
	 *            integer value
	 */
	public SymbolicValue(long value) {
		type = INTEGER;
		this.intValue = value;
		floatValue = 0;
		boolValue = false;
		symbolValue = "";
		exprValue = new ArrayList<SymbolicValue>();
		exprType = ADD_EXPR;
	}

	/**
	 * Initiate a new boolean symbolic value
	 * 
	 * @param value
	 *            boolean value
	 */
	public SymbolicValue(boolean value) {
		type = BOOLEAN;
		intValue = 0;
		this.boolValue = value;
		floatValue = 0;
		symbolValue = "";
		exprValue = new ArrayList<SymbolicValue>();
		exprType = ADD_EXPR;
	}

	/**
	 * Initiate a new string symbolic value
	 * 
	 * @param second
	 *            string value
	 */
	public SymbolicValue(String symbol) {
		type = SYMBOL;
		intValue = 0;
		floatValue = 0;
		boolValue = false;
		symbolValue = symbol;
		exprValue = new ArrayList<SymbolicValue>();
		exprType = ADD_EXPR;
	}

	/**
	 * Initiate a new symbolic value that refer to structure of another symbolic
	 * value
	 * 
	 * @param second
	 *            to-be-refered symbolic value
	 */
	public SymbolicValue(SymbolicValue element) {
		type = SYMBOLIC_EXPRESSION;
		intValue = 0;
		boolValue = false;
		floatValue = 0;
		symbolValue = "";
		exprValue = new ArrayList<SymbolicValue>();
		exprValue.add(element);
		exprType = ADD_EXPR;
	}

	/**
	 * Initiate a new symbolic value
	 */
	public SymbolicValue() {
		type = UNDEFINED;
		intValue = 0;
		floatValue = 0;
		boolValue = false;
		symbolValue = "";
		exprValue = new ArrayList<SymbolicValue>();
		exprType = ADD_EXPR;
	}

	/**
	 * Change type of symbolic value
	 * 
	 * @param type
	 *            type to be updated
	 */
	public void setType(int type) {
		if (type >= UNDEFINED && type <= SYMBOLIC_EXPRESSION) {
			this.type = type;
		} else {
			logger.error("INVALID TYPE");
		}
	}

	/**
	 * Generate a new symbol value
	 * 
	 * @see SymbolGenerator
	 */
	public void initialSymbolicValue() {
		type = SYMBOL;
		symbolValue = SymbolGenerator.generate();
	}

	/**
	 * @return symbolic type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return integer value (must refer type before using this value)
	 */
	public long getIntValue() {
		return intValue;
	}

	/**
	 * @return float value (must refer type before using this value)
	 */
	public float getFloatValue() {
		return floatValue;
	}

	/**
	 * @return boolean value (must refer type before using this value)
	 */
	public boolean getBoolValue() {
		return boolValue;
	}

	/**
	 * @return type of symbolic expression (must refer type before using this
	 *         value)
	 */
	public int getExprType() {
		return exprType;
	}

	/**
	 * @return expression list (must refer type before using this value)
	 */
	public ArrayList<SymbolicValue> getExprValue() {
		return exprValue;
	}

	/**
	 * @return symbol value (must refer type before using this value)
	 */
	public String getSymbolValue() {
		return symbolValue;
	}

	/**
	 * update integer value
	 * 
	 * @param value
	 *            integer value
	 */
	public void setValue(long value) {
		intValue = value;
	}

	/**
	 * update float value
	 * 
	 * @param value
	 *            float value
	 */
	public void setValue(float value) {
		floatValue = value;
	}

	/**
	 * update boolean value
	 * 
	 * @param value
	 *            boolean value
	 */
	public void setValue(boolean value) {
		boolValue = value;
	}

	/**
	 * update string value
	 * 
	 * @param value
	 *            string value
	 */
	public void setValue(String value) {
		symbolValue = value;
	}

	/**
	 * update expression type
	 * 
	 * @param type
	 *            expression type to be updated
	 */
	public void setExprTypeValue(int type) {
		exprType = type;
	}

	/**
	 * add new expression to expression list
	 * 
	 * @param exprType
	 *            type of updated expression
	 * @param value
	 *            expression value
	 */
	public void addExprValue(int exprType, SymbolicValue value) {
		if (exprType == ADD_EXPR || exprType == SUB_EXPR || exprType == DIV_EXPR || exprType == MUL_EXPR) {
			if (type == INTEGER) {
				exprValue.add(new SymbolicValue(intValue));
			} else if (type == SYMBOL) {
				exprValue.add(new SymbolicValue(symbolValue));
			} else if (type != SYMBOLIC_EXPRESSION) {
				logger.error("Mismatch expression type!");
				return;
			}
		} else if (exprType == AND_EXPR || exprType == OR_EXPR) {
			if (type == SYMBOL) {
				exprValue.add(new SymbolicValue(symbolValue));
			} else if (type == BOOLEAN) {
				exprValue.add(new SymbolicValue(boolValue));
			} else if (type != SYMBOLIC_EXPRESSION) {
				logger.error("Mismatch expression type!");
				return;
			}
		} else {
			if (exprType != type) {
				logger.error("Not support expression type!");
				return;
			}
		}
		exprValue.add(value);
		this.type = SYMBOLIC_EXPRESSION;
		this.exprType = exprType;
	}

	/**
	 * check whether symbolic value can be resolved to an integer value or not
	 * 
	 * @return true if all expression can be calculated, false otherwise
	 */
	private boolean isIntegerExpression() {
		if (type == INTEGER || type == FLOAT) {
			return true;
		} else if (type != SYMBOLIC_EXPRESSION) {
			return false;
		}
		boolean res = true;
		for (int i = 0; i < exprValue.size(); i++) {
			res &= exprValue.get(i).isIntegerExpression();
		}
		return res;
	}

	/**
	 * resolve symbolic value to an integer value
	 * 
	 * @return Long value if success, null otherwise
	 */
	public Long calculateExprIntVal() {
		if (!isIntegerExpression()) {
			return null;
		}
		if (type == INTEGER) {
			return new Long(intValue);
		}
		if (type == FLOAT) {
			return new Long(Math.round(floatValue));
		}
		long result = 0;
		for (int i = 0; i < exprValue.size(); i++) {
			if (exprType == ADD_EXPR) {
				result += exprValue.get(i).calculateExprIntVal();
			} else if (exprType == SUB_EXPR) {
				result -= exprValue.get(i).calculateExprIntVal();
			} else {
				return null;
			}
		}
		return new Long(result);
	}

	/**
	 * exchange one kind of symbol in expression list to new symbol (preserved
	 * for loop rechecking)
	 * 
	 * @param oldSym
	 *            old symbol to be deleted
	 * @param newSym
	 *            new symbol to be updated
	 */
	public void exchangeSymbol(String oldSym, String newSym) {
		if (type == SYMBOL) {
			if (symbolValue.equals(oldSym)) {
				symbolValue = newSym;
			}
		}
		if (type == SYMBOLIC_EXPRESSION) {
			for (int i = 0; i < exprValue.size(); i++) {
				exprValue.get(i).exchangeSymbol(oldSym, newSym);
			}
		}
	}

	/**
	 * resolve symbolic value to a string
	 */
	public String toString() {
		String tmpString = "";
		switch (type) {
		case UNDEFINED:
			return "...";
		case SYMBOL:
			return symbolValue.toUpperCase();
		case INTEGER:
			if (intValue < 0) {
				tmpString = "- ";
			}
			return (tmpString + Math.abs(intValue));
		case FLOAT:
			if (floatValue < 0) {
				tmpString = "- ";
			}
			return (tmpString + Math.abs(floatValue));
		case BOOLEAN:
			return Boolean.toString(boolValue).toUpperCase();
		case SYMBOLIC_EXPRESSION:
			String ret = "";
			if (exprValue != null) {
				for (int i = 0; i < exprValue.size(); i++) {
					if (exprValue.get(i).type == SYMBOLIC_EXPRESSION && exprValue.get(i).exprValue.size() > 1
							&& exprValue.get(i).exprType / 10 < exprType / 10) {
						ret += "(" + exprValue.get(i).toString() + ")";
					} else {
						String toAppend = exprValue.get(i).toString();
						int lastNonSpace = -1;
						for (int j = ret.length() - 1; j >= 0; j--) {
							if (ret.charAt(j) != ' ') {
								lastNonSpace = j;
								break;
							}
						}
						if (lastNonSpace != -1) {
							int firstNonSpace = -1;
							for (int j = 0; j < toAppend.length(); j++) {
								if (toAppend.charAt(j) != ' ') {
									firstNonSpace = j;
									break;
								}
							}
							if (firstNonSpace != -1) {
								// exchange signs between '+' and '-'
								if ((ret.charAt(lastNonSpace) == '+' || ret.charAt(lastNonSpace) == '-')
										&& (toAppend.charAt(firstNonSpace) == '-' || toAppend.charAt(firstNonSpace) == '+')) {
									ret = ret.substring(0, lastNonSpace);
									toAppend = toAppend.substring(firstNonSpace);
								}
							}
						}
						ret += toAppend;
					}
					if (i < exprValue.size() - 1) {
						switch (exprType) {
						case ADD_EXPR:
							ret += " + ";
							break;
						case SUB_EXPR:
							ret += " - ";
							break;
						case MUL_EXPR:
							ret += " * ";
							break;
						case DIV_EXPR:
							ret += " / ";
							break;
						case AND_EXPR:
							ret += " && ";
							break;
						case OR_EXPR:
							ret += " || ";
							break;
						default:
							ret += " " + exprType + " ";
						}
					}
				}
			}
			return ret;
		}
		return "";
	}

	/**
	 * clone a new symbolic value with the same structure
	 */
	public SymbolicValue clone() {
		SymbolicValue result = new SymbolicValue();
		result.setValue(intValue);
		result.setValue(boolValue);
		result.setValue(symbolValue);
		if (exprValue != null && exprValue.size() > 0) {
			result.setType(SYMBOLIC_EXPRESSION);
			result.setExprTypeValue(exprType);
			for (int i = 0; i < exprValue.size(); i++) {
				result.addExprValue(exprType, exprValue.get(i).clone());
			}
		}
		result.setType(type);
		return result;
	}
}
