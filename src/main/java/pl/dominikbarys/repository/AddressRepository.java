package pl.dominikbarys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominikbarys.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
