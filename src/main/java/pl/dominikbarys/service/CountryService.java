package pl.dominikbarys.service;

import org.springframework.stereotype.Service;
import pl.dominikbarys.dto.country.CountryDTO;
import pl.dominikbarys.dto.country.CreateCountryDTO;
import pl.dominikbarys.entity.Country;
import pl.dominikbarys.exception.NotFoundException;
import pl.dominikbarys.exception.NotUniqueException;
import pl.dominikbarys.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;


    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryDTO> findAllCountries(){
        return countryRepository.findAll().stream()
                .map(this::convertCountryToCountryDTO)
                .collect(Collectors.toList());
    }

    public CountryDTO findCountryById(Integer id){
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country by id " + id + " was not found"));

        return convertCountryToCountryDTO(country);
    }

    public CountryDTO addCountry(CreateCountryDTO country){
        if(!countryRepository.existsByName(country.getName())){
            Country newCountry = new Country();
            newCountry.setName(country.getName());

            countryRepository.save(newCountry);

            return convertCountryToCountryDTO(newCountry);
        }else{
            throw new NotUniqueException("Country with name " + country.getName() + " already exists");
        }
    }

    public CountryDTO convertCountryToCountryDTO(Country country){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());

        return countryDTO;
    }

}
