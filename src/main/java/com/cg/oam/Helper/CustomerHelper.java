package com.cg.oam.Helper;

import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Entity.Customer;

public class CustomerHelper {
	public static CustomerBean generateCustomerBean(Customer customer) {
		CustomerBean customerBean=null;
		if(customer!=null)
			customerBean = new CustomerBean(customer);
		if(customer.getAddress()!=null)
			customerBean.setAddress(customer.getAddress());;
		return customerBean;
	}
}
