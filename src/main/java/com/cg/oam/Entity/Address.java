package com.cg.oam.Entity;

import com.cg.oam.Bean.AddressBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
	public Address(AddressBean addressBean) {
		addressId=addressBean.getAddressId();
		houseNumber=addressBean.getHouseNumber();
		street=addressBean.getStreet();
		landmark=addressBean.getLandmark();
		city=addressBean.getCity();
		state=addressBean.getState();
		pinCode=addressBean.getPinCode();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "address_id")
	private Integer addressId;
	@Column(name = "house_number")
	private Integer houseNumber;
	@Column(name = "street")
	private String street;
	@Column(name = "landmark")
	private String landmark;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "pinCode")
	private Integer pinCode;
	
}