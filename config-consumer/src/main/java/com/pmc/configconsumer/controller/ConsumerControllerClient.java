package com.pmc.configconsumer.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RequestMapping("/consumer")
@RestController
public class ConsumerControllerClient {

	private static final Logger LOG = LoggerFactory.getLogger(ConsumerControllerClient.class.getName());
	
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/clientMessage")
	@HystrixCommand(fallbackMethod = "getMessageFallback", commandKey = "firstHytrixCommand", groupKey = "firstHytrixGroup")
	public String getClientMessage(){
		ServiceInstance serviceInstance=loadBalancer.choose("config-client");
		
		LOG.info(serviceInstance.getUri().toString());
		
		String baseUrl=serviceInstance.getUri().toString();
		
		baseUrl=baseUrl+"/rest/message";
		
		
		ResponseEntity<String> response=null;
		try{
		response=restTemplate.exchange(baseUrl,
				HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
		LOG.info(response.getBody());
		return response.getBody(); 
	}
	
	private String getMessageFallback(){
		return "Fallback - Default Message";
	}
	
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	private HttpEntity getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	
}
