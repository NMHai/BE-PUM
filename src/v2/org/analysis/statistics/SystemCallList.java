package v2.org.analysis.statistics;

import java.util.ArrayList;

public class SystemCallList {
	private ArrayList<String> nameList;
	private int[] num;
	private int index;
	private ArrayList<StringArrayList> nameFile;
	static int NumOfName = 1000;

	public SystemCallList() {
		super();
		index = -1;
		nameList = new ArrayList<String>();
		num = new int[NumOfName];
		nameFile = new ArrayList<StringArrayList>();
	}

	public void insert(String name, String fileName) {
		int t = contain(name);

		if (t == -1) {
			// insert new
			index++;
			nameList.add(name);
			num[index] = 1;
			nameFile.add(new StringArrayList(fileName));
		} else {
			if (nameFile.get(t).insert(fileName))
				num[t]++;
		}
	}

	private boolean check(String src, String dest) {
		String[] s1 = src.split("@");
		String[] d1 = dest.split("@");
		if (s1[0].toLowerCase().equals(d1[0].toLowerCase()) && s1[1].toLowerCase().equals(d1[1].toLowerCase()))
			return true;

		return false;
	}

	private int contain(String name) {
		int t = 0;
		for (String n : nameList) {
			if (check(n, name))
				return t;
			t++;
		}

		return -1;
	}

	public void printResult(String path) {
		FileProcess nameSysFile = new FileProcess(path + "/name.txt");
		FileProcess numSysFile = new FileProcess(path + "/num.txt");
		FileProcess nameListFile = new FileProcess(path + "/nameList.txt");

		for (int i = 0; i < nameList.size(); i++) {
			numSysFile.appendFile(String.valueOf(num[i]));
			nameSysFile.appendFile(nameList.get(i));

			StringArrayList l = nameFile.get(i);
			nameListFile.appendFile(nameList.get(i));
			for (int j = 0; j < l.size(); j++)
				nameListFile.appendFile(l.get(j));
			nameListFile.appendFile("===============================================");
		}
	}

	/*
	 * public void printExcelFile(String path) { //Print the result in Excel
	 * File. //Blank workbook XSSFWorkbook workbook = new XSSFWorkbook();
	 * 
	 * //Create a blank sheet XSSFSheet sheet =
	 * workbook.createSheet("System Call");
	 * 
	 * //This data needs to be written (Object[]) Map<String, Object[]> data =
	 * new TreeMap<String, Object[]>(); data.put("1", new Object[] {"ID",
	 * "NAME", "LASTNAME"}); data.put("2", new Object[] {1, "Amit", "Shukla"});
	 * data.put("3", new Object[] {2, "Lokesh", "Gupta"}); data.put("4", new
	 * Object[] {3, "John", "Adwards"}); data.put("5", new Object[] {4, "Brian",
	 * "Schultz"});
	 * 
	 * //Iterate over data and write to sheet Set<String> keyset =
	 * data.keySet(); int rownum = 0; for (int i=0; i < nameList.size(); i++) {
	 * Row row = sheet.createRow(rownum++); //int cellnum = 0; Cell cell =
	 * row.createCell(0); cell.setCellValue(nameList.get(i));
	 * 
	 * cell = row.createCell(1); cell.setCellValue(num[i]); } try { //Write the
	 * workbook in file system FileOutputStream out = new FileOutputStream(new
	 * File(path)); workbook.write(out); out.close();
	 * System.out.println("SystemCall data written successfully on disk."); }
	 * catch (Exception e) { e.printStackTrace(); } }
	 */
}
