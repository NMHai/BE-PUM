/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.value;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zunc
 */
public class SolverResult {

	private boolean _isSAT = true;
	private boolean _isReseuse  = false;
	private Map<String, Long> _result = new HashMap<>();

	public SolverResult(boolean sat) {
		_isSAT = sat;
	}	

	public SolverResult(boolean sat, boolean reseuse) {
		_isSAT = sat;
		_isReseuse = reseuse;
	}

	public SolverResult(boolean sat, Map<String, Long> result) {
		_isSAT = sat;
		_result = result;
	}
	
	public SolverResult(Map<String, Long> result) {
		_result = result;
	}

	public boolean isReseuse() {
		return _isReseuse;
	}
	
	public boolean isSAT() {
		return _isSAT;
	}

	public Map<String, Long> getResult() {
		return _result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Long> entry : _result.entrySet()) {
			sb.append(entry.getKey()).append("=").append(Long.toHexString(entry.getValue())).append("; ");
		}
		return "SAT=" + _isSAT + ", " + sb.toString();
	}
	
}
