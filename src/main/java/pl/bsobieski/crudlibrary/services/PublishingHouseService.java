package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;
import pl.bsobieski.crudlibrary.repositories.PublishingHouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublishingHouseService {
    private final PublishingHouseRepository publishingHouseRepository;

    @Autowired
    public PublishingHouseService(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    public Iterable<PublishingHouse> getAll() {
        return publishingHouseRepository.findAll();
    }

    public Optional<PublishingHouse> getById(Long id) {
        return publishingHouseRepository.findById(id);
    }

    public PublishingHouse save(PublishingHouse publishingHouse) {
        return publishingHouseRepository.save(publishingHouse);
    }

    public Iterable<PublishingHouse> saveAll(Iterable<PublishingHouse> publishingHouseIterable){
        return publishingHouseRepository.saveAll(publishingHouseIterable);
    }

    public void deleteById(Long id) {
        publishingHouseRepository.deleteById(id);
    }

    public Optional<PublishingHouse> getByName(String name) {
        return publishingHouseRepository.getByName(name);
    }

    public Iterable<PublishingHouse> getPublishingHouseNamesByPattern(String pattern){
        return publishingHouseRepository.getPublishingHouseNamesByPattern(pattern);
    }

    public Iterable<PublishingHouse> getPublishingHousesByCity(String city){
        return publishingHouseRepository.getPublishingHousesByCity(city);
    }
}
