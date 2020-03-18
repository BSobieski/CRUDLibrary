package pl.bsobieski.crudlibrary.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String streetAdress;

    @NotNull
    private String postCode;

    @NotNull
    private String city;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    private String email;

    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "publishingHouse", orphanRemoval = true)
    private List<Book> booksOfThePublishingHouse = new ArrayList<>();

    public PublishingHouse() {
    }

    public PublishingHouse(String name, String streetAdress, @NotNull String postCode, String city, Country country, String phone, String email) {
        this.name = name;
        this.streetAdress = streetAdress;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phone = phone;
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

    public void setName(String publishingHouseName) {
        this.name = publishingHouseName;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String publishingHousePostCode) {
        this.postCode = publishingHousePostCode;
    }

    public List<Book> getBooksOfThePublishingHouse() {
        return booksOfThePublishingHouse;
    }

    public void setBooksOfThePublishingHouse(List<Book> booksOfThePublishingHouse) {
        this.booksOfThePublishingHouse = booksOfThePublishingHouse;
    }
}
