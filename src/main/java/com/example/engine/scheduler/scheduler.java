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
import com.example.engine.service.SchedulerService;
@Component
public class scheduler  {
	@Autowired
	SchedulerService service;
	@Scheduled(fixedRate = 300000)
	public void valid() 
	{
		service.validate();
	}
}