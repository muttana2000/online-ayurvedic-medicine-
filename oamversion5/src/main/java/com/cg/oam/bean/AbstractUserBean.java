package com.cg.oam.bean;

import java.util.List;

import com.cg.oam.entity.AbstractUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractUserBean {
	private int userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String role;
	
	private List<OrderDetailsBean> orders;
	private List<PrescriptionBean> prescriptions;
	public AbstractUserBean(AbstractUser abstractUser) {
		userId=abstractUser.getUserId();
		firstName=abstractUser.getFirstName();
		lastName=abstractUser.getLastName();
		username=abstractUser.getUsername();
		password=abstractUser.getPassword();
		role = abstractUser.getRole();
		
	}
}
