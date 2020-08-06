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
import com.example.engine.model.slaveType;
import com.example.engine.exception.ResourceNotFoundException;
import java.util.Date;
import java.util.Base64;
import com.example.engine.model.slaveDTO;
@Service
public class EngineServiceImpl implements EngineService{     
    @Autowired
    EngineRepo engineRepository;
    @Autowired
    slaveTypeRepo slaveTypeRepository;
    
    @Override
    public void addEngine(Engine eng,Long ida){
    for (slave sla : eng.getSlaves()) {
        try{
        sla.setPassword(Base64.getEncoder().encodeToString(sla.getPassword().getBytes()));        
        sla.setEngine(eng); 
        sla.setType(slaveTypeRepository.findById(sla.getCategory())); 
        sla.setIsActive(true);
        sla.setStatus("INITIALISING");
        sla.setCreatedOn(new Date());       
    }
    catch(Exception e)
    {
        throw new  ResourceNotFoundException("Category or password can't be null.");
    }
    }
    eng.setIsActive(true);
    eng.setStatus("INITIALISING");
    eng.setCreatedOn(new Date());
    eng.setCreatedBy(ida);
    engineRepository.save(eng);                        
    }

    @Override
    public void deleteEngine(Long id)
    {
        Long userId=new Long(id);
    	Engine eng=engineRepository.findById(userId)
    	 .orElseThrow(() -> new ResourceNotFoundException("No engine with the given id has been added "));
    	 eng.setIsActive(false);
         for(slave sla:eng.getSlaves())
            sla.setIsActive(false);
    	 engineRepository.save(eng);
    }
    
    @Override
    public List<Engine> displayEngine(Long a)
    {    	
		List<Engine> engines = new ArrayList<>();    
		for(Engine eng: engineRepository.findAll())
            {
                if(eng.getCreatedBy()==a)
                    engines.add(eng);
            }
		return engines;  
    }    
    @Override
    public void updateEngine(Long id,String name,String desc,List<slaveDTO> sla)
    {
        
         Long userId=new Long(id);
        Engine engDb=engineRepository.findById(userId)
         .orElseThrow(() -> new ResourceNotFoundException("No engine with the given id has been added "));

    	if(name!=null)
    		engDb.setName(name);

    	if(desc!=null)
    		engDb.setName(desc);

        List<slave> slaves=engDb.getSlaves();
        for(int i=0;i<sla.size();i++)
        {
            
            if(sla.get(i).getType().equals("add"))
                {                    
                    sla.get(i).getSlave1().setEngine(engDb);
                    sla.get(i).getSlave1().setPassword(Base64.getEncoder().encodeToString(sla.get(i).getSlave1().getPassword().getBytes()));                            
                    sla.get(i).getSlave1().setType(slaveTypeRepository.findById(sla.get(i).getSlave1().getCategory())); 
                    sla.get(i).getSlave1().setIsActive(true);
                    sla.get(i).getSlave1().setStatus("INITIALISING");
                    sla.get(i).getSlave1().setCreatedOn(new Date());
                    slaves.add(sla.get(i).getSlave1());
                }
            else if(sla.get(i).getType().equals("remove"))
            {   
                int del=-1;
                for(int j=0;j<slaves.size();j++)
                {
                    
                    if(slaves.get(j).getId()==sla.get(i).getSlave1().getId())
                        {
                          
                            del=j;                            
                        }
                }
                if(del!=-1)
                    slaves.remove(del);                            

            }
            else if(sla.get(i).getType().equals("update"))
            {
                System.out.println("Updating");
                for(int j=0;j<slaves.size();j++)
                {
                    if(slaves.get(j).getId()==sla.get(i).getSlave1().getId())
                        {
                            if(sla.get(i).getSlave1().getName()!=null)
                                slaves.get(j).setName(sla.get(i).getSlave1().getName());
                            if(sla.get(i).getSlave1().getCores()!=null)
                                slaves.get(j).setCores(sla.get(i).getSlave1().getCores());
                            if(sla.get(i).getSlave1().getRam()!=null)
                                slaves.get(j).setRam(sla.get(i).getSlave1().getRam());
                            if(sla.get(i).getSlave1().getCategory()!=null)
                            {
                                if(slaves.get(j).getCategory()!=sla.get(i).getSlave1().getCategory())
                                {
                              slaves.get(j).setCategory(sla.get(i).getSlave1().getCategory());
                              slaves.get(j).setType(slaveTypeRepository.findById(slaves.get(j).getCategory()));
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
