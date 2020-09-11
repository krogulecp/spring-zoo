package info.practice.springzoo.application;

import info.practice.springzoo.domain.animal.AnimalDomainService;
import info.practice.springzoo.domain.animal.AnimalDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalDomainService animalDomainService;

    public AnimalService(AnimalDomainService animalDomainService) {
        this.animalDomainService = animalDomainService;
    }

    public List<AnimalDto> listAllAnimals() {
        return animalDomainService.listAllAnimals();
    }

    public void addAnimal(AnimalDto animalDto) {
        animalDomainService.addAnimal(animalDto);
    }

}
