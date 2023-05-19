package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.dto.address.AddressDTO;
import pl.dominikbarys.dto.address.CreateAddressDTO;
import pl.dominikbarys.entity.Address;
import pl.dominikbarys.entity.Country;
import pl.dominikbarys.exception.NotFoundException;
import pl.dominikbarys.repository.AddressRepository;
import pl.dominikbarys.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final CountryRepository countryRepository;

    public AddressService(AddressRepository addressRepository, CountryRepository countryRepository) {
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
    }

    public List<AddressDTO> findAllAddresses(){
        return addressRepository.findAll().stream()
                .map(this::convertAddressToAddressDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO findAddressById(Integer id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address by id " + id + " was not found"));

        return convertAddressToAddressDTO(address);
    }

    public AddressDTO addAddress(CreateAddressDTO address){
        Address newAddress = new Address();
        newAddress.setCity(address.getCity());
        newAddress.setStreet(address.getStreet());
        newAddress.setPostalCode(address.getPostalCode());
        newAddress.setNumber(address.getNumber());

        if(address.getCountryId() != 0) {
            Country country = countryRepository.findById(address.getCountryId())
                    .orElseThrow(() -> new NotFoundException(
                            "Country with id " + address.getCountryId() + " was not found"));
        }

        addressRepository.save(newAddress);

        return convertAddressToAddressDTO(newAddress);
    }

    public AddressDTO convertAddressToAddressDTO(Address address){
        //TODO transform DTOs to show not only id but for example name of record in another table
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setNumber(address.getNumber());
        if(address.getCountry() != null)
            addressDTO.setCountryId(address.getCountry().getId());

        return addressDTO;
    }

}
