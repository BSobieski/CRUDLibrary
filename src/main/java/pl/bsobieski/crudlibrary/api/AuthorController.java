package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Author;
import pl.bsobieski.crudlibrary.services.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public Iterable<Author> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Author> getById(@PathVariable("id") Long id) {
        return authorService.getById(id);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping
    public Author modifyAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @DeleteMapping
    public void deleteAuthor(@RequestParam Long id){
        authorService.deleteById(id);
    }

    @GetMapping
    public Optional<Author> getByName(@RequestParam String name){
        return authorService.getByName(name);
    }

    @GetMapping("/{pattern}")
    public List<Author> getAuthorNamesByPattern(@PathVariable("pattern") String pattern){
        return authorService.getAuthorNamesByPattern(pattern);
    }

}

