package pl.dominikbarys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominikbarys.dto.salespoint.CreateSalesPointDTO;
import pl.dominikbarys.dto.salespoint.SalesPointDTO;
import pl.dominikbarys.entity.Offer;
import pl.dominikbarys.entity.SalesPoint;
import pl.dominikbarys.service.SalesPointService;

import java.util.List;

@RestController
@RequestMapping("/salesPoints")
public class SalesPointController {

    private final SalesPointService salesPointService;

    public SalesPointController(SalesPointService salesPointService) {
        this.salesPointService = salesPointService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalesPointDTO>> getAllSalesPoints(){
        List<SalesPointDTO> salesPoints = salesPointService.findAllSalesPoints();
        return new ResponseEntity<>(salesPoints, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<SalesPointDTO> getSalesPointById(@PathVariable("id") Integer id){
        SalesPointDTO salesPoint = salesPointService.findSalesPointById(id);
        return new ResponseEntity<>(salesPoint, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SalesPointDTO> addSalesPoint(@RequestBody CreateSalesPointDTO salesPoint){
        SalesPointDTO newSalesPoint = salesPointService.addSalesPoint(salesPoint);
        return new ResponseEntity<>(newSalesPoint, HttpStatus.CREATED);
    }

    @PatchMapping("/addOffers/{id}")
    public ResponseEntity<SalesPointDTO> addOffers(@RequestBody List<Integer> offers, @PathVariable("id") Integer id){
        SalesPointDTO salesPoint = salesPointService.addOffers(offers, id);
        return new ResponseEntity<>(salesPoint, HttpStatus.OK);
    }

    @PatchMapping("/removeOffers/{id}")
    public ResponseEntity<SalesPointDTO> removeOffers(@RequestBody List<Integer> offers, @PathVariable("id") Integer id){
        SalesPointDTO salesPoint = salesPointService.removeOffers(offers, id);
        return new ResponseEntity<>(salesPoint, HttpStatus.OK);
    }

    @PatchMapping("/{salesPointId}/address/{addressId}")
    public ResponseEntity<SalesPointDTO> assignAddressToSalesPoint(
            @PathVariable("salesPointId") Integer salesPointId,
            @PathVariable("addressId") Integer addressId){

        SalesPointDTO salesPointDTO = salesPointService.assignAddressToSalesPoint(salesPointId, addressId);
        return new ResponseEntity<>(salesPointDTO, HttpStatus.OK);
    }

}
