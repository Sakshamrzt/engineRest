package com.example.engine.controllers;
import com.example.engine.model.Engine;
import com.example.engine.model.slave;
import com.example.engine.repository.EngineRepo;
import java.util.List;
import java.util.ArrayList;    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;  
import java.util.Optional; 
@RestController
public class MainController {
@Autowired    
EngineRepo e_repository;
@PostMapping("/create")
public Engine create(@RequestBody Engine eng) {
	//#1
	// ArrayList < slave > slaveList = new ArrayList<>();
	// Engine engi = new Engine(eng.getId(),eng.getCreatedBy(),eng.getName(),eng.getStatus(),"",eng.getCreatedOn(),eng.getIsActive(),slaveList);	
	// for (slave sla : eng.getSlaves()) {
	// slaveList.add( new slave(sla.getId(),sla.getName(),sla.getCreatedOn(),sla.getIsActive(),sla.getStatus(),sla.getIp(),sla.getUserName(),sla.getPassword(), sla.getCores(),sla.getRam(), engi ) ) ;
	// }
	// engi.setSlaves( slaveList );
	//#2
	   //  if( eng.getSlaves().size() > 0 )
    // {
    //     eng.getSlaves().stream().forEach( slaveItem -> {
    //         slaveItem.setEngine( eng );
    //     } );
    // }
    //return eng;
    return e_repository.save(eng);
}
}                                                                                                           