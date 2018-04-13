package com.netas.devices_messages.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.netas.devices_messages.model.Messages;
@Repository
public interface DataRepository extends CrudRepository<Messages,String> {
	
	Messages findById(String id);
	@Modifying
	@Transactional
	void  deleteById(String id);
	List<Messages> findAll();
	List<Messages> findByDeviceid(String deviceid);
}
