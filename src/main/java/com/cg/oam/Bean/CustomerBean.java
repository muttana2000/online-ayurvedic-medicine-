package com.cg.oam.Bean;

import java.util.List;

import com.cg.oam.Entity.Address;
import com.cg.oam.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CustomerBean{
	private Integer userId;
	private Integer age;
	private String gender;
	private String email;
	private Long phoneNumber;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private List<OrderDetailsBean> orders;
	private List<PrescriptionBean> prescriptions;
	private Address address;
	
	public CustomerBean(Customer customer) {
		
		userId = customer.getUserId();
		age = customer.getAge();
		gender=customer.getGender();
		email=customer.getEmail();
		phoneNumber=customer.getPhoneNumber();
		firstName = customer.getFirstName();
		lastName = customer.getLastName();
		username = customer.getUsername();
		password = customer.getPassword();
		address = customer.getAddress();
		//orders = new ArrayList<>();
		//customer.getOrders().stream()
		//.forEach(order->{orders.add(new OrderDetailsBean(order));
		//});
		
		}


}
