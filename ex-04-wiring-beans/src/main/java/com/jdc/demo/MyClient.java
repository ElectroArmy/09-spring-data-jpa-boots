package com.jdc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyClient {
	//DI in here
	@Autowired
	private MyService service;

	
	public void sendHello() {
		service.sendMessage("Hello");
	}
}
