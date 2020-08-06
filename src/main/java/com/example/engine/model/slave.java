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
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.FetchType; 
import javax.persistence.JoinColumn;  
import io.swagger.annotations.ApiModel;  
import io.swagger.annotations.ApiModelProperty;  
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.engine.model.Engine;
import java.util.Base64;
@ApiModel(description="All details about the slave")  
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class slave {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(nullable=false)
  private @Getter @Setter Long id;
  @Column(nullable=false)
  private @Getter @Setter String name;
  @Column(nullable=false)
  private @Getter @Setter Date createdOn;
  @Column(nullable=false)
  private @Getter @Setter Boolean isActive;
  @Column(nullable=false)
  private @Getter @Setter String status;
  @Column(nullable=false)
  private @Getter @Setter String ip;
  @Column(nullable=false)
  private @Getter @Setter String userName;
  @Column(nullable=false)
  private @Getter @Setter String password;
  @Column(nullable=false)
  private @Getter @Setter Integer cores;
  @Column(nullable=false)
  private @Getter @Setter Double ram;  
  @ManyToOne(fetch=FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name="engine_id",referencedColumnName="id")
  private @Getter @Setter Engine engine;
  @Column(nullable=false)
  private @Getter @Setter String category;  
  @ManyToOne(optional=true)
  @JoinColumn(name="slaveType_id",referencedColumnName="id")
  private @Getter @Setter slaveType type;

}

