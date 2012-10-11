package com.network.client.proxy;

public class AndroclickClient {
	
	private AndroclickNetworkManager networkService;
	
	public AndroclickClient() {
		this.networkService = new AndroclickNetworkManager();
	}
	
	public String doAndroclickRequest(String requester) {
		return this.networkService.communicateWithServer(requester);
	}

}