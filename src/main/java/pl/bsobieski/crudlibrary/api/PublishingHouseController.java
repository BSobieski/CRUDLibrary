package pl.bsobieski.crudlibrary.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bsobieski.crudlibrary.entities.PublishingHouse;
import pl.bsobieski.crudlibrary.services.PublishingHouseService;

import java.util.Optional;

@RestController
@RequestMapping("/api/publishinghouse")
public class PublishingHouseController {
    private PublishingHouseService publishingHouseService;

    @Autowired
    public PublishingHouseController(PublishingHouseService publishingHouseService) {
        this.publishingHouseService = publishingHouseService;
    }

    @GetMapping("/all")
    public Iterable<PublishingHouse> getAll(){
        return publishingHouseService.getAll();
    }

    @GetMapping
    public Optional<PublishingHouse> getById(@RequestParam Long id){
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
}
