package com.client.infoclick.factory;
/**
 * 
 * @author b3by
 * This is the factory class. It will be used to generate objects based on what call will be done by consumers.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class AndroclickObjectFactory {
	
	public static final int BUS = 0;
	public static final int STOP = 1;
	
	
	/**
	 * 
	 * @param selector
	 * @return AndroclickObject
	 * This is the main method to obtain a bus or stop object
	 */
	public static AndroclickObject getAndroclickObject(int selector) {
		switch (selector) {
		case AndroclickObjectFactory.BUS:
			Bus messageBus = new Bus();
			return messageBus;
		case AndroclickObjectFactory.STOP:
			Stop messageStop = new Stop();
			return messageStop;
		default:
			return null;
		}
	}
	
	
	public static AndroclickObject[] getAndroclickObjectList(int selector, String message) throws JSONException {
		JSONObject fromMessage = new JSONObject(message);
		Log.v("Fctry", "Message to parse: " + Integer.toString(fromMessage.length()));
		JSONArray toResend = new JSONArray();
		
		if (selector == AndroclickObjectFactory.BUS) {
			Log.v("Fctry", "Here for buses");
			toResend = fromMessage.getJSONArray("androclick_objects");
			int lengthOfBuses = toResend.length();
			Log.v("Fctry", "Array size: " + String.valueOf(lengthOfBuses));
			Bus[] toReturn = new Bus[lengthOfBuses];
			for (int i=0; i<lengthOfBuses; i++) {
				toReturn[i] = new Bus();
				toReturn[i].setObjectProperties(String.valueOf(toResend.getString(i)));
			}
			return toReturn;
		} else if (selector == AndroclickObjectFactory.STOP) {
			Log.v("Fctry", "Here for stops");
			toResend = fromMessage.getJSONArray("androclick_objects");
			Log.v("Fctry", "Array extracted from message: " + toResend);
			int lengthOfStops = toResend.length();
			Log.v("Fctry", "Array size: " + String.valueOf(lengthOfStops));
			Stop[] toReturn = new Stop[lengthOfStops];
			Log.v("Fctry", "Return array created.");
			for (int i=0; i<lengthOfStops; i++) {
				toReturn[i] = new Stop();
				Log.v("Fctry", "Converting " + String.valueOf(toResend.getString(i)));
				toReturn[i].setObjectProperties(String.valueOf(toResend.getString(i)));
			}
			Log.v("Fctry", "All done with parsing.");
			return toReturn;
		} else return null;
		
	}

}