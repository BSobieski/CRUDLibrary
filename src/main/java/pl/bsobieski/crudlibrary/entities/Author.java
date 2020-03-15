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
    private String authorName;

    private LocalDate dateOfBirth;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_of_origin")
    private Country countryOfOrigin;

    @Column(columnDefinition = "TEXT")
    private String authorDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "bookAuthor")
    private List<Book> listOfAuthorBooks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "translationAuthor")
    private List<Book> listOfTranslatedBooks = new ArrayList<>();

    public Author() {
    }

    public Author(String authorName, LocalDate dateOfBirth, Country countryOfOrigin, String authorDescription) {
        this.authorName = authorName;
        this.dateOfBirth = dateOfBirth;
        this.countryOfOrigin = countryOfOrigin;
        this.authorDescription = authorDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String firstName) {
        this.authorName = firstName;
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

    public String getAuthorDescription() {
        return authorDescription;
    }

    public void setAuthorDescription(String text) {
        this.authorDescription = text;
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
