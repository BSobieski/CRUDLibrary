package pl.bsobieski.crudlibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;
import pl.bsobieski.crudlibrary.repositories.PublishingHouseRepository;

import java.util.Optional;

@Service
public class PublishingHouseService {
    private PublishingHouseRepository publishingHouseRepository;

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

    public void deleteById(Long id) {
        publishingHouseRepository.deleteById(id);
    }
}
