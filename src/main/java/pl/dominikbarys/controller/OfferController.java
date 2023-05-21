package pl.dominikbarys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominikbarys.dto.offer.CreateOfferDTO;
import pl.dominikbarys.dto.offer.UpdateOfferDTO;
import pl.dominikbarys.entity.Offer;
import pl.dominikbarys.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Offer>> getAllOffers(){
        List<Offer> offers = offerService.findAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Offer> findOfferById(@PathVariable("id") Integer id){
        Offer offer = offerService.findOfferById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Offer> addOffer(@RequestBody CreateOfferDTO offer){
        Offer newOffer = offerService.addOffer(offer);
        return new ResponseEntity<>(newOffer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Offer> updateOffer(@RequestBody UpdateOfferDTO offer, @PathVariable("id") Integer id){
        Offer updatedOffer = offerService.updateOffer(offer, id);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @PatchMapping("/{offerId}/product/{productId}")
    public ResponseEntity<Offer> assignProductToOffer(@PathVariable("offerId") Integer offerId,
                                                      @PathVariable("productId") Integer productId){
        Offer updatedProductOffer = offerService.assignProductToOffer(offerId, productId);
        return new ResponseEntity<>(updatedProductOffer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
