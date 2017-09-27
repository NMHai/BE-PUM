package v2.org.analysis.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class is used to handle the JSON file generated for web application.
 * 
 * @author Minh Hai
 *
 */

public class JSONObj<K, V> {
	private JSONObject mainObj = null;	

	/**
	 * Constructor of JSONHandler.
	 * 
	 * @param no
	 *            param
	 */

	public JSONObj() {
		mainObj = new JSONObject();
	}

	@Override
	public String toString() {
		return mainObj.toString();
	}

	/**
	 *
	 * Add a pair of Key and Value
	 * 
	 * @param1 Key
	 * @param2 Value
	 * 
	 * @return No return
	 */
	@SuppressWarnings("unchecked")
	public void add(K key, V value) {
		mainObj.put(key, value);
	}

	/**
	 *
	 * Add a pair of Key and JSON Object
	 * 
	 * @param1 Key
	 * @param2 JSON Handler
	 * 
	 * @return No return
	 */
	@SuppressWarnings("unchecked")
	public void add(K key, JSONObj h) {
		mainObj.put(key, h);
	}

	/**
	 *
	 * Add a pair of Key and JSON Array
	 * 
	 * @param1 Key
	 * @param2 JSON Array
	 * 
	 * @return No return
	 */
	@SuppressWarnings("unchecked")
	public void add(K key, JSONArray ja) {
		// TODO Auto-generated method stub
		mainObj.put(key, ja);
	}

	
}
