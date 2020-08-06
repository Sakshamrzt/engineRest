package com.example.engine.model;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;  
import io.swagger.annotations.ApiModelProperty;  
@ApiModel(description="All details about the slaveType")  
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class slaveType {
  @Id
  @Column(nullable=false)
  private @Getter @Setter String id;
  @Column(nullable=false)
  private @Getter @Setter String name;
}

