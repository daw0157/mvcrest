package dw.mvc.rest.service;

import java.util.List;

import dw.mvc.rest.api.v1.model.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> getAllCustomers();
	
	CustomerDTO getCustomerById(Long id);
	
}
