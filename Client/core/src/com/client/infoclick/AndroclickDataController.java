package com.client.infoclick;

import org.json.JSONException;
import org.json.JSONObject;
import com.client.infoclick.factory.AndroclickObjectFactory;
import com.client.infoclick.factory.Bus;
import com.client.infoclick.factory.Stop;
import com.network.client.proxy.AndroclickClient;
import com.network.client.proxy.AndroclickSkeleton;
import android.os.AsyncTask;
//import android.util.Log;

public class AndroclickDataController extends AsyncTask<Object, Void, String> implements AndroclickSkeleton {
	
	//private final String _CLASS_NAME = AndroclickDataController.class.getName();
	
	public static final int RESPONSE_OK = 0;
	public static final int RESPONSE_BAD = 1;
	
	public static final int STOP_REQUEST_ID = 0;
	public static final int LINE_REQUEST_ID = 1;
	public static final int LOCATION_REQUEST_ID = 2;
	
	private static final String STOP_REQUEST = "stop_request=";
	private static final String LINE_REQUEST = "line_request=";
	private static final String LOCATION_REQUEST = "location_request=";
	
	private AndroclickClient currentProxy;
	
	private AndroclickSpareMessageModel spareMessageModel;
	private AndroclickBusModel busModel;
	private AndroclickStopModel stopModel;
	
	private String resultBuffer;
	
	public AndroclickDataController() {
		this.currentProxy = new AndroclickClient();
		
		this.spareMessageModel = AndroclickSpareMessageModel.getInstance();
		this.busModel = AndroclickBusModel.getInstance();
		this.stopModel = AndroclickStopModel.getInstance();
				
	}
	
	@Override
	public String requestForStop(String number) {
		//Log.v(_CLASS_NAME, "Processing DataController method!");
		Stop newStop = (Stop)AndroclickObjectFactory.getAndroclickObject(AndroclickObjectFactory.STOP);
		newStop.setNumber(number);
		newStop.setCounty("");
		newStop.setStreet("");
		//Log.v(_CLASS_NAME, "Message to send: " + STOP_REQUEST + newStop.toString());
		return this.currentProxy.doAndroclickRequest(STOP_REQUEST + newStop.toString());
	}

	@Override
	public String requestForLine(String line) {
		//Log.v(_CLASS_NAME, "Processing DataController method!");
		Bus newBus = (Bus)AndroclickObjectFactory.getAndroclickObject(AndroclickObjectFactory.BUS);
		newBus.setLine(line);
		newBus.setTime("");
		//Log.v(_CLASS_NAME, "Message to send: " + LINE_REQUEST + newBus.toString());
		return this.currentProxy.doAndroclickRequest(LINE_REQUEST + newBus.toString());
	}
	
	@Override
	public String requestForLocation(String county, String street) {
		//Log.v(_CLASS_NAME, "Processing DataController method!");
		Stop newStop = (Stop)AndroclickObjectFactory.getAndroclickObject(AndroclickObjectFactory.STOP);
		newStop.setCounty(county);
		newStop.setStreet(street);
		newStop.setNumber("");
		//Log.v(_CLASS_NAME, "Message to send: " + LOCATION_REQUEST + newStop.toString());
		return this.currentProxy.doAndroclickRequest(LOCATION_REQUEST + newStop.toString());
	}
	
	@Override
	protected String doInBackground(Object... params) {
		switch((Integer)params[0]) {
		case STOP_REQUEST_ID :
			resultBuffer = this.requestForStop((String)params[1]);
			return STOP_REQUEST;
		case LINE_REQUEST_ID :
			resultBuffer = this.requestForLine((String)params[1]);
			return LINE_REQUEST;
		case LOCATION_REQUEST_ID :
			resultBuffer = this.requestForLocation((String)params[1], (String)params[2]);
			return LOCATION_REQUEST;
		default :
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(String results) {
		try {
			int responseStatus = AndroclickDataController.checkStatus(resultBuffer);
			if (results.equals(STOP_REQUEST)) {
				if (responseStatus == RESPONSE_OK) {
					//Log.v(_CLASS_NAME, "Trying to build results vector.");
					this.busModel.setBus((Bus[])AndroclickObjectFactory.getAndroclickObjectList(AndroclickObjectFactory.BUS, resultBuffer));
					//Log.v(_CLASS_NAME, "Well done.");
				} else if (responseStatus == RESPONSE_BAD) {
					this.spareMessageModel.setSpareMessage(AndroclickDataController.getValue(resultBuffer, "description"));
					//Log.v(_CLASS_NAME, "Spare message: " + this.spareMessageModel.getSpareMessage());
				} else {
					this.spareMessageModel.setSpareMessage("What the fuck did happen??");
				}
			} else if (results.equals(LINE_REQUEST) || results.equals(LOCATION_REQUEST)) {
				if (responseStatus == RESPONSE_OK) {
		    		//Log.v(_CLASS_NAME, "Trying to build results vector.");
		    		this.stopModel.setStop((Stop[])AndroclickObjectFactory.getAndroclickObjectList(AndroclickObjectFactory.STOP, resultBuffer));
		    		//Log.v(_CLASS_NAME, "Well done.");
		    	} else if (responseStatus == RESPONSE_BAD) {
		    		this.spareMessageModel.setSpareMessage(AndroclickDataController.getValue(resultBuffer, "description"));
		    		//Log.v(_CLASS_NAME, "Spare message: " + this.spareMessageModel.getSpareMessage());
		    	} else {
		    		this.spareMessageModel.setSpareMessage("What the fuck did happen??");
		    	}
			}
			resultBuffer = "";
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static int checkStatus(String message) throws JSONException {
		JSONObject arrayString = new JSONObject(message);
		return (arrayString.has("status")) ? arrayString.getInt("status") : -1;
	}
	
	public static String getValue(String message, String name) throws JSONException {
		JSONObject arrayString = new JSONObject(message);
		return (arrayString.has(name)) ? arrayString.getString(name) : null;
	}

}