package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
