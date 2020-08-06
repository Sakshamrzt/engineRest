package com.example.engine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.example.engine.repository.slaveTypeRepo;
import com.example.engine.model.slaveType;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class EngineApplication {
	public static void main(String[] args) {
		SpringApplication.run(EngineApplication.class, args);
	}
	@Bean
  	public CommandLineRunner demo(slaveTypeRepo repository) {return (args)->{
      repository.save(new slaveType("e51b38a6-30ec-11e9-babd-fa163e093ca8","Infrastructure"));
      repository.save(new slaveType("f711eb25-30ec-11e9-babd-fa163e093ca8","Technology"));      
	};
   }

}