package pl.bsobieski.crudlibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "countryOfOrigin")
    private List<Author> authorsFromThisCountry = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<PublishingHouse> publishingHousesFromCountry = new ArrayList<>();

    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long countryId) {
        this.id = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthorsFromThisCountry() {
        return authorsFromThisCountry;
    }

    public void setAuthorsFromThisCountry(List<Author> authorsFromThisCountry) {
        this.authorsFromThisCountry = authorsFromThisCountry;
    }

    public List<PublishingHouse> getPublishingHousesFromCountry() {
        return publishingHousesFromCountry;
    }

    public void setPublishingHousesFromCountry(List<PublishingHouse> publishingHousesFromCountry) {
        this.publishingHousesFromCountry = publishingHousesFromCountry;
    }
}
