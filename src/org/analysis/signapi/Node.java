package org.analysis.signapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.jakstab.asm.AbsoluteAddress;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.value.LongValue;

/**
 *
 * @author zunc
 */
public class Node {

	private String _sign = "";
	private boolean _isLeaf = false;
	private List<Node> _child = new ArrayList<>();
	private Node _parent = null;
	private int _indent;
	private String _libName;

	// leaf node
	private String _apiName = "";
	private int _blockLen;
	private int _totalLen;
	private boolean _isSymbolLinked = false;
	private int _refCurOffset = 0;

	// crc16
	private int _crc16len = 0;
	private int _crc16;

	// ext signature
	private int _extOffset = -1;
	private byte _extByte;

	// ref API
	private int _refOffset = -1;
	private String _refAPI = "";

	private Map<String, Node> _mapApi;
	public Map<String, Node> _mapAllApi;

	public Node() {

	}

	public Node(boolean isLeaf) {
		this._isLeaf = isLeaf;
	}

	public void addByte(byte b) {
		this._sign += String.format("%02x", b);
	}

	public String getSign() {
		return _sign;
	}

	public void addDot() {
		this._sign += "..";
	}

	public void setMapAPI(Map<String, Node> map) {
		this._mapApi = map;
	}

	public Map<String, Node> getMapAPI() {
		return this._mapApi;
	}

	public void setAllMapAPI(Map<String, Node> map) {
		this._mapAllApi = map;
	}

	public Map<String, Node> getMapAllAPI() {
		return this._mapAllApi;
	}

	public void setIsLeaf(boolean isLeaf) {
		this._isLeaf = isLeaf;
	}

	public void setSign(String sign) {
		this._sign = sign;
	}

	public void addChild(Node node) {
		node.setParent(this);
		this._child.add(node);
		node.setIndent(_indent + 1);
	}

	static final List<String> WHITELIST;

	static {
		WHITELIST = new ArrayList<>();
//		WHITELIST.add("gets");
		WHITELIST.add("scanf");
//		WHITELIST.add("puts");
//		WHITELIST.add("printf");
		WHITELIST.add("len");
		WHITELIST.add("free");
	}

	private boolean isWhitelist(String apiName) {
		return true;
//		for (String white : WHITELIST) {
//			if (apiName.contains(white)) {
//				return true;
//			}
//		}
//		return false;
	}

	private byte getByte(MemoryV2 mem, long addr) {
		return (byte) ((LongValue) mem.getByteMemoryValue(addr)).getValue();
	}

	private String getSignTest(MemoryV2 mem, long addr, int len) {
		byte[] bytesArray = mem.getBytesArray(new AbsoluteAddress(addr), len);
		StringBuilder sb = new StringBuilder();
		for (byte b : bytesArray) {
			sb.append(String.format("%x", b));
		}
		return sb.toString();
	}

	private boolean _matchSign(String sign, MemoryV2 mem, long addr) {
		int sizeSign = sign.length();
		for (int i = 0; i < sizeSign; i += 2) {
			long pos = addr + i / 2;
			String hex = sign.substring(i, i + 2);
			if (hex.equals("..")) {
				// pass
			} else {
				int val = Integer.parseInt(hex, 16);
				byte bVal = (byte) val;
				byte bMem = getByte(mem, pos);
				if (bMem != bVal) {
					return false;
				}
			}

		}
		return true;
	}

	private boolean _isDbgLib() {
		return this._libName.startsWith("msfps40.sig");
	}

	public Node match(MemoryV2 mem, long addr) {
//		if (_isDbgLib() && addr == 0x4029c8) {
//			System.out.println("0xBEEF");
//		}
		return match(mem, addr, false);
	}

