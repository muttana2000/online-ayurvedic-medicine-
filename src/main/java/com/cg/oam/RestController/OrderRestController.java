package com.cg.oam.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.Bean.OrderDetailsBean;
import com.cg.oam.Entity.OrderDetails;
import com.cg.oam.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
	@Autowired
	  private OrderService orderService;

	  @PostMapping("/add")
	  public ResponseEntity<OrderDetails> addOrder(@RequestBody OrderDetailsBean order) {
	    OrderDetails addedOrder = orderService.addOrder(order);
	    return ResponseEntity.ok(addedOrder);
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<OrderDetails> getOrderById(@PathVariable Integer id) {
	    Optional<OrderDetails> order = orderService.getOrderById(id);
	    if (!order.isPresent()) {
	      return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(order.get());
	  }

	  @GetMapping("/allorders")
	  public ResponseEntity<List<OrderDetailsBean>> getAllOrders() {
	    List<OrderDetailsBean> orders = orderService.getAllOrders();
	    return ResponseEntity.ok(orders);
	  }

	  @PutMapping("/{id}/cancel")
	  public ResponseEntity<Void> cancelOrder(@PathVariable Integer id) {
	    orderService.cancelOrder(id);
	    return ResponseEntity.ok().build();
	  }
}
