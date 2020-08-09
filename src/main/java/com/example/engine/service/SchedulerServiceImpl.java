package com.example.engine.service;
import org.springframework.core.env.Environment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.example.engine.model.Engine;
import com.example.engine.model.slave;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.engine.repository.EngineRepo;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import com.example.engine.scheduler.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.engine.service.SchedulerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import com.example.engine.exception.ResourceNotFoundException;
@Service
@Transactional(rollbackFor = {Exception.class})
public class SchedulerServiceImpl implements SchedulerService{   
@Autowired
EngineRepo engineRepository;
@Autowired
private Mapping mapObject;
@Autowired
ResourceLoader resourceLoader;
@Autowired
private Environment environment;
@Override
public void validate() 
{	
	String path;		
	Resource resource;
	Map<String, String> map;
	try{
		path=environment.getProperty("slave.password_path");
		resource = resourceLoader.getResource(environment.getProperty("slave.password_path"));
		map =  mapObject.getMapping(resource);
	}
	catch(Exception e)
	{
		throw new  ResourceNotFoundException("File can't be loaded. Check path.");     
	}

	for(Engine engine:engineRepository.findAll())
	{			
		if(engine.getStatus().equals("INITIALISING"))		
		{
			checkValidSlaveList(map,engine);
			engineRepository.save(engine);				
		}
		
	}				
}

private void checkValidSlaveList(Map<String,String> map,Engine engine)
{
	List<slave> slaves=engine.getSlaves();		
	Boolean valid=true;
	for(int i=0;i<engine.getSlaves().size();i++)
	{
		if(slaves.get(i).getStatus().equals("INITIALISING"))
		{
			String ip=slaves.get(i).getIp();
			String password=slaves.get(i).getPassword();				
			if(map.containsKey(ip))
			{
				if(map.get(ip).equals(password)){
					slaves.get(i).setStatus("RUNNING");
				}
				else
				{
					slaves.get(i).setStatus("FAILED");
					valid=false;
				}
			}
			else
			{
				slaves.get(i).setStatus("FAILED");
				valid=false;
			}
		}				
	}

	if(valid==true)			
	engine.setStatus("RUNNING");
	else
	engine.setStatus("FAILED");
	engine.setSlaves(slaves);		
}

}
