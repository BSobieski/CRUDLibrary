package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Language;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    public Optional<Language> findByLanguageName(String languageName);
}
