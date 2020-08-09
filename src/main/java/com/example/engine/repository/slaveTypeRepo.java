package com.example.engine.repository;
import com.example.engine.model.slaveType;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;
@Repository
public interface slaveTypeRepo extends CrudRepository<slaveType, UUID> {	
}