package dw.mvc.rest.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dw.mvc.rest.model.Category;
import dw.mvc.rest.model.Customer;
import dw.mvc.rest.model.Product;
import dw.mvc.rest.repositories.CategoryRepository;
import dw.mvc.rest.repositories.CustomerRepository;
import dw.mvc.rest.repositories.ProductRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;
	
	private CustomerRepository customerRepository;
	
	private ProductRepository productRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
			ProductRepository productRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
		loadProducts();
		System.out.println("Categories: " + categoryRepository.count());
		System.out.println("Customers: " + customerRepository.count());
		System.out.println("Products: " + productRepository.count());
	}

	private void loadCustomers() {
		Customer c1 = new Customer();
		c1.setFirstname("joe");
		c1.setLastname("schmo");
		
		Customer c2 = new Customer();
		c2.setFirstname("tom");
		c2.setLastname("brady");
		
		Customer c3 = new Customer();
		c3.setFirstname("phillipe");
		c3.setLastname("rios");
		
		Customer c4 = new Customer();
		c4.setFirstname("mark");
		c4.setLastname("stewart");
		
		Customer c5 = new Customer();
		c5.setFirstname("rob");
		c5.setLastname("gronk");
		
		customerRepository.save(c1);
		customerRepository.save(c2);
		customerRepository.save(c3);
		customerRepository.save(c4);
		customerRepository.save(c5);
	}

	private void loadCategories() {
		Category fruits = new Category();
		fruits.setName("Fruits");
		Category dried = new Category();
		dried.setName("Dried");
		Category fresh = new Category();
		fresh.setName("Fresh");
		Category exotic = new Category();
		exotic.setName("Exotic");
		Category nuts = new Category();
		nuts.setName("Nuts");
		
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
	}
	
	private void loadProducts() {
		Product bananas = new Product();
		bananas.setName("Bananas");
		bananas.setPrice(BigDecimal.valueOf(1.99));
		bananas.setCategory(categoryRepository.findByName("Fruits"));
		Product oranges = new Product();
		oranges.setName("Oranges");
		oranges.setCategory(categoryRepository.findByName("Fruits"));
		oranges.setPrice(BigDecimal.valueOf(1.50));
		Product pineapples = new Product();
		pineapples.setName("Pineapples");
		pineapples.setPrice(BigDecimal.valueOf(4.99));
		pineapples.setCategory(categoryRepository.findByName("Dried"));
		Product apples = new Product();
		apples.setName("Apples");
		apples.setPrice(BigDecimal.valueOf(0.99));
		apples.setCategory(categoryRepository.findByName("Fresh"));
		Product cranberries = new Product();
		cranberries.setName("Cranberries");
		cranberries.setPrice(BigDecimal.valueOf(3.50));
		cranberries.setCategory(categoryRepository.findByName("Nuts"));
		
		productRepository.save(bananas);
		productRepository.save(oranges);
		productRepository.save(pineapples);
		productRepository.save(apples);
		productRepository.save(cranberries);
		
	}
	
}
