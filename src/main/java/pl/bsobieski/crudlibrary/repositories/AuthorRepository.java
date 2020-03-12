package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
