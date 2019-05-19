package de.interstellar.model.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** This class provides the functionality to parse a JSON object
 *  and retrieve the included data. 
 * 
 * @author Florian Sturn
 * @date 16.05.2019
 *
 */
public class JSONParser {
	
	
	
	/** This function takes a String and parses it into a JSON object.
	 *  If the string does not represent a valid JSON then the parsing
	 *  will not be executed.
	 *  
	 * @author Florian Sturn
	 * @date 16.05.2019 
	 * 
	 * @param pData => a String representing a JSON object.
	 * @return a JSONObject containing the data from the given string.
	 */
	public JSONObject createJSONObject(String pData) {
		
		JSONObject obj = null;
		
		try {
			obj = new JSONObject(pData);
		} catch(JSONException ex) {
			System.out.println("The given String does not represent a valid JSON object!");
		}
				
		return obj;
		
	}
	
	/** This function retrieves an array from a specific JSONObject. The selection
	 *  of the Array is based on the given name of the array.
	 *  If an array with the given is not contained, then a null object will be
	 *  returned.
	 *  
	 * @author Florian Sturn
	 * @date 16.05.2019 
	 * 
	 * @param obj => JSONObject from which to retrieve the array
	 * @param pName => name of the array within the JSONObject
	 * @return a JSONArray
	 */
	public JSONArray retrieveArray(JSONObject obj, String pName) {
		
		JSONArray arr = null;
		
		try {
			arr = obj.getJSONArray(pName);
		} catch(JSONException ex) {
			System.out.println("Array with name " + "[" + pName + "] not found");
			System.out.println(ex.getMessage());
		}
		
		return arr;
		
	}
	
	/** This function retrieves the string value of a specific key from a given
	 *  JSONObject. If the JSONObject does not contain the given key,
	 *  then an empty String will be returned.
	 * 
	 * @author Florian Sturn
	 * @date 16.05.2019 
	 * 
	 * @param pObject => JSONObject from which to retrieve a value.
	 * @param pValueKey => key to the value we want to retrieve
	 * @return value of the matching key
	 */
	public String retrieveStringValue(JSONObject pObject, String pValueKey) {
		
		String value = "";
		
		try {
			value = pObject.getString(pValueKey);
		} catch(JSONException ex) {
			System.out.println("[" + pValueKey + "]" + " " + "not found in JSONObject");
			System.out.println(ex.getMessage());
		}

		return value;
	}
	
	/** This function retrieves the double value of a specific key from a given
	 *  JSONObject. If the JSONObject does not contain the given key,
	 *  then the default value -1.0 will be returned.
	 *  
	 * @author Florian Sturn
	 * @date 16.05.2019 
	 * 
	 * @param pObject => JSONObject from which to retrieve a value
	 * @param pValueKey => key to the value we want to retrieve
	 * @return value of the matching key
	 */
	public double retrieveDoubleValue(JSONObject pObject, String pValueKey) {
		
		double value = -1.0;
		
		try {
			value = pObject.getDouble(pValueKey);
		} catch(JSONException ex) {
			System.out.println("[" + pValueKey + "]" + " " + "not found in JSONObject");
			System.out.println(ex.getMessage());
		}

		return value;
		
		
	}
	
	/** This function retrieves the integer value of a specific key form a given
	 *  JSONObject. If the JSONObject does not contain the given key,
	 *  then the default value -1 will be returned.
	 *
	 * @author Florian Sturn
	 * @date 16.05.2019
	 * 
	 * @param pObject => JSONObject from which to retrieve a value
	 * @param pValueKey => key to the value we want to retrieve
	 * @return value of the matching key
	 */
	public int retrieveIntegerValue(JSONObject pObject, String pValueKey) {
		
		int value = -1;
		
		try {
			value = pObject.getInt(pValueKey);
		} catch(JSONException ex) {
			System.out.println("[" + pValueKey + "]" + " " + "not found in JSONObject");
			System.out.println(ex.getMessage());
		}

		return value;		
	}

}
