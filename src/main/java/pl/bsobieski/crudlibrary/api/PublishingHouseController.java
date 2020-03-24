package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;
import pl.bsobieski.crudlibrary.services.PublishingHouseService;

import java.util.Optional;

@RestController
public class PublishingHouseController {
    private final PublishingHouseService publishingHouseService;

    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping("/publishinghouse/all")
    public Iterable<PublishingHouse> getAll(){
        return publishingHouseService.getAll();
    }

    @GetMapping("/admin/publishinghouse/id/{id}")
    public Optional<PublishingHouse> getById(@PathVariable("id") Long id){
        return publishingHouseService.getById(id);
    }

    @PostMapping("/admin/publishinghouse")
    public PublishingHouse addPublishingHouse(@RequestBody PublishingHouse publishingHouse){
        return publishingHouseService.save(publishingHouse);
    }

    @PutMapping("/admin/publishinghouse")
    public PublishingHouse modifyPublishingHouse(@RequestBody PublishingHouse publishingHouse){
        return publishingHouseService.save(publishingHouse);
    }

    @DeleteMapping("/admin/publishinghouse")
    public void deletePublishingHouse(@RequestParam Long id){
        publishingHouseService.deleteById(id);
    }

    @GetMapping("/admin/publishinghouse")
    public Optional<PublishingHouse> getByName(@RequestParam String name){
        return publishingHouseService.getByName(name);
    }

    @GetMapping("/publishinghouse/search/{pattern}")
    public Iterable<PublishingHouse> getPublishingHouseNamesByPattern(@PathVariable("pattern") String pattern){
        return publishingHouseService.getPublishingHouseNamesByPattern(pattern);
    }

    @GetMapping("/publishinghouse/city/{city}")
    public Iterable<PublishingHouse> getPublishingHousesByCity(@PathVariable("city") String city){
        return publishingHouseService.getPublishingHousesByCity(city);
    }
}
