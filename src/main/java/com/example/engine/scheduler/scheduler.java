package com.example.engine.scheduler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import com.example.engine.model.Engine;
import com.example.engine.model.slave;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.engine.repository.EngineRepo;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import com.example.engine.scheduler.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class scheduler  {
	@Autowired
    EngineRepo engineRepository;
	@Autowired
	private Mapping mapOb;
	@Autowired
	ResourceLoader resourceLoader;
	@Scheduled(fixedRate = 300000)
	public void validate() throws Exception
	{			
		Resource res = resourceLoader.getResource("classpath:password.txt");
		Map<String, String> map =  mapOb.getMapping(res);
		for(Engine eng:engineRepository.findAll())
		{			
			if(eng.getStatus().equals("INITIALISING"))		
			{
				eng.getSlaves();
				checkValidSlaveList(map,eng);
				engineRepository.save(eng);				
			}
			
		}				
	}
	private void checkValidSlaveList(Map<String,String> map,Engine eng)
	{
		List<slave> slaves=eng.getSlaves();		
		Boolean valid=true;
		for(int i=0;i<eng.getSlaves().size();i++)
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
		eng.setStatus("RUNNING");
		else
		eng.setStatus("FAILED");
		eng.setSlaves(slaves);		
	}
	
}