package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.OrderDetailsBean;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.OrderDetails;
import com.cg.oam.entity.Prescription;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IMedicineRepository;
import com.cg.oam.repository.IOrderDetailsRepository;
import com.cg.oam.repository.IPrescriptionRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	@Autowired
	private IOrderDetailsRepository orderRepository;
	@Autowired
	private IMedicineRepository medicineRepository;
	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IPrescriptionRepository prescriptionRepository;

	public List<OrderDetailsBean> getOrdersByUserId(Integer userId) {
		// get all orders then filter out based on usedId
		List<OrderDetailsBean> newOrders = new ArrayList<>();
		orderRepository.findAll().stream().forEach(order -> {
			if (order.getCustomer().getUserId() == userId) {
				newOrders.add(new OrderDetailsBean(order, true));
			}
		});

		return newOrders;
	}

	// cancel order by customerId
	@Transactional
	public OrderDetailsBean cancelOrder(Integer userId, Integer orderId) {
		Optional<OrderDetails> optionalOrder = orderRepository.findById(orderId);
		if (!optionalOrder.isEmpty() || optionalOrder.get().getCustomer().getUserId() != (userId)) {
			throw new NoSuchElementException();
		}
		OrderDetails cancelOrder = optionalOrder.get();
		cancelOrder.setStatus("Cancelled");
		OrderDetailsBean orderDetailsBean = new OrderDetailsBean(cancelOrder, false);
		orderRepository.save(cancelOrder);
		return orderDetailsBean;
	}

	// adding
	@Transactional
	public OrderDetailsBean addOrder(OrderDetails orderDetails) {
		if (orderDetails.getNoOfItems() <= 0) {
			throw new InvalidInputException();
		}
		Optional<Medicine> optionalMedicine = medicineRepository.findById(orderDetails.getMedicine().getMedicineId());
		if (optionalMedicine.isEmpty()) {
			throw new NoSuchElementException();
		}
		orderDetails.setMedicine(optionalMedicine.get());
		Optional<Customer> optionalCustomer = customerRepository.findById(orderDetails.getCustomer().getUserId());
		if (optionalCustomer.isEmpty()) {
			throw new NoSuchElementException();
		}
		orderDetails.setCustomer(optionalCustomer.get());

		Optional<Prescription> optionalPrescription = prescriptionRepository
				.findById(orderDetails.getPrescription().getPrescriptionId());
		if (optionalPrescription.isEmpty()) {
			throw new NoSuchElementException();
		}
		orderDetails.setPrescription(optionalPrescription.get());
		float medCost = orderDetails.getMedicine().getMedicineCost();
		Integer noOfItems = orderDetails.getNoOfItems();
		Double totalCost = (double) (medCost * noOfItems);
		orderDetails.setTotalCost(totalCost);
		List<OrderDetails> orders = optionalCustomer.get().getOrders();
		orders.add(orderDetails);
		orderRepository.save(orderDetails);
		OrderDetailsBean bean = new OrderDetailsBean(orderDetails, true);
		return bean;
	}

	// get order by orderId
	@Transactional
	public OrderDetailsBean getOrderByOrderId(Integer id) {
		Optional<OrderDetails> checkorder = orderRepository.findById(id);
		if (checkorder.isEmpty()) {
			throw new NoSuchElementException();
		}
		OrderDetails orderDetails = checkorder.get();
		OrderDetailsBean orderDetailsBean = new OrderDetailsBean(orderDetails, true);
		return orderDetailsBean;
	}

	// get all orders
	@Transactional
	public List<OrderDetailsBean> getAllOrders() {
		List<OrderDetailsBean> orders = new ArrayList<>();
		orderRepository.findAll().stream().forEach(order -> {
			orders.add(new OrderDetailsBean(order, false));
		});
		return orders;
	}
	
	@Transactional
	public OrderDetailsBean updateOrder(OrderDetails orderDetails) {
		if (orderDetails.getNoOfItems() <= 0) {
			throw new InvalidInputException();
		}
		Optional<Medicine> optionalMedicine = medicineRepository.findById(orderDetails.getMedicine().getMedicineId());
		if (optionalMedicine.isEmpty()) {
			throw new NoSuchElementException();
		}
		orderDetails.setMedicine(optionalMedicine.get());
		Optional<Customer> optionalCustomer = customerRepository.findById(orderDetails.getCustomer().getUserId());
		if (optionalCustomer.isEmpty()) {
			throw new NoSuchElementException();
		}
		orderDetails.setCustomer(optionalCustomer.get());

		Optional<Prescription> optionalPrescription = prescriptionRepository
				.findById(orderDetails.getPrescription().getPrescriptionId());
		if (optionalPrescription.isEmpty()) {
			throw new NoSuchElementException();
		}
		orderDetails.setPrescription(optionalPrescription.get());
		float medCost = orderDetails.getMedicine().getMedicineCost();
		Integer noOfItems = orderDetails.getNoOfItems();
		Double totalCost = (double) (medCost * noOfItems);
		orderDetails.setTotalCost(totalCost);
		List<OrderDetails> orders = optionalCustomer.get().getOrders();
		orders.add(orderDetails);
		orderRepository.save(orderDetails);
		OrderDetailsBean bean = new OrderDetailsBean(orderDetails, false);
		return bean;
	}

}
