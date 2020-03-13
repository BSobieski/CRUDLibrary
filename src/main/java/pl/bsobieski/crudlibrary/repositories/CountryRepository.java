package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Country;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {
    public Optional<Country> findByCountryName(String countryName);
}
