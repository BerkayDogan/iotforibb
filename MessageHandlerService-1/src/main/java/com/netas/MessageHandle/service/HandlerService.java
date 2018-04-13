package com.netas.MessageHandle.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netas.MessageHandle.bus.producer.Sender;
import com.netas.MessageHandle.client.ToDataManagerService;
import com.netas.MessageHandle.client.ToDeviceManagerService;
import com.netas.MessageHandle.model.Device;
import com.netas.MessageHandle.model.Messages;

@Service
public class HandlerService {
	@Autowired
	ToDeviceManagerService ManagerServiceClient;
	@Autowired
	ToDataManagerService DataManagerServiceClient;
	@Autowired
	Sender sender;
	/*
	 * @Autowired private Sender sender;
	 */
	private static final String boot_t = "boot.t";

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public ResponseEntity<?> CheckDevice(String id, String AuthToken) throws NotFoundException, JsonProcessingException {
		// RGV2aWNlTmFtZTpUb2tlbg==

		byte[] decoded = Base64.getDecoder().decode(AuthToken);

		String decodedString = new String(decoded);

		LOG.info("DECODED: " + decodedString);
		// Device b = new Device();
		// b.setName(decodedString);
		String words[] = decodedString.split(":");

		String deviceName = words[0];
		String token = words[1];
		// return new ResponseEntity<Device>(device, HttpStatus.OK);
		// AuthToken base64(DeviceName:Token) ++

		// decode et ++

		// : göre parse et

		// token in var elinde,feign git device in token ini al,control et ++

		List<Messages> m = new ArrayList<>();
		Device d = ManagerServiceClient.GetDevice(id).getBody();
		if (d != null) {
			if (token.equals(d.getAuthToken()) && deviceName.equals(d.getName())) {
				
				Messages message = new Messages();
				
				
				ObjectMapper mapper = new ObjectMapper();
				LOG.info("AUTHORIZED: " + deviceName);
				message.setData(deviceName+"Sent to Data Manager Service");
				message.setDeviceid(id);

				String jsonInString = mapper.writeValueAsString(message);
				//sender = new Sender();
				LOG.info("Sender is ready.");
				
				LOG.info("jsonInString: " + jsonInString);
				sender.send(boot_t,jsonInString );
				LOG.info("Message is sent.: ");
				/*
				 * Doğrulama çalışır ise DataManagerService inden devicein tüm datalarını
				 * response etmeli ve HTTP body de gelen json formatındaki data yı da KAFKA ya
				 * PRODUCE(send) edip DataManagerService in de KAFKADAN ilgili topicden veriyi
				 * consume etmek.DataManagerService bu consume ettiği string json datasını
				 * objcetmapper ile objecte dönüştürüp işlemi ve ardından db ye insert etmeli.
				 */
				m = DataManagerServiceClient.GetDeviceMessages(id);
				return new ResponseEntity<List<Messages>>(m, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Authorization is not valid.", HttpStatus.UNAUTHORIZED);
			}
		}

		return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);

	}
	public String home() {
	    sender.send(boot_t, "Hello Boot!");
		return "Hello :)";
	}
}
