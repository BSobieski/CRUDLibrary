package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Book;
import pl.bsobieski.crudlibrary.services.BookService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public Iterable<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Book> getById(@PathVariable("id") Long id){
        return bookService.getById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping
    public Book modifyBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam Long id){
        bookService.deleteById(id);
    }

    @GetMapping("/search/{pattern}")
    public Iterable<Book> getBookTitlesByPattern(@PathVariable("pattern") String pattern){
        return bookService.getBookTitlesByPattern(pattern);
    }

    @GetMapping
    public Optional<Book> getByTitle(String title){
        return bookService.getByTitle(title);
    }

    @GetMapping("/releaseDate/{releaseDate}")
    public Iterable<Book> getBooksByReleaseDateAfter(@PathVariable("releaseDate") String releaseDate){
        return bookService.getBooksByReleaseDateAfter(LocalDate.parse(releaseDate));
    }

    @GetMapping("/releaseYear/{releaseYear}")
    public Iterable<Book> getBooksByReleaseYearAfter(@PathVariable("releaseYear") Integer releaseYear){
        return bookService.getBooksByReleaseYearAfter(releaseYear);
    }
}
