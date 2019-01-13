package dw.mvc.rest.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import dw.mvc.rest.api.v1.model.ProductDTO;
import dw.mvc.rest.model.Product;

public class ProductMapperTest {

	public static final String NAME = "Peaches";
	public static final BigDecimal PRICE = BigDecimal.valueOf(2.50);
    public static final long ID = 1L;
	
	ProductMapper productMapper = ProductMapper.INSTANCE;
	
	@Test
	public void productToProductDTOTest() throws Exception {
		Product product = new Product();
		product.setName(NAME);
		product.setPrice(PRICE);
		product.setId(ID);
		
		
		ProductDTO ProductDTO = productMapper.productToProductDTO(product);
		
		assertEquals(Long.valueOf(ID), ProductDTO.getId());
		assertEquals(NAME, ProductDTO.getName());
		assertEquals(PRICE, ProductDTO.getPrice());
	}
	
}
