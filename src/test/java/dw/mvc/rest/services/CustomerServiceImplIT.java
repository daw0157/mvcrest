package dw.mvc.rest.services;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import dw.mvc.rest.api.v1.mapper.CustomerMapper;
import dw.mvc.rest.api.v1.model.CustomerDTO;
import dw.mvc.rest.bootstrap.Bootstrap;
import dw.mvc.rest.model.Customer;
import dw.mvc.rest.repositories.CategoryRepository;
import dw.mvc.rest.repositories.CustomerRepository;
import dw.mvc.rest.service.CustomerService;
import dw.mvc.rest.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	CustomerService customerService;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Loading Customer Data");
		System.out.println(customerRepository.findAll().size());
		
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
		bootstrap.run();
		
		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
		
	}
	
	@Test
	public void patchCustomerUpdateFirstName() throws Exception {
		String updateName = "updatedname";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname(updateName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updateName, updatedCustomer.getFirstname());
		assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstname())));
		assertThat(originalLastName, equalTo(updatedCustomer.getLastname()));
	}
	
	@Test
	public void patchCustomerUpdateLasttName() throws Exception {
		String updateName = "updatedname";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastname(updateName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updateName, updatedCustomer.getLastname());
		assertThat(originalFirstName, equalTo(updatedCustomer.getFirstname()));
		assertThat(originalLastName, not(equalTo(updatedCustomer.getLastname())));
	}

	private Long getCustomerIdValue() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Customers found: " + customers.size());
		return customers.get(0).getId();
	}
}
