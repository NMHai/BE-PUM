package org.analysis.signapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zunc
 */
public class FlirtDumpReader {

	private String _strPath;
	private BufferedReader _br;
	private Node _root;
	private int _nApi;
	private int _nErrParse;
	private Map<String, Node> _mapAPI = new HashMap<>();

	public FlirtDumpReader(String path) throws FileNotFoundException {
		_strPath = path;
		FileInputStream fis = new FileInputStream(new File(_strPath));
		_br = new BufferedReader(new InputStreamReader(fis));
		_root = new Node(false);
		Path p = Paths.get(path);
		_root.setLibName(p.getFileName().toString());
		_root.setRoot();
	}

	public Map<String, Node> getMapAPI() {
		return this._mapAPI;
	}

	private int countIndent(String line) {
		int nSpace = 0;
		while (line.charAt(nSpace) == ' ') {
			nSpace++;
		}
		return nSpace / 2 + 1;
	}

	private boolean isInternal(String line) {
		// format: 5589E5:
		return line.endsWith(":");
	}

	private Node getNodeByIndent(Node curNode, int indent) {
		Node cur = curNode;
		Node newNode = new Node();
		if (indent > curNode.getIndent()) {
			curNode.addChild(newNode);
			return newNode;
		}
		while (cur != null && cur.getIndent() != indent) {
			cur = cur.getParent();
		}
		cur.getParent().addChild(newNode);
		return newNode;
		//return cur;
	}

	public Node parse() throws IOException {
		Node curNode = _root;
		String line;
		curNode.setMapAPI(_mapAPI);
		while ((line = _br.readLine()) != null) {
			int nIdt = countIndent(line);
			line = line.trim();
			boolean isInternalNode = isInternal(line);
			Node node = getNodeByIndent(curNode, nIdt);
			node.setIsLeaf(!isInternalNode);
			if (isInternalNode) {
				String sign = line.substring(0, line.length() - 1);
				node.setSign(sign);
			} else {
				// API info
				// 0. 00 0000 019E 0000:setservent 003C:endservent 006C:getservent (016A: C7)
				line = line.split(". ", 2)[1];
				String strCrc16 = line.substring(0, 7);
				int crcLen = Integer.parseInt(strCrc16.substring(0, 2), 16);
				int crc16 = Integer.parseInt(strCrc16.substring(3, 7), 16);
				// set crc16
				node.setCRC16Len(crcLen);
				node.setCRC16(crc16);

				String stdLine = line.substring(13);
				//<!> dirty code
				stdLine = stdLine.replace("(?)", "");
				stdLine = stdLine.replace("(l?)", "");
				stdLine = stdLine.replace("(l)", "");
				stdLine = stdLine.replace("(!)", "");
				stdLine = stdLine.replace("(?!)", "");
				stdLine = stdLine.replace("(l!)", "");
				stdLine = stdLine.replace("(d)", "");
				Scanner sc = new Scanner(stdLine);
				// [(\?)]?\w+:[\w~\.\@\%\$:()_?]+( [\(]\w+: \w+[\)])?( \([\w]+\))?( \(([\w\%\@\ \:?$()\[\]])+\))?
				Pattern pt = Pattern.compile("[(\\?)]?\\w+:[\\w~\\.\\@\\%\\$:()_?]+( [\\(]\\w+: \\w+[\\)])?( \\([\\w]+\\))?( \\(([\\w\\%\\@\\ \\:?$()\\[\\]])+\\))?");
				Matcher matcher = pt.matcher(stdLine);
				boolean isMatch = false;
				int cnt = 0;
				// matching each api
				while (matcher.find()) {
					if (cnt > 0) {
						// new node
						try {
							node = getNodeByIndent(curNode, nIdt);
							node.setIsLeaf(true);
							// set crc16 for new node
							node.setCRC16Len(crcLen);
							node.setCRC16(crc16);
						} catch (Exception ex) {
							System.out.println("parse_ex: " + ex);
						}
					}
					cnt++;
					String group = matcher.group();
					String basicSign = group;
					String extInfo = "";
					int posSpace = basicSign.indexOf(' ');
					if (posSpace > 0) {
						String[] arStr = basicSign.split(" ", 2);
						basicSign = arStr[0];
						extInfo = arStr[1];
					}
					basicSign = basicSign.trim();
					String[] arDetail = basicSign.split(":");
					try {
						String strRefOffset = arDetail[0];
						int offset = Integer.parseInt(strRefOffset, 16);
						String apiName = arDetail[1];
						node.setRefCurOffset(offset);
						node.setAPIName(apiName);

						_mapAPI.put(apiName, node);
						//--- extend signature
						if (!extInfo.isEmpty()) {
							String[] arExt = extInfo.split("\\) ");
							for (String strExt : arExt) {
								try {
									boolean isRef = false;
									String strStd = strExt.substring(1, strExt.length() - 1);

									int posRef = strStd.indexOf("REF ");
									if (posRef >= 0) {
										strStd = strStd.substring(4);
										isRef = true;
									}
									String[] reExt = strStd.split(":", 2);
									if (reExt.length == 2) {
										String strLeft = reExt[0];
										int left = Integer.parseInt(strLeft, 16);
										String right = reExt[1].trim();
										if (isRef) {
											node.setRefOffset(left);
											node.setRefAPI(right);
										} else {
											node.setExtOffset(left);
											int val = Integer.parseInt(right, 16);
											byte bVal = (byte) val;
											node.setExtByte(bVal);
										}
									}
								} catch (Exception ex) {
									System.out.println("ERR_REF: " + ex);
								}
							}
						}
						_nApi++;
					} catch (Exception ex) {
//						System.out.println("err_function: " + basicSign);
						_nErrParse++;
					}
//					node.printLeaf();
					isMatch = true;

					if (node.getAPIName().isEmpty()) {
						//System.out.println("WTF");
					}
				}

				if (!isMatch) {
					System.out.println("[-] Wrong API matching: " + stdLine);
				}
			}
			curNode = node;
		}
		return _root;
	}

	public int getNApi() {
		return this._nApi;
	}

	public int getErrorParse() {
		return this._nErrParse;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// for test
		//--- file
		String path = "IDASig/dump/wa16rtw.sig.dump";
		FlirtDumpReader rdr = new FlirtDumpReader(path);
		rdr.parse();
		if (rdr.getErrorParse() > 0) {
			System.out.println(String.format(" - error: %d/%d", rdr.getErrorParse(), rdr.getNApi()));
		}

		//--- dir
		String sigDir = "vmshare/IDASig/dump";
		File dir = new File(sigDir);
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".dump");
			}
		});

		List<Node> lstRoot = new ArrayList<>();
		int cnt = 0;
		int errAPI = 0;
		int successAPI = 0;
		Map<String, Node> mapAllAPI = new HashMap<>();
		for (File file : files) {
			String sigPath = file.getAbsolutePath();
			System.out.println("Path:" + sigPath);
			FlirtDumpReader reader = new FlirtDumpReader(sigPath);
			Node node = reader.parse();
			if (node != null) {
				lstRoot.add(node);
				cnt++;
			}
			if (reader.getErrorParse() > 0) {
				System.out.println(String.format(" - error: %d/%d", reader.getErrorParse(), reader.getNApi()));
			}

			node.setAllMapAPI(mapAllAPI);
			mapAllAPI.putAll(reader.getMapAPI());
			errAPI += reader.getErrorParse();
			successAPI += reader.getNApi();
		}
		System.out.println(cnt + " / " + files.length);
		System.out.println(String.format(" - error/success: %d/%d", errAPI, successAPI));
		System.out.println("DONE");
	}
}
