package com.cg.oam.Bean;

import java.util.Date;
import com.cg.oam.Entity.Customer;
import com.cg.oam.Entity.Medicine;
import com.cg.oam.Entity.OrderDetails;
import com.cg.oam.Entity.Prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsBean {
private Integer orderId;
	
	private Integer noOfItems;

	private String status;

	private Date orderDate;

	private Date delieveryDate;

	private Double totalCost;
	
	private Customer customer;
	
	private Medicine medicine;

	private Prescription prescription;

	
	public OrderDetailsBean(OrderDetails orderDetails) {
		orderId=orderDetails.getOrderId();
		noOfItems=orderDetails.getNoOfItems();
		status=orderDetails.getStatus();
		orderDate=orderDetails.getOrderDate();
		delieveryDate=orderDetails.getDelieveryDate();
		totalCost=orderDetails.getTotalCost();
		customer = orderDetails.getCustomer();
		medicine = orderDetails.getMedicine();
		prescription = orderDetails.getPrescription();		}

}
