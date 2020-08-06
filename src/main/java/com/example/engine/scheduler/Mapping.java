package com.example.engine.scheduler;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
@Component
public class Mapping {

    public Map<String, String> getMapping(Resource res) throws IOException {

        Map<String, String> ipMap = new HashMap<>();

        InputStream is = res.getInputStream();
          BufferedReader br = new BufferedReader(new InputStreamReader(is));

          String line;
          while ((line = br.readLine()) != null) {
            String[] words = line.split("\\s+");                        
                    ipMap.put(words[0], words[1]);              
          }
          br.close();
        return ipMap;
        }
        
    }
