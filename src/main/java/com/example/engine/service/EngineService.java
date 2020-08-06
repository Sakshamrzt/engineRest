package com.example.engine.service;
import com.example.engine.model.Engine;
import com.example.engine.model.slaveDTO;
import java.util.List;
public interface EngineService {
    public void addEngine(Engine eng,Long ida);
    public void deleteEngine(Long id);
    public List<Engine> displayEngine(Long a);
    public void updateEngine(Long id,String name,String desc,List<slaveDTO> sla);
}