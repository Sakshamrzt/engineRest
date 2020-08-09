package com.example.engine.controllers;
import com.example.engine.model.Engine;
import com.example.engine.model.slave;
import com.example.engine.model.slaveDTO;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.http.ResponseEntity; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;  
import java.util.Optional; 
import com.example.engine.service.EngineService;
import org.springframework.http.HttpStatus;
import java.util.List; 
import java.util.UUID; 
import io.swagger.annotations.*;  
@RestController
@RequestMapping("/api")
public class MainController {
@Autowired
EngineService service;
@PostMapping("/create")
@ApiOperation(value = "Create engine and its slaves", notes = "Delete type parameter for slave while giving input. Enter category from choices:e51b38a6-30ec-11e9-babd-fa163e093ca8 or f711eb25-30ec-11e9-babd-fa163e093ca8")
public ResponseEntity<Object> create(@RequestHeader(value="createdBy") UUID createdBy,@RequestBody Engine engine) {
	service.addEngine(engine,createdBy);
	return new ResponseEntity<>("Engine is created successfully", HttpStatus.CREATED);	 
} 

@DeleteMapping("/delete/{id}")
@ApiOperation(value = "Delete engine and its slaves", notes = "Enter the id of engine you want to delete.")
public ResponseEntity<Object> delete(@PathVariable UUID id)
{	
	service.deleteEngine(id);
	return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);	
}

@GetMapping("/see")
@ApiOperation(value = "Display engine and its slaves entered by a user", notes = "Enter the userId whose added entities you wanna see")
public List<Engine> getAllEngine(@RequestHeader(value="createdBy") UUID userId) {  
	return service.displayEngine(userId);    
}
@PutMapping("/updateEngine/{id}")
@ApiOperation(value = "Updating engine and its slaves", notes = "Enter id as header. Enter name, description. In slave array give type=update for updating the slave with the specified data, type=add for adding slave to that engine, and type=remove if you want the slave to be deleted")
public  ResponseEntity<Object> update(@PathVariable UUID id,@RequestParam String name,@RequestParam String desc,@RequestBody List<slaveDTO> Slave )
{
	service.updateEngine(id,name,desc,Slave);	
	return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
}
}                                                                                                         