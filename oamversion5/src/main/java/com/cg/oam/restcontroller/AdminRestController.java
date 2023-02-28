package com.cg.oam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.bean.CustomerBean;
import com.cg.oam.bean.MedicineBean;
import com.cg.oam.service.AdminService;

@CrossOrigin(origins="http://localhost:3000")
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
	/*
	 * //view All orders By userId
	 * 
	 * @GetMapping("/getAllOrderByUserId/{userid}") public
	 * ResponseEntity<List<OrderDetails>>
	 * viewAllOrderByCustomerId(@PathVariable("userid") Integer userid){
	 * List<OrderDetails> orderList = adminService.findOrdersByUserId(userid);
	 * return ResponseEntity.ok(orderList); }
	 */

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
	/*
	 * //Find All order
	 * 
	 * @GetMapping("/getAllOrders") public ResponseEntity<List<OrderDetails>>
	 * viewAllOrder(){ List<OrderDetails> allOrders = adminService.findAllOrders();
	 * return ResponseEntity.ok(allOrders); }
	 */
		/*
		 * //Find All order by Status
		 * 
		 * @GetMapping("/getOrderByStatus/{status}") public
		 * ResponseEntity<List<OrderDetailsBean>>
		 * viewOrderByStatus(@PathVariable("status") String status){
		 * List<OrderDetailsBean> orderListByStatus =
		 * adminService.findOrdersByStatus(status); return
		 * ResponseEntity.ok(orderListByStatus); }
		 */
		
		/*
		 * //Update Order by Id
		 * 
		 * @PutMapping("/updateOrderById/{orderid}") public ResponseEntity<String>
		 * updateOrderById(@PathVariable("orderid") int orderid, @RequestBody
		 * OrderDetailsBean orderDetailsBean) {
		 * adminService.updateOrderById(orderid,orderDetailsBean); return
		 * ResponseEntity.ok("Order updated successfully"); }
		 */
}
