package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.Country;
import pl.bsobieski.crudlibrary.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Iterable<Country> getAll() {
        return countryRepository.findAll();
    }

    public Optional<Country> getById(Long id) {
        return countryRepository.findById(id);
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    public Optional<Country> getByName(String name) {
        return countryRepository.getByName(name);
    }

    public Iterable<Country> getCountryNamesByPattern(String pattern){
        return countryRepository.getCountryNamesByPattern(pattern);
    }

    public Iterable<Country> saveAll(Iterable<Country> countryIterable){
        return countryRepository.saveAll(countryIterable);
    }
}
