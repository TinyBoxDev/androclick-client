package com.client.infoclick.factory;

/**
 * 
 * @author b3by
 * This is the class in witch all results for bus search will be saved.
 */

import org.json.JSONException;
import org.json.JSONObject;

public class Bus extends AndroclickObject {

	private String time = "";
	private String line = "";
	
	@Override
	public boolean setObjectProperties(String properties) {
		try {
			JSONObject manager = new JSONObject(properties);
			this.time = manager.getString("time");
			this.line = manager.getString("line");
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("Bus parameter not found into JSON!");
		}
		return false;
	}
	
	@Override
	public String toString() {
		JSONObject manager = new JSONObject();
		try {
			manager.put("time", this.time);
			manager.put("line", this.line);
			return manager.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("Error during JSON operations!");
		}
		return null;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String setter) {
		this.time = setter;
	}
	
	public String getLine() {
		return this.line;
	}
	
	public void setLine(String setter) {
		this.line = setter;
	}
	
}
