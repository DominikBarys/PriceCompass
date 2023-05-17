package pl.dominikbarys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominikbarys.entity.Offer;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
