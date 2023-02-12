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

import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Bean.MedicineBean;
import com.cg.oam.Bean.OrderDetailsBean;
import com.cg.oam.Entity.Customer;
import com.cg.oam.Entity.OrderDetails;
import com.cg.oam.Service.AdminService;
import com.cg.oam.Service.CustomerService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private AdminService adminService;

	/*
	 * //adding customer
	 * 
	 * @PostMapping("/addCustomer") public ResponseEntity<String>
	 * addCustomer(@RequestBody Customer customer) {
	 * customerService.addCustomer(customer); return
	 * ResponseEntity.ok("Customer Saved"); }
	 */
	// view all customers
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerBean>> viewAllCustomers() {
		List<CustomerBean> allCustomers = adminService.findAllUsers();
		return ResponseEntity.ok(allCustomers);

	}

	// Finding Customer by Id
	@GetMapping("/getCustomerById/{userid}")
	public ResponseEntity<CustomerBean> viewCustomerByID(@PathVariable("userid") Integer userid) {
		CustomerBean custRetrieved = adminService.findCustomerById(userid);
		return ResponseEntity.ok(custRetrieved);
	}

	// Find all medicine by category
	@GetMapping("/getAllMedicineByCategory/{category}")
	public ResponseEntity<List<MedicineBean>> findAllMedicineByCategory(@PathVariable("category") String category) {
		List<MedicineBean> allMedicineByCategory = adminService.findAllMedicineByCategory(category);
		return ResponseEntity.ok(allMedicineByCategory);
	}
	
	//view All orders By userId
		@GetMapping("/getAllOrderByUserId/{userid}")
		public ResponseEntity<List<OrderDetailsBean>> viewAllOrderByCustomerId(@PathVariable("userid") Integer userid){
			List<OrderDetailsBean> orderList = adminService.findOrdersByUserId(userid);
			return ResponseEntity.ok(orderList);
		}

	// find medicine by id
	@GetMapping("/getMedicineById/{medicineid}")
	public ResponseEntity<MedicineBean> findMedicineById(@PathVariable("medicineid") Integer medicineid) {
		MedicineBean medicine = adminService.findMedicineById(medicineid);
		return ResponseEntity.ok(medicine);
	}

	// update medicine by id
	@PutMapping("/updateMedicneById/{medicineid}")
	public ResponseEntity<String> updateMedicineById(@RequestBody MedicineBean medicineBean,@PathVariable Integer medicineid) {
		adminService.updateMedicineById(medicineBean,medicineid);
		return ResponseEntity.ok("Medicine updated successfully");
	}
	//Find All order
		@GetMapping("/getAllOrders")
		public ResponseEntity<List<OrderDetailsBean>> viewAllOrder(){
			List<OrderDetailsBean> allOrders = adminService.findAllOrders();
			return ResponseEntity.ok(allOrders);
		}
		
		//Find All order by Status
		@GetMapping("/getOrderByStatus/{status}")
		public ResponseEntity<List<OrderDetailsBean>> viewOrderByStatus(@PathVariable("status") String status){
		List<OrderDetailsBean> orderListByStatus = adminService.findOrdersByStatus(status);
		return ResponseEntity.ok(orderListByStatus);
		}
		
		//Update Order by Id
		@PutMapping("/updateOrderById/{orderid}")
		public ResponseEntity<String> updateOrderById(@PathVariable("orderid") int orderid, @RequestBody OrderDetailsBean orderDetailsBean) {
			adminService.updateOrderById(orderid,orderDetailsBean);
			return ResponseEntity.ok("Order updated successfully");
		}
}
