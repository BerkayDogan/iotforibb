package com.netas.devices_messages.bus.consumer;

import java.util.concurrent.CountDownLatch;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netas.devices_messages.model.Messages;
import com.netas.devices_messages.repository.*;
import com.netas.devices_messages.service.MessageService;

@Component
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
	@Autowired
	DataRepository repo; 
	@Autowired
	MessageService MessageService; 

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "${kafka.topic.boot}")
	public void receive(ConsumerRecord<?, ?> consumerRecord) throws Throwable {
		
		LOGGER.info("AUTHORIZED: " + consumerRecord.value().toString());
		String incomingString = consumerRecord.value().toString();
		incomingString = incomingString.substring(1, incomingString.length());
		incomingString = incomingString.substring(0, incomingString.length() - 1);
		incomingString = incomingString.replace("\\", "");
		LOGGER.info("incomingString: " + incomingString);
		ObjectMapper mapper = new ObjectMapper();
		Messages new_message = mapper.readValue(incomingString, Messages.class);
		MessageService.addMessage(new_message, null);
		//json string -> java pojo
		//servisin içinde add message var onu çağır
		latch.countDown();
	}
}