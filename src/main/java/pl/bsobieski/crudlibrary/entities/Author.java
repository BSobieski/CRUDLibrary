package pl.bsobieski.crudlibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private LocalDate dateOfBirth;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_of_origin")
    private Country countryOfOrigin;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Book> listOfAuthorBooks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "translationAuthor", orphanRemoval = true)
    private List<Book> listOfTranslatedBooks = new ArrayList<>();

    public Author() {
    }

    public Author(String name, LocalDate dateOfBirth, Country countryOfOrigin, String description) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.countryOfOrigin = countryOfOrigin;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Country getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(Country countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public List<Book> getListOfAuthorBooks() {
        return listOfAuthorBooks;
    }

    public void setListOfAuthorBooks(List<Book> listOfAuthorBooks) {
        this.listOfAuthorBooks = listOfAuthorBooks;
    }

    public List<Book> getListOfTranslatedBooks() {
        return listOfTranslatedBooks;
    }

    public void setListOfTranslatedBooks(List<Book> listOfTranslatedBooks) {
        this.listOfTranslatedBooks = listOfTranslatedBooks;
    }
}
