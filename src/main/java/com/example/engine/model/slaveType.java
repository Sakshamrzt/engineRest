package com.example.engine.model;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;  
import io.swagger.annotations.ApiModelProperty;  
import org.hibernate.id.UUIDGenerator;
import org.hibernate.annotations.Type;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
@ApiModel(description="All details about the slaveType")  
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class slaveType {
  @Id
  @GeneratedValue(generator="UUID")
  @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
  @Column(nullable=false,updatable=false)
  @Type(type="uuid-char")
  private   UUID id;
  @Column(nullable=false)
  private   String name;
}