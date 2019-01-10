package dw.mvc.rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.mvc.rest.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
