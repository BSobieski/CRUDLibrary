package pl.bsobieski.crudlibrary.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.bsobieski.crudlibrary.entities.*;
import pl.bsobieski.crudlibrary.services.*;
import pl.bsobieski.crudlibrary.utils.Roles;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

@Component
public class DataBaseInit implements CommandLineRunner {
    private AuthorService authorService;
    private BookService bookService;
    private CountryService countryService;
    private LanguageService languageService;
    private PublishingHouseService publishingHouseService;
    private UserService userService;

    @Autowired
    public DataBaseInit(AuthorService authorService, BookService bookService, CountryService countryService,
                        LanguageService languageService, PublishingHouseService publishingHouseService, UserService userService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
        this.languageService = languageService;
        this.publishingHouseService = publishingHouseService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        loadCountries();
        loadLanguages();
        loadPublishingHouses();
        loadAuthors();
        loadBooks();
        loadUsers();
    }

    private void loadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Sobiech250", Base64.getEncoder().encodeToString("123".getBytes()),"123", "Bartosz",
                "Sobieski","123456789","123@123.com",Roles.ADMIN.name(),  LocalDate.parse("1998-10-08"),false));
        users.add(new User("SimpleUserAccount", Base64.getEncoder().encodeToString("useruser".getBytes()),"useruser","user",
                "user","user","user", Roles.USER.name(), LocalDate.parse("1998-10-08"),false));
        users.add(new User("LockedUser", Base64.getEncoder().encodeToString("locked".getBytes()),"locked", "locked",
                "locked","locked","locked",Roles.USER.name(), LocalDate.parse("1990-12-01"),true));
        userService.saveAll(users);
    }

    private void loadBooks() {
        Resource resource = new ClassPathResource("dataresources/books.dat");
        try {
            List<String> allBooksFromFile = Files.readAllLines(resource.getFile().toPath());
            List<Book> allBooksToSave = new ArrayList<>();
            for (String line : allBooksFromFile) {
                List<String> booksParameters = Arrays.asList(line.split(","));
                Optional<Author> bookAuthor = authorService.getByName(booksParameters.get(1));
                Optional<Author> translationAuthor = authorService.getByName(booksParameters.get(2));
                Optional<PublishingHouse> publishingHouse = publishingHouseService.getByName(booksParameters.get(3));
                Optional<Language> languageOfPublication = languageService.getByName(booksParameters.get(4));
                Optional<Language> languageOfTranslation = languageService.getByName(booksParameters.get(5));
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
            bookService.saveAll(allBooksToSave);
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
                Optional<Country> countryOfOrigin = countryService.getByName(authorPaameters.get(2));
                countryOfOrigin.ifPresent(country ->
                        allAuthorsToSave.add(new Author(
                                authorPaameters.get(0),
                                LocalDate.parse(authorPaameters.get(1)),
                                country,
                                authorPaameters.get(3)
                        ))
                );
            }
            authorService.saveAll(allAuthorsToSave);
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
                Optional<Country> publishingHouseCountry = countryService.getByName(PHParameters.get(4));
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
            publishingHouseService.saveAll(allPHToSave);
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
            languageService.saveAll(allLanguagesToSave);
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
            countryService.saveAll(allCountriesToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
