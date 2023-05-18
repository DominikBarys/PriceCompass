package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.dto.offer.CreateOfferDTO;
import pl.dominikbarys.dto.offer.UpdateOfferDTO;
import pl.dominikbarys.entity.Offer;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.exception.NotFoundException;
import pl.dominikbarys.repository.OfferRepository;
import pl.dominikbarys.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final ProductRepository productRepository;

    public OfferService(OfferRepository offerRepository, ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
    }

    public List<Offer> findAllOffers(){
        return offerRepository.findAll();
    }

    public Offer findOfferById(Integer id){
        return offerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer by id " + id + " was not found"));
    }

    public Offer addOffer(CreateOfferDTO offer){

        Offer newOffer = new Offer();

        BigDecimal price = offer.getPrice().setScale(2, RoundingMode.DOWN);
        if(offer.getProductName() != null) {
            Product product = productRepository.findByName(offer.getProductName())
                    .orElseThrow(()-> new NotFoundException(
                            "Product with name " + offer.getProductName() + " does not exist"));
            newOffer.setProduct(product);
        }

        newOffer.setPrice(price);

        return offerRepository.save(newOffer);
    }

    public Offer updateOffer(UpdateOfferDTO offer, Integer id){

        Offer updatedOffer = offerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer with id " + id + " does not exist"));

        updatedOffer.setPrice(offer.getPrice().setScale(2, RoundingMode.DOWN));

        Product product = productRepository.findByName(offer.getProductName())
                .orElseThrow(() -> new NotFoundException(
                        "Product with name " + offer.getProductName() + " does not exist"));

        updatedOffer.setProduct(product);

       return offerRepository.save(updatedOffer);
    }

    public Offer assignProductToOffer(Integer offerId, Integer productId){
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new NotFoundException("Offer with id " + offerId + " does not exist"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with id" + productId + " does not exist"));

        offer.setProduct(product);

        return offerRepository.save(offer);
    }


    public void deleteOffer(Integer id){
        offerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer with id " + id + " does not exist"));
        offerRepository.deleteById(id);
    }

}
