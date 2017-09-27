/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.analysis.signapi;

/**
 *
 * @author zunc
 */
public class APIInfo {

	private String _name;
	private String _libName;
	private int _nstack;
	private boolean _isProduceSymbolic = false;

	public APIInfo(String name, String libName) {
		this._name = name;
		this._libName = libName;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getName() {
		return this._name;
	}
	
	public void setLibName(String libName) {
		this._libName = libName;
	}

	public String getLibName() {
		return this._libName;
	}

	public void setNStack(int nstack) {
		this._nstack = nstack;
	}

	public int getNStack() {
		return this._nstack;
	}

	public void setIsProduceSymbolic(boolean isProduceSymbolic) {
		this._isProduceSymbolic = isProduceSymbolic;
	}

	public boolean getIsProduceSymbolic() {
		return this._isProduceSymbolic;
	}

}
