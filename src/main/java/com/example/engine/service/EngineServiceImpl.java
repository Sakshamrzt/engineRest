package com.example.engine.service;
import java.util.List;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.engine.model.Engine;
import com.example.engine.model.slave;
import com.example.engine.repository.EngineRepo;
import com.example.engine.repository.slaveTypeRepo;
import com.example.engine.exception.ResourceNotFoundException;
import java.util.Date;
import java.util.Base64;
import java.util.UUID;
import com.example.engine.DTO.slaveDTO;
import com.example.engine.DTO.engineDTO;
import com.example.engine.DTO.slaveMainDTO;
import com.example.engine.model.slaveType;
@Service
public class EngineServiceImpl implements EngineService{     
    @Autowired
    EngineRepo engineRepository;
    @Autowired
    slaveTypeRepo slaveTypeRepository;    
    @Override
    public void addEngine(engineDTO engine1,UUID ida){
    Engine engine=new Engine();    
    engine.setName(engine1.getName());
    engine.setDescription(engine1.getDescription());
    engine.setCreatedOn(engine1.getCreatedOn());
    engine.setIsActive(engine1.getIsActive());
    engine.setSlaves(engine1.getSlaveList());
    for (slave Slave : engine.getSlaves()) {
        try{
        Slave.setPassword(Base64.getEncoder().encodeToString(Slave.getPassword().getBytes()));
        }
        catch(Exception e)
        {
         throw new  ResourceNotFoundException("Password can't be null.");
        }  
        Slave.setEngine(engine); 
        slaveType parent=slaveTypeRepository.findById(Slave.getType().getId())
        .orElseThrow(() ->  new  ResourceNotFoundException("Wrong Category uuid."));        
        Slave.setType(parent);
        if(Slave.getIsActive()==null)
            Slave.setIsActive(true);
        if(Slave.getStatus()==null)
            Slave.setStatus("INITIALISING");
        if(Slave.getCreatedOn()==null)
            Slave.setCreatedOn(new Date()); 
        if(Slave.getCores()==null)
            throw new  ResourceNotFoundException("Cores can't be null.");
        if(Slave.getIp()==null)
            throw new  ResourceNotFoundException("Ip can't be null.");
        if(Slave.getName()==null)
            throw new  ResourceNotFoundException("Name can't be null."); 
        if(Slave.getUserName()==null)
            throw new  ResourceNotFoundException("Username can't be null.");        
              
    }
    if(engine.getName()==null)
        throw new  ResourceNotFoundException("Name can't be null.");
    if(engine.getIsActive())
        engine.setIsActive(true);
    if(engine.getStatus()==null)
        engine.setStatus("INITIALISING");
    if(engine.getCreatedOn()==null)
        engine.setCreatedOn(new Date());
    if(engine.getCreatedBy()==null)
        engine.setCreatedBy(ida);
    engineRepository.save(engine);                        
    }

    @Override
    public void deleteEngine(UUID id)
    {
    	Engine engine=engineRepository.findById(id)
    	 .orElseThrow(() -> new ResourceNotFoundException("No engine with the given id has been added "));
    	 engine.setIsActive(false);
         for(slave Slave:engine.getSlaves())
            Slave.setIsActive(false);
    	 engineRepository.save(engine);
    }
    
    @Override
    public List<Engine> displayEngine(UUID a)
    {    	
		List<Engine> engines = new ArrayList<>();    
		for(Engine engine: engineRepository.findAll())
            {
                if(engine.getCreatedBy().equals(a))
                    engines.add(engine);
            }
		return engines;  
    }    
    @Override
    public void updateEngine(UUID id,slaveMainDTO SlaveMain)
    {
               
        Engine engDb=engineRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("No engine with the given id has been added "));

    	if(SlaveMain.getName()!=null)
    		engDb.setName(SlaveMain.getName());

