package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> getByName(String name);

    @Query("select a " +
            "from Author a " +
            "where a.name like %?1%")
    List<Author> getAuthorNamesByPattern(String pattern);
}
