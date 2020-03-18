package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Optional<Country> getByName(String countryName);

    @Query("select c " +
            "from Country c " +
            "where c.name like %?1%")
    List<Country> getCountryNamesByPattern(String pattern);
}
