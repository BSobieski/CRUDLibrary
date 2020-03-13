package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Author;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    public Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
