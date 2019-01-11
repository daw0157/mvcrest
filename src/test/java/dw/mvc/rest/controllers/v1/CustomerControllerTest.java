package dw.mvc.rest.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import dw.mvc.rest.api.v1.model.CustomerDTO;
import dw.mvc.rest.service.CustomerService;

public class CustomerControllerTest {

	@Mock
	CustomerService customerService;
	
	@InjectMocks
	CustomerController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGetAllCustomers() throws Exception {
		CustomerDTO c1 = new CustomerDTO();
		c1.setId(1L);
		c1.setFirstname("dan");
		
		CustomerDTO c2 = new CustomerDTO();
		c2.setId(2L);
		c2.setFirstname("jen");
		
		List<CustomerDTO> customers = Arrays.asList(c1, c2);
		
		when(customerService.getAllCustomers()).thenReturn(customers);
		
		mockMvc.perform(get(CustomerController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	public void testGetCustomerById() throws Exception{
		CustomerDTO c1 = new CustomerDTO();
        c1.setId(1l);
        c1.setFirstname("name");
        c1.setLastname("last");
        
        when(customerService.getCustomerById(anyLong())).thenReturn(c1);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("name")));
	}

	@Test
	public void createNewCustomer() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstname("jake");
		customer.setLastname("stone");
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstname(customer.getFirstname());
		returnDTO.setLastname(customer.getLastname());
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);
		
		mockMvc.perform(post(CustomerController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(customer)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstname", equalTo("jake")))
			.andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
	}
	
	@Test
	public void updateCustomer() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstname("jake");
		customer.setLastname("stone");
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstname(customer.getFirstname());
		returnDTO.setLastname(customer.getLastname());
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		when(customerService.saveCustomerById(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);
		
		mockMvc.perform(put(CustomerController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(customer)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstname", equalTo("jake")))
			.andExpect(jsonPath("$.lastname", equalTo("stone")))
			.andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
	}
	
	@Test
	public void patchCustomer() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstname("jake");
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstname(customer.getFirstname());
		returnDTO.setLastname("smith");
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);
		
		mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(AbstractRestControllerTest.asJsonString(customer)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstname", equalTo("jake")))
			.andExpect(jsonPath("$.lastname", equalTo("smith")))
			.andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
	}
}
