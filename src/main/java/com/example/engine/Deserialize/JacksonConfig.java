package com.example.engine.Deserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.Module ;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import com.example.engine.Deserialize.CustomerDateAndTimeDeserialize;
//@Configuration
public class JacksonConfig {
// @Bean
// public Module dynamoDemoEntityDeserializer() {
//     SimpleModule module = new SimpleModule();
//     module.addDeserializer(Date.class, new CustomerDateAndTimeDeserialize());
//     return module;
// }
}