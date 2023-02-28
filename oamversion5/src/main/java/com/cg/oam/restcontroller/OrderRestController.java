package com.cg.oam.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.bean.OrderDetailsBean;
import com.cg.oam.entity.OrderDetails;
import com.cg.oam.service.OrderService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	//Add order
    @PostMapping("/addorder")
    public ResponseEntity<OrderDetailsBean> placeOrderByMedicineIdAndCustomerId(@RequestBody OrderDetails orderDetails){
    	OrderDetailsBean bean = orderService.addOrder(orderDetails);
 	    return ResponseEntity.ok(bean);
    }
	
	@GetMapping("/allOrders/{userId}")
	public ResponseEntity<List<OrderDetailsBean>> getAllOrdersByUserId(@PathVariable Integer userId){
	List<OrderDetailsBean> orders = orderService.getOrdersByUserId(userId);
	return ResponseEntity.ok(orders);
		
	}
	
	@GetMapping("/allOrders")
	public ResponseEntity<List<OrderDetailsBean>> getAllOrders(){
		List<OrderDetailsBean> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderDetailsBean> getOrderByOrderId(@PathVariable Integer orderId){
		OrderDetailsBean orderDetailsBean = orderService.getOrderByOrderId(orderId);
		return ResponseEntity.ok(orderDetailsBean);
	}
	
	@PutMapping("/order/update")
	public ResponseEntity<OrderDetailsBean> updateOrder(@RequestBody OrderDetails orderDetails){
	OrderDetailsBean bean =	orderService.updateOrder(orderDetails);
		return ResponseEntity.ok(bean);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//all order apis are used in both admin and customer restControllers
	
	
	//@Autowired
	  //private OrderService orderService;

	  //@PostMapping("/add")
	  //public ResponseEntity<OrderDetails> addOrder(@RequestBody OrderDetailsBean order) {
	    //OrderDetails addedOrder = orderService.addOrder(order);
	   // return ResponseEntity.ok(addedOrder);
	  //}

	 // @GetMapping("/{id}")
	  //public ResponseEntity<OrderDetails> getOrderById(@PathVariable Integer id) {
	    //Optional<OrderDetails> order = orderService.getOrderById(id);
	   // if (!order.isPresent()) {
	   //   return ResponseEntity.notFound().build();
	   // }
	   // return ResponseEntity.ok(order.get());
	  //}

	  //@GetMapping("/allorders")
	 // public ResponseEntity<List<OrderDetailsBean>> getAllOrders() {
	   // List<OrderDetailsBean> orders = orderService.getAllOrders();
	  //  return ResponseEntity.ok(orders);
	  //}

	  //@PutMapping("/{id}/cancel")
	  //public ResponseEntity<Void> cancelOrder(@PathVariable Integer id) {
	  //  orderService.cancelOrder(id);
	 //   return ResponseEntity.ok().build();
	 // }
}
