package dw.mvc.rest.api.v1.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import dw.mvc.rest.model.Category;
import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	private String name;
	private BigDecimal price;
	private Category category;
	@JsonProperty("product_url")
	private String productUrl;
	
}
