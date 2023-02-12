package com.cg.oam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.Bean.OrderDetailsBean;
import com.cg.oam.Entity.OrderDetails;
import com.cg.oam.Repository.IOrderDetailsRepository;
import com.cg.oam.exception.EmptyInputException;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;

@Service
public class OrderService {
	@Autowired
	private IOrderDetailsRepository orderRepository;

	public List<OrderDetails> getOrdersByUserId(Integer userId) {
		return orderRepository.findByCustomerUserId(userId);
	}

	// cancel order BY ORDERiD
	public OrderDetails cancelOrder(Integer id) {
		Optional<OrderDetails> checkorder = orderRepository.findById(id);
		if (checkorder == null) {
			throw new NoSuchElementException();
		}
		OrderDetails order = orderRepository.findByOrderDetailsId(id);
		order.setStatus("Cancelled");
		return orderRepository.save(order);
	}

	// cancel order by customerId
	public OrderDetails cancelOrder(Integer userId, Integer orderId) {
		Optional<OrderDetails> order = orderRepository.findById(orderId);
		if (!order.isPresent() || !order.get().getCustomer().getUserId().equals(userId)) {
			throw new NoSuchElementException();
		}
		OrderDetails cancelOrder = order.get();
		cancelOrder.setStatus("Cancelled");
		return orderRepository.save(cancelOrder);
	}

	// view all orders
	// adding
	public OrderDetails addOrder(OrderDetailsBean orderDetailsBean) {
		if (orderDetailsBean.getNoOfItems() < 0 || orderDetailsBean.getTotalCost() < 0) {
			throw new InvalidInputException();
		}
		if (orderDetailsBean.getStatus().isEmpty() || orderDetailsBean.getStatus().length() < 0) {
			throw new EmptyInputException();
		}
		OrderDetails orderDetails = new OrderDetails(orderDetailsBean);
		return orderRepository.save(orderDetails);
	}
	// get by id
		public Optional<OrderDetails> getOrderById(Integer id) {
			Optional<OrderDetails> checkorder = orderRepository.findById(id);
			if (checkorder == null) {
				throw new NoSuchElementException();
			}
			return orderRepository.findById(id);
		}
	// adding
	/*
	 * public OrderDetails addOrder(OrderDetailsBean orderDetailsBean) {
	 * if(orderDetailsBean.getNoOfItems()<0 || orderDetailsBean.getTotalCost()<0) {
	 * throw new InvalidInputException(); }
	 * if(orderDetailsBean.getStatus().isEmpty() ||
	 * orderDetailsBean.getStatus().length()<0) { throw new EmptyInputException(); }
	 * OrderDetails orderDetails = new OrderDetails(orderDetailsBean); return
	 * orderRepository.save(orderDetails); } //get by id public
	 * Optional<OrderDetails> getOrderById(Integer id) { Optional<OrderDetails>
	 * checkorder = orderRepository.findById(id); if (checkorder == null) { throw
	 * new NoSuchElementException(); } return orderRepository.findById(id); }
	 */
	// get all orders
	// public List<OrderDetailsBean> getAllOrders() {
	// List<OrderDetails> list = orderRepository.findAll();
	// List<OrderDetailsBean> beanList = new ArrayList<>();
	// list.stream().forEach(order->beanList.add(OrderDetailsHelper.generateOrderDetailsBean(order)));
	// if(list.isEmpty()) {
	// throw new EmptyResultDataAccessException();
	// }
	// return beanList;
	// }
	/*
	 * //cancel order public OrderDetails cancelOrder(Integer id) {
	 * Optional<OrderDetails> checkorder = orderRepository.findById(id); if
	 * (checkorder == null) { throw new NoSuchElementException(); } OrderDetails
	 * order=orderRepository.findByOrderDetailsId(id); order.setStatus("Cancelled");
	 * return orderRepository.save(order); }
	 */
}
