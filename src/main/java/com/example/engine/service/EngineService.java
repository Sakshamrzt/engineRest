package com.example.engine.service;
import com.example.engine.model.Engine;
import com.example.engine.model.slaveDTO;
import java.util.UUID;
import java.util.List;
public interface EngineService {
    public void addEngine(Engine eng,UUID ida);
    public void deleteEngine(UUID id);
    public List<Engine> displayEngine(UUID a);
    public void updateEngine(UUID id,String name,String desc,List<slaveDTO> sla);
}