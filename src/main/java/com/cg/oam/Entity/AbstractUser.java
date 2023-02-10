package com.cg.oam.Entity;

import com.cg.oam.Bean.AbstractUserBean;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public class AbstractUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer userId; // primary key
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_name")
	 private String username;
	@Column(name = "password")
	private String password;
    public AbstractUser(AbstractUserBean abstractUserBean) {
    	userId = abstractUserBean.getUserId();
    	firstName = abstractUserBean.getFirstName();
    	lastName = abstractUserBean.getLastName();
    	username = abstractUserBean.getUsername();
    	password = abstractUserBean.getPassword();
    	
    	}
	
	
	//@OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
	//private List<OrderDetails> orders;
	
	//@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	//private List<Prescription> prescriptions;
	
	
	
}
