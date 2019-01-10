package dw.mvc.rest.service;

import java.util.List;

import dw.mvc.rest.api.v1.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();
	
	CategoryDTO getCategoryByName(String name);
	
}
