package com.cg.oam.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {
	/*
	 * public OrderDetails(OrderDetailsBean orderDetailsBean) {
	 * orderId=orderDetailsBean.getOrderId();
	 * noOfItems=orderDetailsBean.getNoOfItems();
	 * status=orderDetailsBean.getStatus();
	 * orderDate=orderDetailsBean.getOrderDate();
	 * delieveryDate=orderDetailsBean.getDelieveryDate();
	 * totalCost=orderDetailsBean.getTotalCost(); medicine=
	 * orderDetailsBean.getMedicine(); }
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	private Integer orderId; // primary key and not null
	@Column(name = "no_of_items")
	private Integer noOfItems;
	@Column(name = "status")
	private String status;
	
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@Column(name = "delivey_date")
	private LocalDate delieveryDate;
	@Column(name = "total_cost")
	private Double totalCost;
	
	
	@ManyToOne
	@JoinColumn(name="fk_customer_id")
	private Customer customer;
	
	//unidirectional
	@OneToOne
	@JoinColumn(name="medicine_id")
	private Medicine medicine;
	
	
	//bidirectional
	@ManyToOne 
	@JoinColumn(name="fk_prescription_id", referencedColumnName = "prescription_id")
	private Prescription prescription;
	

}