package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.Book;
import pl.bsobieski.crudlibrary.services.BookService;

import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookApi {
    private BookService bookService;

    @Autowired
    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public Iterable<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping
    public Optional<Book> getById(@RequestParam Long id){
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
}
