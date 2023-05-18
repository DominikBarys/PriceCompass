package pl.dominikbarys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominikbarys.entity.SalesPoint;

import java.util.Optional;

public interface SalesPointRepository extends JpaRepository<SalesPoint, Integer> {

    Optional<SalesPoint> findByName(String name);

    boolean existsByName(String name);

}
