package pl.dominikbarys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominikbarys.entity.Offer;
import pl.dominikbarys.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/offer")
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
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer){
        Offer newOffer = offerService.addOffer(offer);
        return new ResponseEntity<>(newOffer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer){
        Offer updatedOffer = offerService.updateOffer(offer);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
