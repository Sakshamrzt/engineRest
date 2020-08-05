package com.example.engine.model;
import java.util.Date;
import com.example.engine.model.Engine;
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
  @JoinColumn(name="engine_id")
  private @Getter @Setter Engine engine;
//   public slave(Long id,Integer cores,String name,String status,String userName,String password,String ip,Double ram,Date createdOn,Boolean isActive)    {
//    this.id=id;
//    this.cores=cores;
//    this.name=name;
//    this.status=status;
//    this.userName=userName;
//    this.password=Base64.getEncoder().encodeToString(password.getBytes());
//    this.ip=ip;
//    this.ram=ram;
//    this.createdOn=createdOn;
//    this.isActive=isActive;
// }
}

