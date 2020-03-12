package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long> {
}
