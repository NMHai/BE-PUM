package v2.org.analysis.json;

import org.json.simple.JSONArray;


public class JSONTest{
	
	@SuppressWarnings("unchecked")
	public static void main(String [] args) {
		new JSONTest().test();
	}	
	
	public void test() {
		JSONObj<String, String> jo = new JSONObj<String, String>();
		jo.add("firstName", "John");
		jo.add("lastName", "Doe");
		
		System.out.println(jo.toString());
		
		JSONObj<String, String> jo1 = new JSONObj<String, String>();
		jo1.add("firstName", "Marry");
		jo1.add("lastName", "Ann");
		
		System.out.println(jo1.toString());
		
		JSONObj <String,String> mainObj = new JSONObj<String,String> ();
		JSONArray ja = new JSONArray();
		ja.add(jo1);
		ja.add(jo);
		
		mainObj.add("employees", jo);
		mainObj.add("employees", ja);
		mainObj.add("employees1", jo);		
		
		System.out.println(mainObj.toString());
	}
	
	
}
