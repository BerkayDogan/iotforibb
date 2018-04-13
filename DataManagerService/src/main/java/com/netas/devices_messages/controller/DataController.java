package com.netas.devices_messages.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netas.devices_messages.model.Messages;
import com.netas.devices_messages.repository.DataRepository;
import com.netas.devices_messages.service.MessageService;

@RestController
public class DataController {
	
	@Autowired
	DataRepository repo;
	@Autowired
	MessageService service;
private final Logger LOG = LoggerFactory.getLogger(this.getClass());

@GetMapping("/search/messages/{id}")
public ResponseEntity<?> GetMessage(@PathVariable String id) throws NotFoundException {
	return service.GetMessage(id);
}
@GetMapping("/search/messages")
public @ResponseBody List<Messages> GetAllMessage() throws NotFoundException {

	
	List<Messages> a=service.GetAllMessage();
	if (a.size() > 0) {
		LOG.info("[GetMessage]: INFO:{}", "OK");}
	else
		LOG.error("[GetMessage]: ERROR:{}", "Not found");
	return a;
}

@RequestMapping(value = "/search/messages", method = RequestMethod.POST)//post methodu
public ResponseEntity<?>  addMessage(@RequestBody Messages message, UriComponentsBuilder ucBuilder) {
	return service.addMessage(message, ucBuilder);
}

@RequestMapping(value = "/search/messages", method = RequestMethod.PUT)//put methodu 
public ResponseEntity<?> updateMessage(@PathVariable("id") String id, @RequestBody Messages message) {
	return service.updateMessage(id, message);
}

@RequestMapping(value = "/search/messages/{id}", method = RequestMethod.DELETE)
public ResponseEntity<?>  deleteMessage(@PathVariable("id") String id) {
	return service.deleteMessage(id);
}
@GetMapping("/search/messages/device/{deviceid}")
public @ResponseBody List<Messages> GetDeviceMessages(@PathVariable String deviceid) throws NotFoundException {
	return service.GetDeviceMessages(deviceid);
}
//@GetMapping("/search/device/deneme")
//public @ResponseBody Messages GetDeviceMessages2() throws NotFoundException {
//	return service.GetDeviceMessages2();
//}

}
