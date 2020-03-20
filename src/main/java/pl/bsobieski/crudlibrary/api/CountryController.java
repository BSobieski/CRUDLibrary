package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Country;
import pl.bsobieski.crudlibrary.services.CountryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public Iterable<Country> getAll(){
        return countryService.getAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Country> getById(@PathVariable("id") Long id){
        return countryService.getById(id);
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country){
        return countryService.save(country);
    }

    @PutMapping
    public Country modifyCountry(@RequestBody Country country){
        return countryService.save(country);
    }

    @DeleteMapping
    public void deleteCountry(@RequestParam Long id){
        countryService.deleteById(id);
    }

    @GetMapping
    public Optional<Country> getByName(@RequestParam String name){
        return countryService.getByName(name);
    }

    @GetMapping("/search/{pattern}")
    public Iterable<Country> getCountryNamesByPattern(@PathVariable("pattern") String pattern){
        return countryService.getCountryNamesByPattern(pattern);
    }

}
