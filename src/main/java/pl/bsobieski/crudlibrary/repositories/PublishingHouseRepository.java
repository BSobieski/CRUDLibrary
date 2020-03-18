package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;

import java.util.List;
import java.util.Optional;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long> {
    Optional<PublishingHouse> getByName(String name);

    @Query("select ph " +
            "from PublishingHouse ph " +
            "where ph.name like %?1%")
    List<PublishingHouse> getPublishingHouseNamesByPattern(String pattern);

    List<PublishingHouse>getPublishingHousesByCity(String city);
}
