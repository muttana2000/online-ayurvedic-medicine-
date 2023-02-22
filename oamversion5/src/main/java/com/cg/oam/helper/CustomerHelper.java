package com.cg.oam.helper;

import com.cg.oam.bean.CustomerBean;
import com.cg.oam.entity.Customer;

public class CustomerHelper {
	
	  public static CustomerBean generateCustomerBean(Customer customer) {
	 CustomerBean customerBean=null; 
	 if(customer!=null) 
		 customerBean = new CustomerBean(customer,false);
	 return customerBean; }
	 
}
