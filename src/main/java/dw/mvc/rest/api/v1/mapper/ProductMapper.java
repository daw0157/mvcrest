package dw.mvc.rest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dw.mvc.rest.api.v1.model.ProductDTO;
import dw.mvc.rest.model.Product;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDTO productToProductDTO(Product product);
	
	Product productDtoToProduct(ProductDTO product);
	
}
