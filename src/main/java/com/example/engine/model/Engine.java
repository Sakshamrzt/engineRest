package com.example.engine.model;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import com.example.engine.model.slave;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;  
import io.swagger.annotations.ApiModelProperty;  
import javax.persistence.OneToMany; 
import javax.persistence.CascadeType; 
@ApiModel(description="All details about the engine")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Engine {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(nullable=false)
  private @Getter @Setter Long id;
  @Column(nullable=false)
  private @Getter @Setter Long createdBy;
  @Column(nullable=false)
  private @Getter @Setter String name;
  @Column(nullable=false)
  private @Getter @Setter String status;
  private @Getter @Setter String description;
  @Column(nullable=false)
  private @Getter @Setter Date createdOn;  
  @Column(nullable=false)
  private @Getter @Setter Boolean isActive;  
  @OneToMany(mappedBy="engine",cascade=CascadeType.ALL)  
  private @Getter @Setter List<slave> slaves;   
}
