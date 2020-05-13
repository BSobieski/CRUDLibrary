package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bsobieski.crudlibrary.entities.Book;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> getByTitle(String title);

    @Query("select b " +
            "from Book b " +
            "where b.title like %?1%")
    Iterable<Book> getBookTitlesByPattern(String pattern);

    Iterable<Book> getBooksByReleaseDateAfter(LocalDate releaseDate);

    Iterable<Book> getBooksByReleaseYearAfter(Integer releaseYear);


}
