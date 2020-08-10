package com.example.engine.DTO;
import com.example.engine.DTO.slaveDTO;
import java.util.List;
import lombok.Data;
import java.util.Date;
@Data
public class slaveMainDTO
{  
private String name;	
private String description;
private List<slaveDTO> slaveList;
}