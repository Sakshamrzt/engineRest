package com.example.engine.service;
import com.example.engine.model.Engine;
import com.example.engine.DTO.slaveDTO;
import com.example.engine.DTO.engineDTO;
import com.example.engine.DTO.slaveMainDTO;
import java.util.UUID;
import java.util.List;
public interface EngineService {
    public void addEngine(engineDTO eng,UUID ida);
    public void deleteEngine(UUID id);
    public List<Engine> displayEngine(UUID a);
    public void updateEngine(UUID id,slaveMainDTO sla);
}