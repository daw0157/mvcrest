package dw.mvc.rest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dw.mvc.rest.api.v1.model.CategoryDTO;
import dw.mvc.rest.api.v1.model.CustomerDTO;
import dw.mvc.rest.api.v1.model.CustomerListDTO;
import dw.mvc.rest.service.CustomerService;

@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
	
	public static final String BASE_URL = "/api/v1/customers";
	
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping
	public ResponseEntity getAllCustomers() {
		return new ResponseEntity<CustomerListDTO>(
				new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity getCustomerById(@PathVariable String id) {
		return new ResponseEntity<CustomerDTO>(
				customerService.getCustomerById(Long.valueOf(id)), HttpStatus.OK);
		
	}

}