    	if(SlaveMain.getDescription()!=null)
    		engDb.setName(SlaveMain.getName());
        List<slaveDTO> Slave=SlaveMain.getSlaveList();
        List<slave> slaves=engDb.getSlaves();
        for(int i=0;i<Slave.size();i++)
        {
            
            if(Slave.get(i).getType().equals("add"))
                {                    
                    Slave.get(i).getSlave1().setEngine(engDb);
                    if(Slave.get(i).getSlave1().getPassword()==null)
                        throw new  ResourceNotFoundException("Password can't be null.");
                    Slave.get(i).getSlave1().setPassword(Base64.getEncoder().encodeToString(Slave.get(i).getSlave1().getPassword().getBytes()));
                    slaveType parent=slaveTypeRepository.findById(Slave.get(i).getSlave1().getType().getId())
                    .orElseThrow(() ->  new  ResourceNotFoundException("Wrong Category uuid"));                            
                    Slave.get(i).getSlave1().setType(parent); 
                    if(Slave.get(i).getSlave1().getIsActive()==null)
                        Slave.get(i).getSlave1().setIsActive(true);
                    if(Slave.get(i).getSlave1().getStatus()==null)
                        Slave.get(i).getSlave1().setStatus("INITIALISING");
                    if(Slave.get(i).getSlave1().getCreatedOn()==null)
                        Slave.get(i).getSlave1().setCreatedOn(new Date());
                    slaves.add(Slave.get(i).getSlave1());
                    if(Slave.get(i).getSlave1().getCores()==null)
                        throw new  ResourceNotFoundException("Cores can't be null.");
                    if(Slave.get(i).getSlave1().getIp()==null)
                        throw new  ResourceNotFoundException("Ip can't be null.");
                    if(Slave.get(i).getSlave1().getName()==null)
                        throw new  ResourceNotFoundException("Name can't be null.");
                    if(Slave.get(i).getSlave1().getUserName()==null)
                        throw new  ResourceNotFoundException("Username can't be null.");        
                }
            else if(Slave.get(i).getType().equals("remove"))
            {   
                int del=-1;
                for(int j=0;j<slaves.size();j++)
                {
                    
                    if(slaves.get(j).getId().equals(Slave.get(i).getSlave1().getId()))
                        {
                          
                            del=j;                            
                        }
                }
                if(del!=-1)
                    slaves.remove(del);                            

            }
            else if(Slave.get(i).getType().equals("update"))
            {                
                for(int j=0;j<slaves.size();j++)
                {
                    if(slaves.get(j).getId().equals(Slave.get(i).getSlave1().getId()))
                        {                            
                            if(Slave.get(i).getSlave1().getName()!=null)
                                slaves.get(j).setName(Slave.get(i).getSlave1().getName());
                            if(Slave.get(i).getSlave1().getIp()!=null)
                                slaves.get(j).setIp(Slave.get(i).getSlave1().getIp());
                            if(Slave.get(i).getSlave1().getPassword()!=null)
                                slaves.get(j).setPassword(Base64.getEncoder().encodeToString(Slave.get(i).getSlave1().getPassword().getBytes()));
                            if(Slave.get(i).getSlave1().getCores()!=null)
                                slaves.get(j).setCores(Slave.get(i).getSlave1().getCores());
                            if(Slave.get(i).getSlave1().getRam()!=null)
                                slaves.get(j).setRam(Slave.get(i).getSlave1().getRam());
                            if(Slave.get(i).getSlave1().getType().getId()!=null)
                            {
                                if(!slaves.get(j).getType().getId().equals(Slave.get(i).getSlave1().getType().getId()))
                                {                              
                              slaveType parent=slaveTypeRepository.findById(slaves.get(j).getType().getId())
                              .orElseThrow(() ->  new  ResourceNotFoundException("Wrong Category uuid."));     
                              slaves.get(j).setType(parent);
                            }
                        }
                        break;
                        }
                }
            }
        }    
        engDb.setSlaves(slaves);        
        engineRepository.save(engDb);
        

    }

}
