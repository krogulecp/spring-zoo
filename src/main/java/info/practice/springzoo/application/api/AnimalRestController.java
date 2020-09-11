package info.practice.springzoo.application.api;

import info.practice.springzoo.application.AnimalService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Api
@RestController
@RequestMapping(path = "/zoo")
public class AnimalRestController {
    private final AnimalService animalService;

    public AnimalRestController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    ResponseEntity<AnimalListResponse> list() {
        return ResponseEntity.ok(AnimalListResponse.from(animalService.listAllAnimals()));
    }

    @PostMapping("/animals")
    ResponseEntity<Void> add(@Valid @RequestBody AnimalRequest animalRequest) {
        String animalId = animalService.addAnimal(animalRequest.toAnimalDto());
        return ResponseEntity.created(URI.create("/animals/" + animalId)).build();
    }
}
