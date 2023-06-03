package com.jdc.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyService {
	
	@Value("${app.service.host}")
	private String hostIp;
	@Value("S{app.service.port}")
	private String portNum;
	@Value("${app.service.retry}")
	private int retriedCount;
	
	public MyService(String hostIp, String portNum, int retriedCount) {
		System.out.printf(
				this.hostIp = hostIp,
				this.portNum = portNum,
				this.retriedCount = retriedCount
				);
	}
	
	public void sendMessage(String message) {
		System.out.println();
	}
}
