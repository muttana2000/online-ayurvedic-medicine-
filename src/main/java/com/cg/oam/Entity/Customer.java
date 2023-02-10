package com.cg.oam.Entity;

import java.util.List;

import com.cg.oam.Bean.CustomerBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("customer")
@EqualsAndHashCode(callSuper=true)
public class Customer extends AbstractUser{
	
	
	 public Customer(CustomerBean customerBean) {
	  age=customerBean.getAge(); 
	  gender=customerBean.getGender();
	  email=customerBean.getEmail();
      phoneNumber=customerBean.getPhoneNumber();
	  address = customerBean.getAddress(); }
	 
	
	@Column(name = "age")
	private Integer age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "email")
	private String email;
	@Column(name = "phone_number")
	private Long phoneNumber;

	@OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
	private List<OrderDetails> orders;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	  private List<Prescription> prescriptions;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
}
