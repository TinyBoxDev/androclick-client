package com.client.infoclick;

import java.util.Observable;

public class AndroclickSpareMessageModel extends Observable {
	
	private String spareMessage = "";
	
	private static AndroclickSpareMessageModel currentMessageModel;
	
	private AndroclickSpareMessageModel() {
		spareMessage = "";
	}
	
	public static synchronized AndroclickSpareMessageModel getInstance() {
		if (currentMessageModel == null)
			currentMessageModel = new AndroclickSpareMessageModel();
		
		return currentMessageModel;
	}
	
	public String getSpareMessage() {
		return spareMessage;
	}
	
	public void setSpareMessage(String messageToCopy) {
		spareMessage = new String(messageToCopy);
		setChanged();
		notifyObservers();
	}
	
}
