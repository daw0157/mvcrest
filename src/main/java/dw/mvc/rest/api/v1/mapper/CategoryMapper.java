package dw.mvc.rest.api.v1.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dw.mvc.rest.api.v1.model.CategoryDTO;
import dw.mvc.rest.model.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
	
    Category categoryDtoToCategory(CategoryDTO categoryDto);
}
