package com.netas.device.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.netas.device.client.ManagerClient;
import com.netas.device.model.Device;
import com.netas.device.model.Messages;
import com.netas.device.repository.DeviceRepository;

@Service
public class DeviceService {

	@Autowired
	DeviceRepository repo;
	@Autowired
	ManagerClient ManagerFeignClient;
	Messages msg;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public ResponseEntity<?> GetDevice(Long id) throws NotFoundException {

		Device d = repo.findById(id);
		LOG.debug("[GetDevice]: INFO:{}", "debug");
		if (d != null) {

			LOG.info("[GetDevice]: INFO:{}", "OK");

			return new ResponseEntity<Device>(d, HttpStatus.OK);

		}
		LOG.error("[GetDevice]: ERROR:{}", "Not found");
		return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
	}

	public List<Device> GetAllDevice() throws NotFoundException {

		List<Device> a = repo.findAll();
		List<Device> b = null;
		if (a.size() > 0) {
			LOG.info("[GetMessage]: INFO:{}", "OK");
			Random r = new Random();
			int rand = r.nextInt(100);
			Collections.sort(a, new Comparator<Device>() {
				@Override
				public int compare(final Device object1, final Device object2) {
					return object1.getId().compareTo(object2.getId());

				}
			});

			if (rand % 2 == 0) {
				b = a.stream().sorted((object1, object2) -> object1.getId().compareTo(object2.getId()))
						.collect(Collectors.toList());

			} else {
				b = a.stream().sorted((object2, object1) -> object1.getId().compareTo(object2.getId()))
						.collect(Collectors.toList());
			}

			LOG.error("[GetMessage]: ERROR:{}", "Not found");

		}

		return b;
	}

	public ResponseEntity<?> addDevice(Device device, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + device.getId());

		System.out.println("A Device with name " + device.getName() + " created!");
		repo.save(device);

		return ResponseEntity.ok(device);

	}

	public ResponseEntity<Device> updateDevice(long id, Device device) {
		System.out.println("Updating device " + id);

		Device currentDevice = repo.findById(id);

		if (currentDevice == null) {
			System.out.println("Device with id " + id + " not found");
			return new ResponseEntity<Device>(HttpStatus.NOT_FOUND);
		}
		repo.save(device);

		return new ResponseEntity<Device>(currentDevice, HttpStatus.OK);
	}

	public ResponseEntity<Void> deleteDevice(long id) {
		System.out.println("Deleting User by id " + id);

		Device device = repo.findById(id);
		
		if (device == null) {
			System.out.println("Unable to delete. Device with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

//		repo.deleteById(id);
		repo.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	public List<Messages> GetDeviceMessages(String deviceid) {
		//Device device = repo.findById(deviceid);
		//return device.getMessages();
		List<Messages>  msg = ManagerFeignClient.GetDeviceMessages(deviceid);
		
		return msg;
		
		
		
	}

}
