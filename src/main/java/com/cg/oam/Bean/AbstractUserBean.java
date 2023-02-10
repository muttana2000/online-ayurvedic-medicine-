package com.cg.oam.Bean;

import java.util.List;

import com.cg.oam.Entity.AbstractUser;

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
	private List<OrderDetailsBean> orders;
	private List<PrescriptionBean> prescriptions;
	public AbstractUserBean(AbstractUser abstractUser) {
		userId=abstractUser.getUserId();
		firstName=abstractUser.getFirstName();
		lastName=abstractUser.getLastName();
		username=abstractUser.getUsername();
		password=abstractUser.getPassword();
	}
}
