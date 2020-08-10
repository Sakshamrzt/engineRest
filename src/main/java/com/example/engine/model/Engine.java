package com.example.engine.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize; 
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Entity;
import com.example.engine.Deserialize.CustomerDateAndTimeDeserialize;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.Lob;
import com.example.engine.model.slave;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;  
import io.swagger.annotations.ApiModelProperty;  
import javax.persistence.OneToMany; 
import javax.persistence.CascadeType; 
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.FetchType; 
import org.hibernate.id.UUIDGenerator;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import com.example.engine.exception.ResourceNotFoundException;
import org.hibernate.annotations.Type;
@ApiModel(description="All details about the engine")
@NoArgsConstructor
@Entity
@Data
public class Engine {
  @ApiModelProperty(value="Unique id for engine")
  @Id
  @GeneratedValue(generator="UUID")
  @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
  @Column(nullable=false,updatable=false)
  @Type(type="uuid-char") 
  private   UUID id;  
  @ApiModelProperty(value="Unique id for creator of engine")
  @Type(type="uuid-char")
  @Column(nullable=false)
  private   UUID createdBy;
  @Column(nullable=false)
  @ApiModelProperty(value="Unique id for name of engine")
  private   String name;
  @Column(nullable=false)
  private   String status;
  @Lob
  @Column
  private   String description;
  @Column(nullable=false)
  //@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
  private   Date createdOn;  
  @Column(nullable=false)
  private   Boolean isActive;  
  @OneToMany(mappedBy="engine",cascade = CascadeType.ALL,fetch= FetchType.LAZY,orphanRemoval = true )
  private    List<slave> slaves= new ArrayList<>();

  // public Engine(UUID createdBy,String name,String status,String description,Date createdOn, Boolean isActive,List<slave> slaves)
  // {
  //   this.createdBy=createdBy;
  //   this.name=name;
  //   this.status=status;
  //   this.description=description;
  //   this.createdOn=createdOn;
  //   this.isActive=isActive;
  //   for(int i=0;i<slaves.size();i++)
  //     this.slaves.add(slaves.get(i));
  // }

}