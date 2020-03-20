package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.User;

public interface UserRepository  extends CrudRepository<User, Long> {
}
