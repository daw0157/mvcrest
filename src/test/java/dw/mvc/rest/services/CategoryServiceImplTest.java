package dw.mvc.rest.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dw.mvc.rest.api.v1.mapper.CategoryMapper;
import dw.mvc.rest.api.v1.model.CategoryDTO;
import dw.mvc.rest.model.Category;
import dw.mvc.rest.repositories.CategoryRepository;
import dw.mvc.rest.service.CategoryService;
import dw.mvc.rest.service.CategoryServiceImpl;

public class CategoryServiceImplTest {

	private static final Long ID = 2L;
	private static final String NAME = "tom";
	
	CategoryService categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	
		categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
	}
	
	@Test
	public void testGetAllCategories() {
		//given
		List<Category> categories = new ArrayList<>();
		categories.add(new Category());
		categories.add(new Category());
		categories.add(new Category());
		
		//when
		when(categoryRepository.findAll()).thenReturn(categories);
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
		
		//then
		assertEquals(3, categoryDTOs.size());
	}

	@Test
	public void testGetCategoryByName() {
		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);
		
		when(categoryRepository.findByName(anyString())).thenReturn(category);
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
		
		assertEquals(ID, categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}

}
