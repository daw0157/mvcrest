package dw.mvc.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dw.mvc.rest.api.v1.mapper.CustomerMapper;
import dw.mvc.rest.api.v1.model.CustomerDTO;
import dw.mvc.rest.controllers.v1.CustomerController;
import dw.mvc.rest.model.Customer;
import dw.mvc.rest.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	private CustomerMapper customerMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		super();
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository
				.findAll()
				.stream()
				.map(customer -> {
					CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
					customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
					return customerDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id)
				.map(customerMapper::customerToCustomerDTO)
				.map(customerDTO -> {
					customerDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + id);
					return customerDTO;
				})
				.orElseThrow(RuntimeException::new);
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
	}

	@Override
	public CustomerDTO saveCustomerById(Long id, CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
		customer.setId(id);
		return saveAndReturnDTO(customer);
		
	}
	
	private CustomerDTO saveAndReturnDTO(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
		returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + savedCustomer.getId());
		return returnDTO;
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {
			if(customerDTO.getFirstname() != null) {
				customer.setFirstname(customerDTO.getFirstname());
			}
			
			if(customerDTO.getLastname() != null) {
				customer.setLastname(customerDTO.getLastname());
			}
			
			CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
			returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + id);
			
			return returnDTO;
		}).orElseThrow(RuntimeException::new);
	}
	
}
