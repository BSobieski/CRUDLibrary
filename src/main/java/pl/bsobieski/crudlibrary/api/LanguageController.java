package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Book;
import pl.bsobieski.crudlibrary.entities.Language;
import pl.bsobieski.crudlibrary.services.LanguageService;

import java.util.Optional;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/all")
    public Iterable<Language> getAll(){
        return languageService.getAll();
    }

    @GetMapping
    public Optional<Language> getById(@RequestParam Long id){
        return languageService.getById(id);
    }

    @PostMapping
    public Language addLanguage(@RequestBody Language language){
        return languageService.save(language);
    }

    @PutMapping
    public Language modifyLanguage(@RequestBody Language language){
        return languageService.save(language);
    }

    @DeleteMapping
    public void deleteLanguage(@RequestParam Long id){
        languageService.deleteById(id);
    }
}
