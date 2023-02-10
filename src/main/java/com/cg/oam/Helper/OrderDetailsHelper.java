package com.cg.oam.Helper;


import com.cg.oam.Bean.OrderDetailsBean;
import com.cg.oam.Entity.OrderDetails;

public class OrderDetailsHelper {
	public static OrderDetailsBean generateOrderDetailsBean(OrderDetails ord) {
		OrderDetailsBean orderDetailsBean = null;
		if(ord!=null) {
			orderDetailsBean = new OrderDetailsBean();
			if(ord.getCustomer()!=null)
			orderDetailsBean.setCustomer(ord.getCustomer());
			if(ord.getPrescription()!=null)
			orderDetailsBean.setPrescription(ord.getPrescription());
			return orderDetailsBean;
		}
		return orderDetailsBean;
	}

}
