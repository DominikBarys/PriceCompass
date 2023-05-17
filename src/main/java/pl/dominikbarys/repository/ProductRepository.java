package pl.dominikbarys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominikbarys.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);

}
