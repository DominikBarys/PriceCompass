package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.entity.Offer;
import pl.dominikbarys.exception.OfferNotFoundException;
import pl.dominikbarys.repository.OfferRepository;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> findAllOffers(){
        return offerRepository.findAll();
    }

    public Offer findOfferById(Integer id){
        return offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer by id " + id + " was not found"));
    }

    public Offer addOffer(Offer offer){
        return offerRepository.save(offer);
    }

    public Offer updateOffer(Offer offer){
        return offerRepository.save(offer);
    }

    public void deleteOffer(Integer id){
        offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Offer with id " + id + " does not exist"));
        offerRepository.deleteById(id);
    }

}
