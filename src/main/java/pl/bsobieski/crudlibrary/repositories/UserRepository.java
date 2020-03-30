package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.User;

import java.util.Optional;

public interface UserRepository  extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
