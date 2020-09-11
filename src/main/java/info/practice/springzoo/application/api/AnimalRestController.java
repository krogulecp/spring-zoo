package info.practice.springzoo.application.api;

import info.practice.springzoo.application.AnimalService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api
@RestController
@RequestMapping(path = "/zoo")
public class AnimalRestController {
    private final AnimalService animalService;

    public AnimalRestController(AnimalService animalService) {
        this.animalService = animalService;
    }
    
    @GetMapping("/animals")
    AnimalListResponse list() {
        return AnimalListResponse.from(animalService.listAllAnimals());
    }

    @PostMapping("/animals")
    void add(@Valid @RequestBody AnimalRequest animalRequest) {
        animalService.addAnimal(animalRequest.toAnimalDto());
    }
}
