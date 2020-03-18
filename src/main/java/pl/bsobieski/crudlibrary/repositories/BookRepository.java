package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> getByTitle(String title);
}
