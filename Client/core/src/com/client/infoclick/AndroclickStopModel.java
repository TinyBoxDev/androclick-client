package com.client.infoclick;

import java.util.Observable;

import com.client.infoclick.factory.Stop;

public class AndroclickStopModel extends Observable {
	
	private Stop[] stopData;
	
	public static AndroclickStopModel currentStopModel;
	
	public static synchronized AndroclickStopModel getInstance() {
		if (currentStopModel == null)
			currentStopModel = new AndroclickStopModel();
		
		return currentStopModel;
	}
	
	public Stop[] getStop() {
		return stopData;
	}
	
	public void setStop(Stop[] stopToCopy) {
		stopData = stopToCopy.clone();
		setChanged();
		notifyObservers();
	}

}
