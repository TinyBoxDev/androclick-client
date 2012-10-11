package com.client.infoclick;

import java.util.Observable;

import com.client.infoclick.factory.Bus;

public class AndroclickBusModel extends Observable {
	
	private Bus[] busData;
	
	public static AndroclickBusModel currentBusModel;
	
	public static synchronized AndroclickBusModel getInstance() {
		if (currentBusModel == null)
			currentBusModel = new AndroclickBusModel();
		
		return currentBusModel;
	}
	
	public Bus[] getBus() {
		return busData;
	}
	
	public void setBus(Bus[] busToCopy) {
		busData = busToCopy.clone();
		setChanged();
		notifyObservers();
	}

}
