package com.network.client.proxy;

public interface AndroclickSkeleton {
	
	public String requestForStop(String number);
	public String requestForLocation(String county, String street);
	public String requestForLine(String line);

}
