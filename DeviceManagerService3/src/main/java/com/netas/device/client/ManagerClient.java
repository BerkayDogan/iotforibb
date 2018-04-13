package com.netas.device.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netas.device.model.Messages;
@FeignClient(name = "data-manager-service")
public interface ManagerClient {

	@RequestMapping(method = RequestMethod.GET, value = "/search/messages/device/{id}")
	public List<Messages> GetDeviceMessages(@PathVariable("id") String id);
}
