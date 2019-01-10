package dw.mvc.rest.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dw.mvc.rest.api.v1.model.CategoryDTO;
import dw.mvc.rest.model.Category;

public class CategoryMapperTest {

	public static final String NAME = "Joe";
    public static final long ID = 1L;
	
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	@Test
	public void categoryToCategoryDTO() throws Exception {
		Category category = new Category();
		category.setName(NAME);
		category.setId(ID);
		
		
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		
		assertEquals(Long.valueOf(ID), categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}

}
