package dw.mvc.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.mvc.rest.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

}
