package dw.mvc.rest.service;

import java.util.List;

import dw.mvc.rest.api.v1.model.ProductDTO;

public interface ProductService {

	List<ProductDTO> getAllProducts();
	
	ProductDTO getProductById(Long id);
	
	ProductDTO createNewProduct(ProductDTO productDTO);
	
	ProductDTO saveProductById(Long id, ProductDTO productDTO);
	
	ProductDTO patchProduct(Long id, ProductDTO productDTO);
	
	void deleteProductById(Long id);
	
}
