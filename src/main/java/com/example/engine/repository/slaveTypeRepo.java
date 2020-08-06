package com.example.engine.repository;
import com.example.engine.model.slaveType;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface slaveTypeRepo extends CrudRepository<slaveType, Long> {
	public slaveType findById(String id);
}