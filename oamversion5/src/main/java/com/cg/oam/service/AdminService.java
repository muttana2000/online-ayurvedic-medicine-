package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.CustomerBean;
import com.cg.oam.bean.MedicineBean;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.EmptyResultDataAccessException;
import com.cg.oam.exception.NoSuchElementException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IMedicineRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {
	
	//customer
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private IMedicineRepository medicineRepository;
	

	// method to get customer by its id
	public CustomerBean findCustomerById(Integer id) {
		Optional<Customer> checkcustomer = customerRepository.findById(id);
	    if (checkcustomer.isEmpty()) {
	      throw new NoSuchElementException();
	    }
		Customer customer = customerRepository.findById(id).get();
		return new CustomerBean(customer,true);
	}
   
	// method to find all users means all customers also
	public List<CustomerBean> findAllUsers() {
		List<CustomerBean> cusList = new ArrayList<>();
		
		customerRepository.findAll().forEach(cus -> cusList.add(new CustomerBean(cus,false)));
		if(cusList.isEmpty()) {
			throw new EmptyResultDataAccessException();
		}
		return cusList;
	}

	/*
	 * //method to find all orders done by a customer public List<OrderDetailsBean>
	 * findOrdersByUserId(Integer userId) { Optional<Customer> checkcustomer =
	 * customerRepository.findById(userId); if (checkcustomer.isEmpty()) { throw new
	 * NoSuchElementException(); } Customer customer =
	 * customerRepository.findByUserId(userId); List<OrderDetailsBean> orderList =
	 * new ArrayList<>(); customer.getOrders().forEach(order -> orderList.add(new
	 * OrderDetailsBean(order,true))); return orderList; }
	 */
	
	//Method to find medicine by category
		
		public List<MedicineBean> findAllMedicineByCategory(String categoryname) {
			List<MedicineBean> medicineList = new ArrayList<>();
			medicineRepository.findAll().forEach(medicine -> {
				if (medicine.getCategory().getCategoryName().equals(categoryname)) 
				{
					medicineList.add(new MedicineBean(medicine, true,false));
				}
			});
			if(medicineList.isEmpty()) {
				throw new EmptyResultDataAccessException();
			}
			return medicineList;
		}
		
		//Method to find medicine by id
		public MedicineBean findMedicineById(Integer medicineId) {
			Optional<Medicine> checkMedicine = medicineRepository.findById(medicineId);
		    if (checkMedicine.isEmpty()) {
		      throw new NoSuchElementException();
		    }
			Medicine medicine = medicineRepository.findById(medicineId).get();
			return new MedicineBean(medicine,true, false);
		}

		//Method to update medicine by id
		public Medicine updateMedicineById(MedicineBean medicine,Integer medicineId) {
			 Medicine checkMedicine = medicineRepository.findByMedicineId(medicineId);
			    if (checkMedicine==null) {
			      throw new NoSuchElementException();
			    }
			   
				checkMedicine.setMedicineName(medicine.getMedicineName());
				checkMedicine.setCompanyName(medicine.getCompanyName());
				checkMedicine.setMedicineCost(medicine.getMedicineCost());
				return medicineRepository.save(checkMedicine);
		}
		
		/*
		 * //method to find all order public List<OrderDetailsBean> findAllOrders() {
		 * List<OrderDetailsBean> orderList = new ArrayList<>(); List<Customer>
		 * customerList = customerRepository.findAll(); for (Customer customer :
		 * customerList) { customer.getOrders().forEach(order -> orderList.add(new
		 * OrderDetailsBean(order, false))); } if (orderList.isEmpty()) { throw new
		 * EmptyResultDataAccessException(); } return orderList; }
		 */
		
		/*
		 * //Method to find all order by status public List<OrderDetailsBean>
		 * findOrdersByStatus(String status) { List<OrderDetailsBean> orderList = new
		 * ArrayList<>(); List<Customer> customerList = customerRepository.findAll();
		 * for (Customer customer : customerList) {
		 * customer.getOrders().stream().filter(order ->
		 * order.getStatus().equalsIgnoreCase(status)) .forEach(order ->
		 * orderList.add(new OrderDetailsBean(order, true))); } if (orderList.isEmpty())
		 * { throw new EmptyResultDataAccessException(); } return orderList; }
		 */
		/*
		 * //Method to update order by Id public OrderDetailsBean
		 * updateOrderById(Integer orderId,OrderDetailsBean orderDetails) {
		 * Optional<OrderDetails> checkOrder = orderRepository.findById(orderId); if
		 * (checkOrder.isEmpty()) { throw new NoSuchElementException(); } OrderDetails
		 * order = checkOrder.get(); order.setStatus(orderDetails.getStatus());
		 * order.setOrderDate(orderDetails.getOrderDate());
		 * order.setTotalCost(orderDetails.getTotalCost()); return new
		 * OrderDetailsBean(orderRepository.save(order), false); }
		 */
	
	
	
	/*
	 * // method to creating customer public Customer createUser(Customer customer)
	 * { if(customer.getFirstName() == null || customer.getFirstName().length()==0
	 * || customer.getLastName() == null || customer.getLastName().length()==0 ||
	 * customer.getGender() == null || customer.getGender().length()==0 ||
	 * customer.getEmail() == null || customer.getEmail().length()==0) { throw new
	 * EmptyInputException(); } if(customer.getAge()==0 || customer.getAge()<0 ||
	 * customer.getPhoneNumber()==0 || customer.getPhoneNumber()<0) { throw new
	 * InvalidInputException(); } return customerRepository.save(customer); }
	 */
	/*
	 * // Method to update user, can change user details public Customer
	 * updateUser(Customer customer) { return customerRepository.save(customer); }
	 */
	
	

	
}
