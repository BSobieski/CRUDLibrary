package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.Author;
import pl.bsobieski.crudlibrary.repositories.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Iterable<Author> getAll(){
        return authorRepository.findAll();
    }

    public Optional<Author> getById(Long id){
        return authorRepository.findById(id);
    }

    public Author save(Author country){
        return authorRepository.save(country);
    }

    public void deleteById(Long id){
        authorRepository.deleteById(id);
    }


}
