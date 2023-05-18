package pl.dominikbarys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominikbarys.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Optional<Product> findByName(String name);

    boolean existsByName(String name); //TODO refactor to be not always inverted

}
