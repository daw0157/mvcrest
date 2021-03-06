package dw.mvc.rest.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import dw.mvc.rest.api.v1.model.CategoryDTO;
import dw.mvc.rest.exceptions.ResourceNotFoundException;
import dw.mvc.rest.service.CategoryService;

public class CategoryControllerTest {

	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	CategoryController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
	}
	
	@Test
	public void testListCategories() throws Exception {
		CategoryDTO cat1 = new CategoryDTO();
		cat1.setId(1L);
		cat1.setName("firstname");
		
		CategoryDTO cat2 = new CategoryDTO();
		cat2.setId(2L);
		cat2.setName("secondname");
		
		List<CategoryDTO> categories = Arrays.asList(cat1, cat2);
		
		when(categoryService.getAllCategories()).thenReturn(categories);
		
		mockMvc.perform(get(CategoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
							
	}
	
	@Test
	public void  testGetByNameCategories() throws Exception {
		CategoryDTO category1 = new CategoryDTO();
        category1.setId(1l);
        category1.setName("name");
        
        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get(CategoryController.BASE_URL + "/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("name")));
        
	}
	
	@Test
	public void testGetByNameNotFound() throws Exception {
		when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(get(CategoryController.BASE_URL + "/FAILED")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

}
