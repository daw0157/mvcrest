package dw.mvc.rest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dw.mvc.rest.api.v1.model.CustomerDTO;
import dw.mvc.rest.api.v1.model.ProductDTO;
import dw.mvc.rest.api.v1.model.ProductListDTO;
import dw.mvc.rest.service.ProductService;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

	public static final String BASE_URL = "/api/v1/products";
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ProductListDTO getAllProducts() {
		return new ProductListDTO(productService.getAllProducts());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO createNewProduct(@RequestBody ProductDTO productDTO) {
		return productService.createNewProduct(productDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		return productService.saveProductById(id, productDTO);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO patchProductById(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		return productService.patchProduct(id, productDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProductById(@PathVariable Long id) {
		productService.deleteProductById(id);
	}
}
