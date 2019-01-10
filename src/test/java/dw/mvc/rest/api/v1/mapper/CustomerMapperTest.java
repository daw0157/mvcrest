package dw.mvc.rest.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dw.mvc.rest.api.v1.model.CustomerDTO;
import dw.mvc.rest.model.Customer;

public class CustomerMapperTest {

	public static final String FIRST_NAME = "Joe";
	public static final String LAST_NAME = "Smith";
    public static final long ID = 1L;
	
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;
	
	@Test
	public void customerToCustomerDTOTest() throws Exception {
		Customer customer = new Customer();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(ID);
		
		
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		
		assertEquals(Long.valueOf(ID), customerDTO.getId());
		assertEquals(FIRST_NAME, customerDTO.getFirstname());
		assertEquals(LAST_NAME, customerDTO.getLastname());
	}

}
