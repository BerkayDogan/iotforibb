package com.netas.device.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.netas.device.model.Device;
@Repository
public interface DeviceRepository extends CrudRepository<Device, Long>{
	Device findById(Long id);
	//Messages findByDeviceid(String deviceid);
	@Modifying
	@Transactional
	void  deleteById(long id); 

	List<Device> findAll();
}
