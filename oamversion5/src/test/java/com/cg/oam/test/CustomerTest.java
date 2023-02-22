/*
 * package com.cg.oam.test;
 * 
 * import static org.junit.jupiter.api.Assertions.assertTrue; import static
 * org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.when;
 * 
 * import org.junit.jupiter.api.BeforeEach; import
 * org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import
 * org.springframework.boot.test.context.SpringBootTest;
 * 
 * import com.cg.oam.entity.Customer; import
 * com.cg.oam.repository.ICustomerRepository; import
 * com.cg.oam.service.CustomerService;
 * 
 * @SpringBootTest public class CustomerTest {
 * 
 * @Mock private ICustomerRepository cust; // creating mock instance for class
 * 
 * @InjectMocks private CustomerService service = new CustomerService(); //
 * creating new instance for Service class and // injecting the mock instance
 * 
 * @BeforeEach public void beforeEach() {
 * 
 * Customer cust = new Customer(); cust.setUsername("shiva@3270");
 * when(service.addCustomer(any(Customer.class))).thenReturn(cust); // TODO
 * Auto-generated method stub }
 * 
 * @Test
 * 
 * @DisplayName(value = "Testing customer") void testAddCustomer1() { Customer
 * custDto = new Customer(); custDto.setUsername("shiva@3270");
 * assertTrue(service.addCustomer(custDto).equals("shiva@3270")); } }
 */