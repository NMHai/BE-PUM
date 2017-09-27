/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.analysis.signapi;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import v2.org.analysis.environment.memory.MemoryV2;

/**
 *
 * @author zunc
 */
public class FlirtMatch {

	private String _signDir;
	private int _errAPI = 0;
	private int _successAPI = 0;
	private List<Node> _lsSign = new ArrayList<>();
	private static boolean _isUse = false;
	private static FlirtMatch _instance = null; // singleton
	private Map<String, Node> _mapAllAPI = new HashMap<>();

	public FlirtMatch(String signDir) {
		this._signDir = signDir;
		_instance = this;
	}

	public static boolean isUse() {
		return _isUse;
	}

	public static void setIsUse(boolean isUse) {
		_isUse = isUse;
	}

	public static FlirtMatch getInstance() {
		return _instance;
	}

	public boolean init() {
		if (!_lsSign.isEmpty()) {
			System.out.println("FlirtMatch had init");
			return false;
		}
		File dir = new File(this._signDir);
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".dump");
			}
		});

		try {
			for (File file : files) {
				String sigPath = file.getAbsolutePath();
				//System.out.println("Path:" + sigPath);
				FlirtDumpReader reader = new FlirtDumpReader(sigPath);
				Node node = reader.parse();
				if (node != null) {
					_lsSign.add(node);
				}
				node._mapAllApi = _mapAllAPI;
				_mapAllAPI.putAll(node.getMapAPI());
				this._errAPI += reader.getErrorParse();
				this._successAPI += reader.getNApi();
			}
			return true;
		} catch (Exception ex) {
			System.out.println("FlirtMatch.init failed: " + ex.toString());
		}
		System.out.println("[+] FlirtMatch loaded: Number of API signtuare = " + this._successAPI);
		return false;
	}

	public String match(MemoryV2 mem, long address) {
		String result = ""; // empty for not found
		for (Node root : _lsSign) {
			Node ret = root.match(mem, address);
			if (ret != null) {
				Node nRoot = ret.getRoot();
				return ret.getAPIName() + "@" + nRoot.getLibName();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// 5589E50FB6450B500FB6450A500FB64509500FB645085068........6A1268
		String hexTest = "5589E5B89E000000CD8089C285D27D10F7DA52E8120045675A8910B8FFFFFFFF";
		byte[] bTest = Utils.hex2byte(hexTest);
		FlirtMatch fmatch = new FlirtMatch("/media/khoai/data/vmshare/IDASig/dump/test");
		fmatch.init();

//		String matchResult = fmatch.match(bTest);
//		System.out.println("matchResult=" + matchResult);
		System.out.println("DONE");
	}

}
