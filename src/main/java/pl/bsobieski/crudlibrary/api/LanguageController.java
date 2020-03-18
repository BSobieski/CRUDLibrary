package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Language;
import pl.bsobieski.crudlibrary.services.LanguageService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
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
    public Optional<Language> getByName(@RequestParam String name){
        return languageService.getByName(name);
    }

    @GetMapping("/id")
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

    @GetMapping("/{pattern}")
    public List<Language> getNameByPattern(@PathVariable("pattern") String pattern){
        return languageService.getNameByPattern(pattern);
    }
}
