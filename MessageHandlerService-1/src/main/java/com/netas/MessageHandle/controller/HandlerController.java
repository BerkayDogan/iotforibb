package com.netas.MessageHandle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netas.MessageHandle.service.HandlerService;
@RestController
public class HandlerController {
	@Autowired
	HandlerService service;
    
       
//        byte[] encodedBytes = Base64.encodeBase64("Test".getBytes());
//        System.out.println("encodedBytes " + new String(encodedBytes));
//        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
//        System.out.println("decodedBytes " + new String(decodedBytes));
   
    @GetMapping("/search/device/{id}")
	public ResponseEntity<?> CheckDevice(@PathVariable("id") String id, @RequestHeader(value="Authorization") String Authorization_Text) throws NotFoundException, JsonProcessingException {
		return service.CheckDevice(id,Authorization_Text);
    }
	@RequestMapping("/")
	public String home() {
		return  service.home();
	}
}
