package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;

import java.util.Optional;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long> {
    Optional<PublishingHouse> getByName(String name);
}
