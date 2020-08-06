package com.example.engine.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.FetchType; 
@ApiModel(description="All details about the engine")
@NoArgsConstructor
@Entity
public class Engine {
  @ApiModelProperty(value="Unique id for engine")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(nullable=false)
  private @Getter @Setter Long id;
  @ApiModelProperty(value="Unique id for creator of engine")
  private @Getter @Setter Long createdBy;
  @Column(nullable=false)
  @ApiModelProperty(value="Unique id for name of engine")
  private @Getter @Setter String name;
  @Column(nullable=false)
  private @Getter @Setter String status;
  private @Getter @Setter String description;
  @Column(nullable=false)
  private @Getter @Setter Date createdOn;  
  @Column(nullable=false)
  private @Getter @Setter Boolean isActive;  
  @OneToMany(mappedBy="engine",cascade = CascadeType.ALL,fetch= FetchType.EAGER,orphanRemoval = true )
  private @Getter @Setter  List<slave> slaves= new ArrayList<>();
}
