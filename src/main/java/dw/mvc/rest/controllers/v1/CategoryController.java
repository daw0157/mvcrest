package dw.mvc.rest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dw.mvc.rest.api.v1.model.CategoryDTO;
import dw.mvc.rest.api.v1.model.CategoryListDTO;
import dw.mvc.rest.service.CategoryService;

@Controller
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
	
	public static final String BASE_URL = "/api/v1/categories";
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public ResponseEntity<CategoryListDTO> getCategories(){
		return new ResponseEntity<CategoryListDTO>(
				new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
	}
	
	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
		return new ResponseEntity<CategoryDTO>(
				categoryService.getCategoryByName(name), HttpStatus.OK);
	}

}
