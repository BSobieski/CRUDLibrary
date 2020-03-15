package pl.bsobieski.crudlibrary.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_author")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "translation_author")
    private Author translationAuthor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publishing_house")
    private PublishingHouse publishingHouse;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "language_of_publication")
    private Language languageOfPublication;

    @ManyToOne
    @JoinColumn(name = "language_of_translation")
    private Language languageOfTranslation;

    private int numberOfPages;

    private int issueNumber;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    @Column(columnDefinition = "SMALLINT")
    private int releaseYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Book() {
    }

    public Book(String title, Author author, Author translationAuthor, @NotNull PublishingHouse publishingHouse, Language languageOfPublication, Language languageOfTranslation,
                int numberOfPages, int issueNumber, LocalDate releaseDate, int releaseYear, String description) {
        this.title = title;
        this.author = author;
        this.translationAuthor = translationAuthor;
        this.publishingHouse = publishingHouse;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getTranslationAuthor() {
        return translationAuthor;
    }

    public void setTranslationAuthor(Author translationAuthor) {
        this.translationAuthor = translationAuthor;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
