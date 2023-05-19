package pl.dominikbarys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dominikbarys.dto.country.CountryDTO;
import pl.dominikbarys.dto.country.CreateCountryDTO;
import pl.dominikbarys.entity.Country;
import pl.dominikbarys.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CountryDTO>> findAllCountries(){
        List<CountryDTO> countries = countryService.findAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CountryDTO> findCountryById(@PathVariable("id") Integer id){
        CountryDTO country = countryService.findCountryById(id);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDTO> addCountry(@RequestBody CreateCountryDTO country){
        CountryDTO newCountry = countryService.addCountry(country);
        return new ResponseEntity<>(newCountry, HttpStatus.CREATED);
    }


}
