package com.cg.oam.bean;

import com.cg.oam.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBean {
	private Integer addressId;
	private Integer houseNumber;
	private String street;
	private String landmark;
	private String city;
	private String state;
	private Integer pinCode;
	private CustomerBean customer;
	
	public AddressBean(Address address,Boolean buildCustomer) {
		addressId=address.getAddressId();
		houseNumber=address.getHouseNumber();
		street=address.getStreet();
		landmark=address.getLandmark();
		city=address.getCity();
		state=address.getState();
		pinCode=address.getPinCode();
		//if(buildCustomer) {
		//	customerBean = new CustomerBean(address.getCustomer(),false);
		//}
	}

}
