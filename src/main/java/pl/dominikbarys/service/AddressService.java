package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.entity.Address;
import pl.dominikbarys.exception.NotFoundException;
import pl.dominikbarys.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAllAddresses(){
        return addressRepository.findAll();
    }

    public Address findAddressById(Integer id){
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address by id " + id + " was not found"));
    }

    public Address addAddress(Address address){
        return addressRepository.save(address);
    }

}
