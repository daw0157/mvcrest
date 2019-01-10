package dw.mvc.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dw.mvc.rest.api.v1.mapper.CategoryMapper;
import dw.mvc.rest.api.v1.model.CategoryDTO;
import dw.mvc.rest.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	private CategoryMapper categoryMapper;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository
				.findAll()
				.stream()
				.map(categoryMapper::categoryToCategoryDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
