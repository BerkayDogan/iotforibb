package com.netas.devices_messages.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.netas.devices_messages.model.Messages;
import com.netas.devices_messages.repository.DataRepository;
@Service
@Transactional
@Component
public class MessageService {
	@Autowired
	DataRepository repo;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	public ResponseEntity<?> GetMessage(String id) throws NotFoundException{
		
		Messages d= repo.findById(id);	
		LOG.debug("[GetMessage]: INFO:{}", "debug");
		if (d != null) {
			
			d.increment();
			repo.save(d);
			LOG.info("[GetMessage]: INFO:{}", "OK");
			
			return new ResponseEntity<Messages>(d, HttpStatus.OK);
		

		}
		LOG.error("[GetMessage]: ERROR:{}", "Not found");
		return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
	}
	
	public List<Messages> GetAllMessage() throws NotFoundException {
			
		List<Messages> a=repo.findAll();
		
		return a;
	}
	public ResponseEntity<?> addMessage(Messages message, UriComponentsBuilder ucBuilder){
	    System.out.println("Creating message " + message.getId());

//	    if (repo.findById(message.getId())!=null) {
//	        System.out.println("A message with name " + message.getData() + " data already exist. Overrided.");            
//	        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//	    }
//	    	System.out.println("A message with name " + message.getData() + " created!");
	  	repo.save(message);
	   	return ResponseEntity.ok(message);
	}
	
	public ResponseEntity<Messages> updateMessage(String id,Messages message) {
	    System.out.println("Updating message " + id);
	     
	    Messages currentMessage = repo.findById(id);
	     
	    if (currentMessage==null) {
	        System.out.println("Message with id " + id + " not found");
	        return new ResponseEntity<Messages>(HttpStatus.NOT_FOUND);
	}
		return new ResponseEntity<Messages>(HttpStatus.OK);
	    
	}
	
	public ResponseEntity<Void> deleteMessage(String id) {
		 System.out.println("Deleting message by id " + id);

		 Messages message = repo.findById(id);
		 if (message == null) {
		 System.out.println("Unable to delete. Device with id " + id + " not found");
		 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		    }

		 repo.delete(id);
		 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	public List<Messages>  GetDeviceMessages(String deviceid) {
		List<Messages>  m= repo.findByDeviceid(deviceid);	
		return m;
	}

//	public Messages GetDeviceMessages2() {
//		Messages msg = new Messages(1, "deviceid", "data", 1);
//		return msg;
//		
//	}
	
	
	}
	

	
