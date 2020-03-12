package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
