package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Book;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> getByTitle(String title);

    @Query("select b " +
            "from Book b " +
            "where b.title like %?1%")
    List<Book> getBookTitlesByPattern(String pattern);

    List<Book> getBooksByReleaseDateAfter(LocalDate releaseDate);

    List<Book> getBooksByReleaseYearAfter(Integer releaseYear);


}
