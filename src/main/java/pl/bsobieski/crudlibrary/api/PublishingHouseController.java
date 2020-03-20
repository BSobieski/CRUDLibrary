package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;
import pl.bsobieski.crudlibrary.services.PublishingHouseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publishinghouse")
public class PublishingHouseController {
    private final PublishingHouseService publishingHouseService;

    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping("/all")
    public Iterable<PublishingHouse> getAll(){
        return publishingHouseService.getAll();
    }

    @GetMapping("/id/{id}")
    public Optional<PublishingHouse> getById(@PathVariable("id") Long id){
        return publishingHouseService.getById(id);
    }

    @PostMapping
    public PublishingHouse addPublishingHouse(@RequestBody PublishingHouse publishingHouse){
        return publishingHouseService.save(publishingHouse);
    }

    @PutMapping
    public PublishingHouse modifyPublishingHouse(@RequestBody PublishingHouse publishingHouse){
        return publishingHouseService.save(publishingHouse);
    }

    @DeleteMapping
    public void deletePublishingHouse(@RequestParam Long id){
        publishingHouseService.deleteById(id);
    }

    @GetMapping
    public Optional<PublishingHouse> getByName(@RequestParam String name){
        return publishingHouseService.getByName(name);
    }

    @GetMapping("/search/{pattern}")
    public Iterable<PublishingHouse> getPublishingHouseNamesByPattern(@PathVariable("pattern") String pattern){
        return publishingHouseService.getPublishingHouseNamesByPattern(pattern);
    }

    @GetMapping("/city/{city}")
    public Iterable<PublishingHouse> getPublishingHousesByCity(@PathVariable("city") String city){
        return publishingHouseService.getPublishingHousesByCity(city);
    }
}
