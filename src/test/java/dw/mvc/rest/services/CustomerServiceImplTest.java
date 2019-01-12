package dw.mvc.rest.services;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dw.mvc.rest.api.v1.mapper.CustomerMapper;
import dw.mvc.rest.api.v1.model.CustomerDTO;
import dw.mvc.rest.controllers.v1.CustomerController;
import dw.mvc.rest.model.Customer;
import dw.mvc.rest.repositories.CustomerRepository;
import dw.mvc.rest.service.CustomerService;
import dw.mvc.rest.service.CustomerServiceImpl;

public class CustomerServiceImplTest {

	CustomerService customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}
	
	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
		
		when(customerRepository.findAll()).thenReturn(customers);
		
		List<CustomerDTO> custDTOs = customerService.getAllCustomers();
		
		assertEquals(3, custDTOs.size());
	}

	@Test
	public void testGetCustomerById() throws Exception {
		Customer cust = new Customer();
		cust.setId(1l);
		cust.setFirstname("dan");

		Optional<Customer> opCust = Optional.of(cust);
		
		when(customerRepository.findById(anyLong())).thenReturn(opCust);

		if(!opCust.isPresent()) {
			throw new Exception("not found");
		}
		
		CustomerDTO customerDTO = customerService.getCustomerById(1l);
		
		assertEquals("dan", customerDTO.getFirstname());
	}
	
	@Test
	public void createNewCustomer() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname("john");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstname(customerDTO.getFirstname());
		savedCustomer.setLastname(customerDTO.getLastname());
		savedCustomer.setId(1L); 
		
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);
		
		assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
		assertEquals(CustomerController.BASE_URL + "/1", savedDTO.getCustomerUrl());
	}
	
	@Test
	public void saveCustomerById() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname("john");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstname(customerDTO.getFirstname());
		savedCustomer.setLastname(customerDTO.getLastname());
		savedCustomer.setId(1L); 
		
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		CustomerDTO savedDTO = customerService.saveCustomerById(1L, customerDTO);
		
		assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
		assertEquals(CustomerController.BASE_URL + "/1", savedDTO.getCustomerUrl());
	}
	
	@Test
	public void testDeleteCustomerById() throws Exception {
		Long id = 1L;
		
		customerRepository.deleteById(id);
		
		verify(customerRepository, times(1)).deleteById(anyLong());
	}
	
}