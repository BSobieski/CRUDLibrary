package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bsobieski.crudlibrary.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