	private Node match(MemoryV2 mem, long addr, boolean refSign) {
		String fullSign = getFullSignature();
		// matching: leaf
		if (this._isLeaf) {
			//return isWhitelist(_apiName) ? this : null;

//			if (this._apiName.equals("_initterm")) {
//				System.out.println("TRACE");
//			}

			//--- matching signature by ref
			if (refSign) {
				String fSign = getFullSignature();
				String signTest = getSignTest(mem, addr, fSign.length() / 2);
				if (!_matchSign(fSign, mem, addr)) {
					return null;
				} else {
//					System.out.println("[DBG]--- RefSign");
//					System.out.println("[DBG]\t " + fSign);
//					System.out.println("[DBG]\t " + signTest);
				}
			}

			//--- matching crc16
			if (_crc16len > 0) {
				byte[] data = mem.getBytesArray(new AbsoluteAddress(addr), _crc16len);
				Integer crc16Result = Utils.crc16(data);
				if (crc16Result != _crc16) {
					return null;
				}
			}

			//--- ext rule offset sign
			if (_extOffset >= 0) {
				try {
					byte bVal = getByte(mem, addr + _extOffset);
					if (bVal != _extByte) {
						return null;
					}
				} catch (Exception ex) {
					System.out.println("match=" + ex);
				}
			}

			//--- ext rule offset sign
			if (_refOffset >= 0) {
				long pos = addr + _refOffset;
				Node root = getRoot();
				Map<String, Node> mapApi = root.getMapAPI();
				if (mapApi == null || root._mapAllApi == null || _refAPI == null) {
					System.out.println("[!] Flirt::match: Something bad happened");
				}
				Node refNode = mapApi.containsKey(_refAPI)
						? mapApi.get(_refAPI) : root._mapAllApi.get(_refAPI);
				if (refNode == null) {
//					System.out.println(String.format("[!] WTF.match: API=%s, not found in signture", _refAPI));
					return null;
				}
				if (refNode.match(mem, pos, true) == null) {
					return null;
				}
			}
			
			if (!refSign) {
				String api = this._apiName;
				// API filter
				if (api.startsWith("?") || api.startsWith("@")) {
					return null;
				}
			}
			return this;
		}

		// matching: current node
		if (!_matchSign(_sign, mem, addr)) {
			return null;
		}

		// matching: children nodes
		long nextAddr = addr + _sign.length() / 2;
		for (Node child : _child) {
			Node nodeMatch = child.match(mem, nextAddr, false);
			if (nodeMatch != null) {
				Node root = this.getRoot();
//				System.out.println(String.format("[DBG] 0x%x: %s@%s sign=%s",
//						addr, nodeMatch.getAPIName(), root.getLibName(),
//						nodeMatch.getFullSignature()));
				return nodeMatch;
			}
		}
		return null;
	}

	public void setParent(Node parent) {
		this._parent = parent;
	}

	public Node getParent() {
		return this._parent;
	}

	public void setAPIName(String apiName) {
		this._apiName = apiName;
	}

	public String getAPIName() {
		return this._apiName;
	}

	//---
	public void setExtByte(byte extByte) {
		this._extByte = extByte;
	}

	public byte getExtByte() {
		return this._extByte;
	}

	public void setExtOffset(int extOffset) {
		this._extOffset = extOffset;
	}

	public int getExtOffset() {
		return this._extOffset;
	}

	//---
	public void setRefAPI(String refAPI) {
		this._refAPI = refAPI;
	}

	public String getRefAPI() {
		return this._refAPI;
	}

	public void setRefOffset(int refOffset) {
		this._refOffset = refOffset;
	}

	public int getRefOffset() {
		return this._refOffset;
	}

	public void setLibName(String libName) {
		this._libName = libName;
	}

	public String getLibName() {
		return this._libName;
	}

	public void setBlockLen(int blockLen) {
		this._blockLen = blockLen;
	}

	public void setTotalLen(int _totalLen) {
		this._totalLen = _totalLen;
	}

	public void setCRC16Len(int len) {
		this._crc16len = len;
	}

	public void setCRC16(int crc16) {
		this._crc16 = crc16;
	}

	public void setSymbolLinked(boolean isSymbolLinked) {
		this._isSymbolLinked = isSymbolLinked;
	}

	public void setRefCurOffset(int refCurOffset) {
		this._refCurOffset = refCurOffset;
	}

	public int getIndent() {
		return this._indent;
	}

	public boolean isRoot() {
		return _indent == 0;
	}

	public void setRoot() {
		this._indent = 0;
	}

	public void setIndent(int indent) {
		this._indent = indent;
	}

	public Node getRoot() {
		Node cur = this;
		while (cur.getParent() != null) {
			cur = cur.getParent();
		}
		return cur;
	}

	public String getFullSignature() {
		List<Node> path = new ArrayList<>();
		Node cur = this;
		while (cur != null) {
			path.add(cur);
			cur = cur.getParent();
		}
		Collections.reverse(path);
		String sign = "";
		for (Node n : path) {
			sign += n.getSign();
		}
		return sign;
	}

	public void printLeaf() {
		List<Node> path = new ArrayList<>();
		Node cur = this;
		while (cur != null) {
			path.add(cur);
			cur = cur.getParent();
		}
		Collections.reverse(path);
		String sign = "";
		for (Node n : path) {
			sign += n.getSign();
		}
//		if (_apiName.equals("tmpnam")) {
//			System.out.println("DEBUG");
//		} else if (_apiName.equals("setlogmask")) {
//			System.out.println("DEBUG");
//		}

		System.out.println(String.format("%s : refCurOffset=%04x crc=%04x %s",
				sign, _refCurOffset, _crc16, _apiName));
	}

}
