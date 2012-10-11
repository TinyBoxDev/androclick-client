package com.network.client.proxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkManager {
	
	private final String _CLASS_NAME = NetworkManager.class.getName();
	
	private final String ADDRESS = "http://androclick.herokuapp.com";
	//private final String ADDRESS = "http://littlemonkey.dyndns.biz";
	//private final String ADDRESS = "http://192.168.1.244:4544";
	
	private HttpURLConnection currentConnection;
	private BufferedReader streamReader = null;
	private BufferedWriter streamWriter = null;
	
	public String communicateWithServer(String message) {
		System.out.println(_CLASS_NAME + ": Start communication with server.");
		String result;
		//boolean checkForConnection;
		try {
			this.openConnection();
			//checkForConnection = this.openConnection();
			//if (!checkForConnection)
			//	return "{\"status\":\"1\",\"description\":\"Server unreachable\"}";
			this.sendMessage(message);
			result = this.receiveMessage();
			this.closeBuffers();
			System.out.println(_CLASS_NAME + ": All done with communicateWithServer method.");
			return result;
		} catch (IOException e) {
			System.out.println("Communication error: something wrong!");
			e.printStackTrace();
			return null;
		}
		
	}
	
	private boolean openConnection() throws IOException {
		System.out.println(_CLASS_NAME + ": Entering openConnection method.");
		URL infoclickURL = new URL(this.ADDRESS);
		this.currentConnection = (HttpURLConnection) infoclickURL.openConnection();
		this.currentConnection.setDoInput(true);
		this.currentConnection.setDoOutput(true);
		System.out.println(_CLASS_NAME + ": Connecting...");
		//if (currentConnection.getResponseCode() == 200) {
		//	System.out.println(_CLASS_NAME + ": Unreachable!");
		//	return false;
		//}
		this.currentConnection.connect();
		System.out.println(_CLASS_NAME + ": All done with openConnection method.");
		return true;
		//return (currentConnection.getResponseCode() == 200 ? true : false);
	}
	
	private void sendMessage(String clientRequest) throws IOException {
		System.out.println(_CLASS_NAME + ": Sending message " + clientRequest + " to the server.");
		this.streamWriter = new BufferedWriter(new OutputStreamWriter(this.currentConnection.getOutputStream()));
		this.streamWriter.write(clientRequest);
		System.out.println(_CLASS_NAME + ": Message sent, flushing buffer.");
		this.streamWriter.flush();
		System.out.println(_CLASS_NAME + ": All done with sendMessage method.");
	}
	
	private String receiveMessage() throws IOException {
		System.out.println(_CLASS_NAME + ": Trying to receive message from the server.");
		String line;
		String serverResponse = new String();
		System.out.println(_CLASS_NAME + ": Opening buffer for reading...");
		this.streamReader = new BufferedReader(new InputStreamReader(this.currentConnection.getInputStream()));
		System.out.println(_CLASS_NAME + ": Start reading...");
		while ((line = this.streamReader.readLine()) != null) {
			serverResponse += line;
			if (!streamReader.ready()) {
				break;
			}
		}
		System.out.println(_CLASS_NAME + ": All done with receiveMessage method.");
		return serverResponse;
	}
	
	private void closeBuffers() throws IOException {
		System.out.println(_CLASS_NAME + ": Closing all connections and buffers...");
		if (this.streamReader != null)
			this.streamReader.close();
		
		if (this.streamWriter != null)
			this.streamWriter.close();
		
		System.out.println(_CLASS_NAME + ": All done with closeBuffers method.");
	}
	

}
