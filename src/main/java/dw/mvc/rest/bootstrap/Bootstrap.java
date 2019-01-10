package dw.mvc.rest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dw.mvc.rest.model.Category;
import dw.mvc.rest.model.Customer;
import dw.mvc.rest.repositories.CategoryRepository;
import dw.mvc.rest.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;
	
	private CustomerRepository customerRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		loadCustomers();
		System.out.println("Categories: " + categoryRepository.count());
		System.out.println("Customers: " + customerRepository.count());
	}

	private void loadCustomers() {
		Customer c1 = new Customer();
		c1.setId(1L);
		c1.setFirstname("joe");
		c1.setLastname("schmo");
		
		Customer c2 = new Customer();
		c2.setId(2L);
		c2.setFirstname("tom");
		c2.setLastname("brady");
		
		Customer c3 = new Customer();
		c3.setId(3L);
		c3.setFirstname("phillipe");
		c3.setLastname("rios");
		
		Customer c4 = new Customer();
		c4.setId(4L);
		c4.setFirstname("mark");
		c4.setLastname("stewart");
		
		Customer c5 = new Customer();
		c5.setId(5L);
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

	
}
