package com.example.engine.Deserialize;
import java.util.Date;
import java.text.SimpleDateFormat; 
import com.example.engine.exception.ResourceNotFoundException;
import java.io.IOException; 
import java.text.ParseException; 
import com.fasterxml.jackson.core.JsonParser; 
import com.fasterxml.jackson.core.JsonProcessingException; 
import com.fasterxml.jackson.databind.DeserializationContext; 
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.annotation.JsonDeserialize; 
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.util.List;
import java.util.ArrayList;
public class CustomerDateAndTimeDeserialize extends  StdDeserializer<Date> {
    public CustomerDateAndTimeDeserialize() { 
      this(null); 
   } 
   public CustomerDateAndTimeDeserialize(Class<Date> t) { 
      super(t); 
   } 


    @Override
    public Date deserialize(JsonParser paramJsonParser,
            DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
          SimpleDateFormat dateFormat1 = new SimpleDateFormat(
          "yyyy-MM-dd HH:mm:ss");
          SimpleDateFormat dateFormat2 = new SimpleDateFormat(
          "yyyy-MM-dd");
          SimpleDateFormat dateFormat3 = new SimpleDateFormat(
          "yyyy-MM-dd HH:mm");
          SimpleDateFormat dateFormat4 = new SimpleDateFormat(
          "yyyy-MM-dd HH");

          List<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();  
          knownPatterns.add(dateFormat1);
          knownPatterns.add(dateFormat2);
          knownPatterns.add(dateFormat3);
          knownPatterns.add(dateFormat4);
        String str = paramJsonParser.getText().trim();
        int i=0;        
        for(SimpleDateFormat onePattern : knownPatterns){
          System.out.println("asdasd");
        try {
            return onePattern.parse(str);
        } catch (Exception e) {                
            i++;
        }        
      }
      if(i==4)
        throw new  ResourceNotFoundException("Wrong Date dateFormat.");
      return null;
    }
}
