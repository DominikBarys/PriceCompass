package pl.dominikbarys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominikbarys.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    boolean existsByName(String name);

}
