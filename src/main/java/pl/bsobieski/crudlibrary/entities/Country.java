package pl.bsobieski.crudlibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String countryName;

    @JsonIgnore
    @OneToMany(mappedBy = "countryOfOrigin")
    private List<Author> authorsFromThisCountry = new ArrayList<>();

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long countryId) {
        this.id = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }

    public List<Author> getAuthorsFromThisCountry() {
        return authorsFromThisCountry;
    }

    public void setAuthorsFromThisCountry(List<Author> authorsFromThisCountry) {
        this.authorsFromThisCountry = authorsFromThisCountry;
    }
}
