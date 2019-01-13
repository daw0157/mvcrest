package dw.mvc.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dw.mvc.rest.api.v1.mapper.ProductMapper;
import dw.mvc.rest.api.v1.model.ProductDTO;
import dw.mvc.rest.controllers.v1.ProductController;
import dw.mvc.rest.exceptions.ResourceNotFoundException;
import dw.mvc.rest.model.Product;
import dw.mvc.rest.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	private ProductMapper productMapper;
	
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository
				.findAll()
				.stream()
				.map(product -> {
					ProductDTO productDTO = productMapper.productToProductDTO(product);
					productDTO.setProductUrl(getProductUrl(product.getId()));
					return productDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(Long id) {
		return productRepository.findById(id)
				.map(productMapper::productToProductDTO)
				.map(productDTO -> {
					productDTO.setProductUrl(getProductUrl(id));
					return productDTO;
				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public ProductDTO createNewProduct(ProductDTO productDTO) {
		return saveAndReturnDTO(productMapper.productDtoToProduct(productDTO));
	}

	@Override
	public ProductDTO saveProductById(Long id, ProductDTO productDTO) {
		Product product = productMapper.productDtoToProduct(productDTO);
		product.setId(id);
		return saveAndReturnDTO(product);
	}

	@Override
	public ProductDTO patchProduct(Long id, ProductDTO productDTO) {
		return productRepository.findById(id).map(product -> {
			if(productDTO.getName() != null) {
				product.setName(productDTO.getName());
			}
			
			if(productDTO.getPrice() != null) {
				product.setPrice(productDTO.getPrice());
			}
			
			if(productDTO.getCategory() != null) {
				product.setCategory(productDTO.getCategory());
			}
			
			ProductDTO returnDTO = productMapper.productToProductDTO(productRepository.save(product));
			returnDTO.setProductUrl(getProductUrl(id));
			
			return returnDTO;
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);

	}
	
	private ProductDTO saveAndReturnDTO(Product product) {
		Product savedProduct = productRepository.save(product);
		ProductDTO returnDTO = productMapper.productToProductDTO(savedProduct);
		returnDTO.setProductUrl(getProductUrl(savedProduct.getId()));
		return returnDTO;
	}
	
	private String getProductUrl(Long id) {
		return ProductController.BASE_URL + "/" + id;
	}

}
