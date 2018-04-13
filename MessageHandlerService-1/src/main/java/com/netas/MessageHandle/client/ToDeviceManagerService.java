package com.netas.MessageHandle.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netas.MessageHandle.model.Device;
@FeignClient(name = "device-manager-service")
public interface ToDeviceManagerService {
	@RequestMapping(method = RequestMethod.GET, value = "/search/device/{id}")
	public ResponseEntity<Device> GetDevice(@PathVariable("id") String id);
	//public HttpHeaders getHeaders();	
}
