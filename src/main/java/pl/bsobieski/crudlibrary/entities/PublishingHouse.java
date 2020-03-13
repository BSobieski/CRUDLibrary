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
    private String publishingHouseName;

    @NotNull
    private String publishingHouseStreetAdress;

    @NotNull
    private String publishingHouseCity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country")
    private Country publishingHouseCountry;

    private String email;

    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "publishingHouse")
    private List<Book> booksOfThePublishingHouse = new ArrayList<>();

    public PublishingHouse() {
    }

    public PublishingHouse(String publishingHouseName, String publishingHouseStreetAdress, String publishingHouseCity, Country publishingHouseCountry, String email, String phone) {
        this.publishingHouseName = publishingHouseName;
        this.publishingHouseStreetAdress = publishingHouseStreetAdress;
        this.publishingHouseCity = publishingHouseCity;
        this.publishingHouseCountry = publishingHouseCountry;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublishingHouseName() {
        return publishingHouseName;
    }

    public void setPublishingHouseName(String publishingHouseName) {
        this.publishingHouseName = publishingHouseName;
    }

    public String getPublishingHouseStreetAdress() {
        return publishingHouseStreetAdress;
    }

    public void setPublishingHouseStreetAdress(String streetAdress) {
        this.publishingHouseStreetAdress = streetAdress;
    }

    public String getPublishingHouseCity() {
        return publishingHouseCity;
    }

    public void setPublishingHouseCity(String city) {
        this.publishingHouseCity = city;
    }

    public Country getPublishingHouseCountry() {
        return publishingHouseCountry;
    }

    public void setPublishingHouseCountry(Country country) {
        this.publishingHouseCountry = country;
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
}
