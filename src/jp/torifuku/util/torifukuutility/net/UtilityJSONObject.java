package jp.torifuku.util.torifukuutility.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class UtilityJSONObject {
	//private static final String TAG = "UtilityJSONObject.";

	/**
	 * getJSONObject
	 * 
	 * @param parent
	 *           
	 * @param name
	 *           
	 * @return
	 */
	static public JSONObject getJSONObject(final JSONObject parent,
			final String name) {
		JSONObject child = null;
		try {
			child = parent.getJSONObject(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return child;
	}

	/**
	 * getJSONArray
	 * 
	 * @param parent
	 *          
	 * @param name
	 *          
	 * @return JSONArray
	 */
	static public JSONArray getJSONArray(final JSONObject parent,
			final String name) {
		JSONArray jsonArray = null;
		try {
			jsonArray = parent.getJSONArray(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonArray;
	}

	/**
	 * getJSONObject
	 * 
	 * @param jsonArray
	 *          
	 * @param index
	 *         
	 * @return JSONObject
	 */
	static public JSONObject getJSONObject(JSONArray jsonArray, int index) {
		JSONObject object = null;
		try {
			object = jsonArray.getJSONObject(index);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * getString
	 * 
	 * @param obj
	 *            
	 * @param name
	 *            
	 * @return String
	 */
	static public String getString(JSONObject obj, final String name) {
		String value = null;
		try {
			value = obj.getString(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
		/*
		 * StringBuilder strBuilder = new StringBuilder("");
		 * 
		 * if ((obj == null) || (name == null)) { return strBuilder.toString();
		 * }
		 * 
		 * try { strBuilder.append(obj.getString(name)); } catch (JSONException
		 * e) { e.printStackTrace(); } String retStr = strBuilder.toString(); if
		 * (!retStr.equals("null")) { return retStr; } else { return "----"; }
		 */
	}

	/**
	 * getInt
	 * 
	 * @param obj
	 *         
	 * @param name
	 *         
	 * @return int
	 */
	static public int getInt(JSONObject obj, final String name) {
		int value = -1;
		try {
			value = obj.getInt(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	/*
	 * static public double getDouble(JSONObject obj, final String name) {
	 * double ret = 0; if ( (obj == null) || (name == null) ) { return ret; }
	 * 
	 * try { ret = obj.getDouble(name); } catch (JSONException e) {
	 * e.printStackTrace(); } return ret; }
	 */

	/**
	 * jsonObjectDebugOut
	 * 
	 * @param jsonObject
	 *            JSONObject
	 */

	static public void jsonObjectDebugOut(JSONObject jsonObject) {
		//final String tag = TAG + "jsonObjectDebugOut";

		if (jsonObject == null) {
			return;
		}

		try {
			String jsonText = jsonObject.toString(4);
			Log.d("jsonText", jsonText);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}
}
