/**
 * 
 */
package v2.org.analysis.system;

import v2.org.analysis.file.FileImage;

/**
 * This class defines the abstraction of one file
 * 
 * @author NMHai
 * 
 */
public class File {
	final static int MAX_FILE_CONTENT = 100;
	private String fileName = "";
	private long access, shareMode, pSecurity, mode, attribute, hTemplateFile, fileHandle;
	private boolean open = true;
	private long searchHandle = 0;
	private String path = "";
	private FileImage fileImage;
	private FilePointer fPointer;
	private MapView mapView;

	private byte[] content = new byte[MAX_FILE_CONTENT];
	private int size = 0;

	public byte getContent(int index) {
		if (index < size)
			return content[index];

		return 0;
	}

	public byte[] getContent() {
		return content;
	}

	public String getContent(long x2, long x3, long x4) {
		return content.toString();
	}

	public int copyContent(byte[] c) {
		if (c.length > MAX_FILE_CONTENT)
			return 0;

		for (int i = 0; i < c.length; i++)
			content[i] = c[i];

		return 1;
	}

	public boolean insertContent(byte c) {
		if (size < MAX_FILE_CONTENT - 1) {
			content[size++] = c;
			return true;
		}

		return false;
	}

	public boolean insertContent(byte[] c) {
		if (size < MAX_FILE_CONTENT - c.length) {
			for (int i = 0; i < c.length; i++)
				content[size + i] = c[i];

			size += c.length;
			return true;
		}

		return false;
	}

	public void closeFile() {
		open = false;
	}

	public boolean getStatus() {
		return open;
	}

	public File(String fileName, long access, long shareMode, long pSecurity, long mode, long attribute,
			long hTemplateFile, long fHandle) {
		super();
		this.fileName = fileName;
		this.access = access;
		this.shareMode = shareMode;
		this.pSecurity = pSecurity;
		this.mode = mode;
		this.attribute = attribute;
		this.hTemplateFile = hTemplateFile;
		this.fileHandle = fHandle;
		open = true;
		content = new byte[MAX_FILE_CONTENT];
		mapView = new MapView();
		readFile();
	}

	public File(String fileName, long access, long shareMode, long pSecurity, long mode, long attribute,
			long hTemplateFile, long fHandle, String path) {
		super();
		this.fileName = fileName;
		this.access = access;
		this.shareMode = shareMode;
		this.pSecurity = pSecurity;
		this.mode = mode;
		this.attribute = attribute;
		this.hTemplateFile = hTemplateFile;
		this.fileHandle = fHandle;
		this.path = path;
		open = true;
		mapView = new MapView();
		readFile();
	}

	public File() {
		// TODO Auto-generated constructor stub
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getAccess() {
		return access;
	}

	public void setAccess(long access) {
		this.access = access;
	}

	public long getShareMode() {
		return shareMode;
	}

	public void setShareMode(long shareMode) {
		this.shareMode = shareMode;
	}

	public long getpSecurity() {
		return pSecurity;
	}

	public void setpSecurity(long pSecurity) {
		this.pSecurity = pSecurity;
	}

	public long getMode() {
		return mode;
	}

	public void setMode(long mode) {
		this.mode = mode;
	}

	public long getAttribute() {
		return attribute;
	}

	public void setAttribute(long attribute) {
		this.attribute = attribute;
	}

	public long gethTemplateFile() {
		return hTemplateFile;
	}

	public void sethTemplateFile(long hTemplateFile) {
		this.hTemplateFile = hTemplateFile;
	}

	/**
	 * @return the fHandle
	 */
	public long getFileHandle() {
		return fileHandle;
	}

	/**
	 * @param fHandle
	 *            the fHandle to set
	 */
	public void setFileHandle(long fHandle) {
		this.fileHandle = fHandle;
	}

	/**
	 * @return the searchHandle
	 */
	public long getSearchHandle() {
		return searchHandle;
	}

	/**
	 * @param searchHandle
	 *            the searchHandle to set
	 */
	public void setSearchHandle(long searchHandle) {
		this.searchHandle = searchHandle;
	}

	/**
	 * @return the path
	 */
	public String getPathFile() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPathFile(String path) {
		this.path = path;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public long writeContent(String str, long t3, long t4, long t5) {
		// TODO Auto-generated method stub
		byte[] t = str.getBytes();

		for (int i = 0; i < t3; i++) {
			if (i + t4 < MAX_FILE_CONTENT)
				content[(int) (i + t4)] = t[i];
		}

		return 1;
	}

	public String getContent(long t3, long t4) {
		// TODO Auto-generated method stub
		String ret = "";
		for (int i = 0; i < t4; i++) {
			if (t4 + 1 < MAX_FILE_CONTENT)
				ret += (char) content[(int) (t4 + i)];
		}

		return ret;
	}

	/**
	 * @return the fPointer
	 */
	public FilePointer getFilePointer() {
		return fPointer;
	}

	/**
	 * @param fPointer
	 *            the fPointer to set
	 */
	public void setFilePointer(FilePointer fPointer) {
		this.fPointer = fPointer;
	}

	/**
	 * @return the mapView
	 */
	public MapView getMapView() {
		return mapView;
	}

	/**
	 * @param mapView
	 *            the mapView to set
	 */
	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public long createFileMapping(long x1, long x2, long x3, long x4, String x5) {
		this.mapView = new MapView(x1, x2, x3, x4, x5);
		mapView.setMapViewHandle(Math.round(Math.random() * Math.pow(10, 7)));

		return mapView.getMapViewHandle();
	}

	public long setEndOfFile() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * @return the fileImage
	 */
	public FileImage getFileImage() {
		return fileImage;
	}

	/**
	 * @param fileImage
	 *            the fileImage to set
	 */
	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}

	public void readFile() {
		// TODO Auto-generated method stub
		if (!fileName.equals("demo.exe"))
			fileName = "demo.exe";

		fileImage = new FileImage("data/data/" + fileName);
	}

	public long createFileMapping(long t2, long t3, long t4, long t5, long t6) {
		// TODO Auto-generated method stub
		this.mapView = new MapView(t2, t3, t4, t5, t6);
		mapView.setMapViewHandle(Math.round(Math.random() * Math.pow(10, 7)));

		return mapView.getMapViewHandle();
	}
}
