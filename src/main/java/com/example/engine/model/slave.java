package com.example.engine.model;
import java.util.Date;
import com.example.engine.model.Engine;
import com.example.engine.model.slaveType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne; 
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.FetchType; 
import javax.persistence.JoinColumn;  
import io.swagger.annotations.ApiModel;  
import io.swagger.annotations.ApiModelProperty;  
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.engine.model.Engine;
import java.util.Base64;
import org.hibernate.id.UUIDGenerator;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
@ApiModel(description="All details about the slave")  
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class slave {
  @Id
  @GeneratedValue(generator="UUID")
  @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
  @Column(nullable=false,updatable=false)
  @Type(type="uuid-char")
  private   UUID id;
  @Column(nullable=false)
  private   String name;
  @Column(nullable=false)
  private   Date createdOn;
  @Column(nullable=false)
  private   Boolean isActive;
  @Column(nullable=false)
  private   String status;
  @Column(nullable=false)
  private   String ip;
  @Column(nullable=false)
  private   String userName;
  @Column(nullable=false)
  private   String password;
  @Column(nullable=false)
  private   Integer cores;
  @Column(nullable=false)
  private   Double ram;  
  @ManyToOne(fetch=FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name="engine_id",referencedColumnName="id")
  private   Engine engine;
  @ManyToOne(optional=true)
  @JoinColumn(name="slaveType_id",referencedColumnName="id")
  private   slaveType type;

}

