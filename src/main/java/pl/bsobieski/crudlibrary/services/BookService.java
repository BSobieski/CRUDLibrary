package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.Book;
import pl.bsobieski.crudlibrary.repositories.BookRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> saveAll(Iterable<Book> bookIterable){
        return bookRepository.saveAll(bookIterable);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    public Iterable<Book> getBookTitlesByPattern(String pattern) {
        return bookRepository.getBookTitlesByPattern(pattern);
    }

    public Iterable<Book> getBooksByReleaseDateAfter(LocalDate releaseDate) {
        return bookRepository.getBooksByReleaseDateAfter(releaseDate);
    }

    public Iterable<Book> getBooksByReleaseYearAfter(Integer releaseYear){
        return bookRepository.getBooksByReleaseYearAfter(releaseYear);
    }
}
