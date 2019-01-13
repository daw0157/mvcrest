package dw.mvc.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dw.mvc.rest.api.v1.mapper.ProductMapper;
import dw.mvc.rest.api.v1.model.ProductDTO;
import dw.mvc.rest.controllers.v1.ProductController;
import dw.mvc.rest.model.Product;
import dw.mvc.rest.repositories.ProductRepository;
import dw.mvc.rest.service.ProductService;
import dw.mvc.rest.service.ProductServiceImpl;

public class ProductServiceImplTest {

	private final Long ID = 1L;
	private final String NAME = "fish";
	private final BigDecimal PRICE = BigDecimal.valueOf(2.5);
	
	@Mock
	ProductRepository productRepository;
	
	ProductService productService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		productService = new ProductServiceImpl(productRepository, ProductMapper.INSTANCE);
	}
	
	@Test
	public void testGetAllProducts() {
		List<Product> products = Arrays.asList(new Product(), new Product(), new Product());
		
		when(productRepository.findAll()).thenReturn(products);
		
		List<ProductDTO> allProductDTOs = productService.getAllProducts();
		
		assertEquals(3, allProductDTOs.size());
	}

	@Test
	public void testGetProductById() {
		Product product = new Product();
		product.setName(NAME);
		product.setPrice(PRICE);
		product.setId(ID);
		
		when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));
		
		ProductDTO prodDTO = productService.getProductById(ID);
		
		assertNotNull(prodDTO);
		assertEquals(NAME, prodDTO.getName());
		assertEquals(PRICE, prodDTO.getPrice());
	}

	@Test
	public void testCreateNewProduct() {
		ProductDTO prod = new ProductDTO();
		prod.setId(ID);
		prod.setName(NAME);
		prod.setPrice(PRICE);
		
		Product created = new Product();
		created.setId(prod.getId());
		created.setName(prod.getName());
		created.setPrice(prod.getPrice());
		
		when(productRepository.save(any(Product.class))).thenReturn(created);
		
		ProductDTO savedDTO = productService.createNewProduct(prod);
		
		assertEquals(prod.getName(), savedDTO.getName());
		assertEquals(prod.getPrice(), savedDTO.getPrice());
		assertEquals(ProductController.BASE_URL + "/1", savedDTO.getProductUrl());
	}

	@Test
	public void testSaveProductById() {
		ProductDTO prod = new ProductDTO();
		prod.setId(ID);
		prod.setName(NAME);
		prod.setPrice(PRICE);
		
		Product created = new Product();
		created.setId(prod.getId());
		created.setName(prod.getName());
		created.setPrice(prod.getPrice());
		
		when(productRepository.save(any(Product.class))).thenReturn(created);
		
		ProductDTO saved = productService.saveProductById(ID, prod);
		
		assertEquals(prod.getName(), saved.getName());
		assertEquals(prod.getPrice(), saved.getPrice());
		assertEquals(ProductController.BASE_URL + "/1", saved.getProductUrl());
	}

	@Test
	public void testDeleteProductById() {
		productRepository.deleteById(ID);
		
		verify(productRepository, times(1)).deleteById(anyLong());
	}

}
