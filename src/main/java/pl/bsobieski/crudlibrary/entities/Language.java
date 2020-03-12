package pl.bsobieski.crudlibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String languageName;

    @JsonIgnore
    @OneToMany(mappedBy = "languageOfPublication")
    private List<Book> publicationLanguageBooks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "languageOfTranslation")
    private List<Book> translatedLanguageBooks = new ArrayList<>();

    public Language() {
    }

    public Language(String languageName) {
        this.languageName = languageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public List<Book> getPublicationLanguageBooks() {
        return publicationLanguageBooks;
    }

    public void setPublicationLanguageBooks(List<Book> publicationLanguageBooks) {
        this.publicationLanguageBooks = publicationLanguageBooks;
    }

    public List<Book> getTranslatedLanguageBooks() {
        return translatedLanguageBooks;
    }

    public void setTranslatedLanguageBooks(List<Book> translatedLanguageBooks) {
        this.translatedLanguageBooks = translatedLanguageBooks;
    }
}
