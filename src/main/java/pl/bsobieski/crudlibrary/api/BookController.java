package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Book;
import pl.bsobieski.crudlibrary.services.BookService;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/all")
    public Iterable<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/admin/book/id/{id}")
    public Optional<Book> getById(@PathVariable("id") Long id){
        return bookService.getById(id);
    }

    @PostMapping("/admin/book")
    public Book addBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping("/admin/book")
    public Book modifyBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/admin/book")
    public void deleteBook(@RequestParam Long id){
        bookService.deleteById(id);
    }

    @GetMapping("/book/search/{pattern}")
    public Iterable<Book> getBookTitlesByPattern(@PathVariable("pattern") String pattern){
        return bookService.getBookTitlesByPattern(pattern);
    }

    @GetMapping("/admin/book")
    public Optional<Book> getByTitle(String title){
        return bookService.getByTitle(title);
    }

    @GetMapping("/book/releaseDate/{releaseDate}")
    public Iterable<Book> getBooksByReleaseDateAfter(@PathVariable("releaseDate") String releaseDate){
        return bookService.getBooksByReleaseDateAfter(LocalDate.parse(releaseDate));
    }

    @GetMapping("/book/releaseYear/{releaseYear}")
    public Iterable<Book> getBooksByReleaseYearAfter(@PathVariable("releaseYear") Integer releaseYear){
        return bookService.getBooksByReleaseYearAfter(releaseYear);
    }
}
