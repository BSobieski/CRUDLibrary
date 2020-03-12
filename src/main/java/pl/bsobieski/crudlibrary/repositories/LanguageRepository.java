package pl.bsobieski.crudlibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.bsobieski.crudlibrary.entities.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
}
