package dw.mvc.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.mvc.rest.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
