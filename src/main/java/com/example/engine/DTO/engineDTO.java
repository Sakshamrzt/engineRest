package com.example.engine.DTO;
import com.example.engine.DTO.slaveMainDTO;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import com.example.engine.model.slave;
@Data
public class engineDTO
{  
  private String name;  
  private String description;  
  private Date createdOn;  
  private Boolean isActive;    
  private List<slave> slaveList;
}