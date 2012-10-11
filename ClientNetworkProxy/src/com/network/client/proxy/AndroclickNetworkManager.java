package com.network.client.proxy;

public class AndroclickNetworkManager extends NetworkManager {
	
	/*
	 * Qui si possono aggiungere funzionalita' di rete relative al server Androclick,
	 * come estensione del NetworkManager generale.
	 */
	
	public String doSearch(String message){
		return this.communicateWithServer(message);
	}

}