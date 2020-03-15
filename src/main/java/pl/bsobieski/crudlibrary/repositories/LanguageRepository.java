package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    Optional<Language> getByName(String languageName);

    void deleteByName(String authorName);

    @Query("select l " +
            "FROM Language l " +
            "where l.name like %?1%")
    List<Language> getLanguageNameByPattern(String pattern);
}
