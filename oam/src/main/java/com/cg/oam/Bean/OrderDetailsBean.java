
package com.cg.oam.bean;

import java.time.LocalDate;

import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.OrderDetails;

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
  
  private LocalDate orderDate;
  
  private LocalDate delieveryDate;
  
 private Double totalCost;
 
  private CustomerBean customer;
  
  private Medicine medicine;
  
  private PrescriptionBean prescription;
 
  
  public OrderDetailsBean(OrderDetails orderDetails ,Boolean value) {
  orderId=orderDetails.getOrderId();
  noOfItems=orderDetails.getNoOfItems();
  status=orderDetails.getStatus(); 
  orderDate=orderDetails.getOrderDate();
  delieveryDate=orderDetails.getDelieveryDate();
  totalCost=orderDetails.getTotalCost(); 
  medicine = orderDetails.getMedicine(); 
  if(value) { customer = new
  CustomerBean(orderDetails.getCustomer(),false); 
  prescription = new PrescriptionBean(orderDetails.getPrescription(),false,false); } }

}
