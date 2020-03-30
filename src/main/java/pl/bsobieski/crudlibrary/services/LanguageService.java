package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.Language;
import pl.bsobieski.crudlibrary.repositories.LanguageRepository;

import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Iterable<Language> getAll(){
        return languageRepository.findAll();
    }

    public Optional<Language> getByName(String name){
        return languageRepository.getByName(name);
    }

    public Optional<Language> getById(Long id){
        return languageRepository.findById(id);
    }

    public Language save(Language language){
        return languageRepository.save(language);
    }

    public void deleteById(Long id){
        languageRepository.deleteById(id);
    }

    public Iterable<Language> getNameByPattern(String pattern){
        return languageRepository.getLanguageNameByPattern(pattern);
    }

    public Iterable<Language> saveAll(Iterable<Language> languageIterable){
        return languageRepository.saveAll(languageIterable);
    }
}
