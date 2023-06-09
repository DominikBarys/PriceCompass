package pl.dominikbarys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominikbarys.dto.address.AddressDTO;
import pl.dominikbarys.dto.address.CreateAddressDTO;
import pl.dominikbarys.entity.Address;
import pl.dominikbarys.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressDTO>> findAllAddresses(){
        List<AddressDTO> addresses = addressService.findAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AddressDTO> findAddressById(@PathVariable("id") Integer id){
        AddressDTO address = addressService.findAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody CreateAddressDTO address){
        AddressDTO newAddress = addressService.addAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }





}
