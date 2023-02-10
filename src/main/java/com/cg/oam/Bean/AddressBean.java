package com.cg.oam.Bean;

import com.cg.oam.Entity.Address;
import com.cg.oam.Entity.Customer;

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
	private Customer customer;
	
	public AddressBean(Address address) {
		addressId=address.getAddressId();
		houseNumber=address.getHouseNumber();
		street=address.getStreet();
		landmark=address.getLandmark();
		city=address.getCity();
		state=address.getState();
		pinCode=address.getPinCode();
		//customer = address.getCustomer();
	}

}
