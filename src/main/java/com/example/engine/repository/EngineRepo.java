package com.example.engine.repository;
import com.example.engine.model.Engine;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;
@Repository
public interface EngineRepo extends CrudRepository<Engine, UUID> {

}