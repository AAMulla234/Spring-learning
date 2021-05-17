package com.pmc.configclient.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RequestMapping("/rest")
@RestController
public class MessageController {
	
	private static final Logger LOG = Logger.getLogger(MessageController.class.getName());
	
	
	
	@Value("${message: Default Hello}")
	private String message;
	
		
	@GetMapping("/message")
	public String getMessage(){
		LOG.info("getMessage from configuration client");
		return message;
	}

}
