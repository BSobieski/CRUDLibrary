package pl.bsobieski.crudlibrary.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.bsobieski.crudlibrary.entities.*;
import pl.bsobieski.crudlibrary.repositories.*;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataBaseInit implements CommandLineRunner {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private CountryRepository countryRepository;
    private LanguageRepository languageRepository;
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    public DataBaseInit(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository, LanguageRepository languageRepository, PublishingHouseRepository publishingHouseRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.languageRepository = languageRepository;
        this.publishingHouseRepository = publishingHouseRepository;
    }

    @Override
    public void run(String... args) {
        loadCountries();
        loadLanguages();
        loadPublishingHouses();
        loadAuthors();
        loadBooks();
    }

    private void loadBooks() {
        Resource resource = new ClassPathResource("dataresources/books.dat");
        try {
            List<String> allBooksFromFile = Files.readAllLines(resource.getFile().toPath());
            List<Book> allBooksToSave = new ArrayList<>();
            for (String line : allBooksFromFile) {
                List<String> booksParameters = Arrays.asList(line.split(","));
                Optional<Author> bookAuthor = authorRepository.findByAuthorName(booksParameters.get(1));
                Optional<Author> translationAuthor = authorRepository.findByAuthorName(booksParameters.get(2));
                Optional<PublishingHouse> publishingHouse = publishingHouseRepository.findByPublishingHouseName(booksParameters.get(3));
                Optional<Language> languageOfPublication = languageRepository.findByLanguageName(booksParameters.get(4));
                Optional<Language> languageOfTranslation = languageRepository.findByLanguageName(booksParameters.get(5));
                allBooksToSave.add(new Book(
                        booksParameters.get(0),
                        bookAuthor.get(),
                        translationAuthor.get(),
                        publishingHouse.get(),
                        languageOfPublication.get(),
                        languageOfTranslation.get(),
                        Integer.parseInt(booksParameters.get(6)),
                        Integer.parseInt(booksParameters.get(7)),
                        LocalDate.parse(booksParameters.get(8)),
                        Integer.parseInt(booksParameters.get(9)),
                        booksParameters.get(10)

                ));
            }
            bookRepository.saveAll(allBooksToSave);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadAuthors() {
        Resource resource = new ClassPathResource("dataresources/authors.dat");
        try {
            List<String> allAuthorsFromFile = Files.readAllLines(resource.getFile().toPath());
            List<Author> allAuthorsToSave = new ArrayList<>();
            for (String line : allAuthorsFromFile) {
                List<String> authorPaameters = Arrays.asList(line.split(","));
                Optional<Country> countryOfOrigin = countryRepository.findByCountryName(authorPaameters.get(2));
                countryOfOrigin.ifPresent(country ->
                        allAuthorsToSave.add(new Author(
                                authorPaameters.get(0),
                                LocalDate.parse(authorPaameters.get(1)),
                                country,
                                authorPaameters.get(3)
                        ))
                );
            }
            authorRepository.saveAll(allAuthorsToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPublishingHouses() {
        Resource resource = new ClassPathResource("dataresources/publishingHouses.dat");
        try {
            List<String> allPHFromFile = Files.readAllLines(resource.getFile().toPath());
            List<PublishingHouse> allPHToSave = new ArrayList<>();
            for (String line : allPHFromFile) {
                List<String> PHParameters = Arrays.asList(line.split(","));
                Optional<Country> publishingHouseCountry = countryRepository.findByCountryName(PHParameters.get(4));
                publishingHouseCountry.ifPresent(country -> allPHToSave.add(
                        new PublishingHouse(
                                PHParameters.get(0),
                                PHParameters.get(1),
                                PHParameters.get(2),
                                PHParameters.get(3),
                                country,
                                PHParameters.get(5),
                                PHParameters.get(6)
                        ))
                );
            }
            publishingHouseRepository.saveAll(allPHToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLanguages() {
        Resource resource = new ClassPathResource("dataresources/languages.dat");
        try {
            List<String> allLanguagesFromFile = Files.readAllLines(resource.getFile().toPath());
            List<Language> allLanguagesToSave = new ArrayList<>();
            allLanguagesFromFile.forEach(line -> allLanguagesToSave.add(new Language(line)));
            languageRepository.saveAll(allLanguagesToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCountries() {
        Resource resource = new ClassPathResource("dataresources/countries.dat");
        try {
            List<String> allCountriesFromFile = Files.readAllLines(resource.getFile().toPath());
            List<Country> allCountriesToSave = new ArrayList<>();
            allCountriesFromFile.forEach(line -> allCountriesToSave.add(new Country(line)));
            countryRepository.saveAll(allCountriesToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
