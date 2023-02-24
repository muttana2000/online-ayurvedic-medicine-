package com.cg.oam.bean;

import java.util.ArrayList;
import java.util.List;

import com.cg.oam.entity.Customer;

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
	private String role;
	//private List<OrderDetailsBean> orders;
	private List<PrescriptionBean> prescriptions;
	private AddressBean address;
	
	public CustomerBean(Customer customer,Boolean value) {
		
		userId = customer.getUserId();
		age = customer.getAge();
		gender=customer.getGender();
		email=customer.getEmail();
		phoneNumber=customer.getPhoneNumber();
		firstName = customer.getFirstName();
		lastName = customer.getLastName();
		username = customer.getUsername();
		password = customer.getPassword();
		role = customer.getRole();
		if(value) {
			//orders = new ArrayList<>();
			//customer.getOrders().stream()
			//.forEach(order->{orders.add(new OrderDetailsBean(order,false));
			//});
			prescriptions = new ArrayList<>();
			customer.getPrescriptions().stream()
			.forEach(pre->{prescriptions.add(new PrescriptionBean(pre,false,false));
			});
			address = new AddressBean(customer.getAddress(),false);
		}
		
		
		}


}
