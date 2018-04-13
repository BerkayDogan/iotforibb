package com.netas.device.controller;


import java.util.List;


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

import com.netas.device.model.Device;
import com.netas.device.model.Messages;
import com.netas.device.repository.DeviceRepository;
import com.netas.device.service.DeviceService;
@RestController
public class DeviceController {
	@Autowired
	DeviceRepository repo;
	@Autowired
	DeviceService service;
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/search/device/{id}")
	public ResponseEntity<?> GetDevice(@PathVariable Long id) throws NotFoundException {
		return service.GetDevice(id);
	}

	@GetMapping("/search/device")
	public @ResponseBody <T> List<Device> GetAllDevice() throws NotFoundException {
		return service.GetAllDevice();
	}

	@RequestMapping(value = "/search/device", method = RequestMethod.POST)
	public ResponseEntity<?> addDevice(@RequestBody Device device, UriComponentsBuilder ucBuilder) {
		return service.addDevice(device, ucBuilder);
	}

	@RequestMapping(value = "/search/device/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Device> updateDevice(@PathVariable("id") long id, @RequestBody Device device) {
		return service.updateDevice(id, device);
	}

	@RequestMapping(value = "/search/device/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDevice(@PathVariable("id") long id) {
		return service.deleteDevice(id);
	}
/*	@GetMapping("/search/deviceMessages")
	public @ResponseBody List<Device> GetDeviceMessages() throws NotFoundException {
		return service.GetAllDevice();
	}
*/
	
	
//	@GetMapping("/search/device/deneme")
//	public ResponseEntity<?> GetDeviceMessages() throws NotFoundException{
//		return service.GetDeviceMessages();
//	}
	@GetMapping("/search/messages/device/{id}")
	public @ResponseBody List<Messages> GetDeviceMessages(@PathVariable("id") String id) throws NotFoundException{
		return service.GetDeviceMessages(id);
	}

}
