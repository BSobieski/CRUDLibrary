package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Author;
import pl.bsobieski.crudlibrary.services.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/all")
    public Iterable<Author> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/admin/author/id/{id}")
    public Optional<Author> getById(@PathVariable("id") Long id) {
        return authorService.getById(id);
    }

    @PostMapping("/admin/author")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/admin/author")
    public Author modifyAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @DeleteMapping("/admin/author")
    public void deleteAuthor(@RequestParam Long id){
        authorService.deleteById(id);
    }

    @GetMapping("/admin/author")
    public Optional<Author> getByName(@RequestParam String name){
        return authorService.getByName(name);
    }

    @GetMapping("/author/search/{pattern}")
    public Iterable<Author> getAuthorNamesByPattern(@PathVariable("pattern") String pattern){
        return authorService.getAuthorNamesByPattern(pattern);
    }

}

