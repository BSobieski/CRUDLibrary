package pl.bsobieski.crudlibrary.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Year;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @ManyToOne
    private Language languageOfPublication;

    @ManyToOne
    private Language languageOfTranslation;

    private int numberOfPages;

    private int issueNumber;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private Year releaseYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Book() {
    }

    public Book(String title, Language languageOfPublication, Language languageOfTranslation, int numberOfPages, int issueNumber, LocalDate releaseDate, Year releaseYear, String description) {
        this.title = title;
        this.languageOfPublication = languageOfPublication;
        this.languageOfTranslation = languageOfTranslation;
        this.numberOfPages = numberOfPages;
        this.issueNumber = issueNumber;
        this.releaseDate = releaseDate;
        this.releaseYear = releaseYear;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguageOfPublication() {
        return languageOfPublication;
    }

    public void setLanguageOfPublication(Language languageOfPublication) {
        this.languageOfPublication = languageOfPublication;
    }

    public Language getLanguageOfTranslation() {
        return languageOfTranslation;
    }

    public void setLanguageOfTranslation(Language languageOfTranslation) {
        this.languageOfTranslation = languageOfTranslation;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
