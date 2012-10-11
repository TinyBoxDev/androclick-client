package com.client.infoclick.factory;

/**
 * 
 * @author b3by
 * This is the class in witch all information about bus stop will be saved.
 */

import org.json.JSONException;
import org.json.JSONObject;

public class Stop extends AndroclickObject {
	
	private String number = "";
	private String county = "";
	private String street = "";
	
	@Override
	public boolean setObjectProperties(String properties) {
		try {
			JSONObject manager = new JSONObject(properties);
			this.number = manager.getString("number");
			this.county = manager.getString("county");
			this.street = manager.getString("street");
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("Stop parameter not found into JSON!");
		}
		
		return false;
		
	}
	
	@Override
	public String toString() {
		JSONObject manager = new JSONObject();
		try {
			manager.put("number", this.number);
			manager.put("county", this.county);
			manager.put("street", this.street);
			return manager.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("Error during JSON operations!");
		}
		
		return null;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public void setNumber(String setter) {
		this.number = setter;
	}
	
	public String getCounty() {
		return this.county;
	}
	
	public void setCounty(String setter) {
		this.county = setter;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public void setStreet(String setter) {
		this.street = setter;
	}

}