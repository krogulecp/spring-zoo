package info.practice.springzoo.application;

import info.practice.springzoo.domain.animal.AnimalDomainService;
import info.practice.springzoo.domain.animal.AnimalDto;
import info.practice.springzoo.domain.cage.CageDomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalDomainService animalDomainService;
    private final CageDomainService cageDomainService;

    public AnimalService(AnimalDomainService animalDomainService, CageDomainService cageDomainService) {
        this.animalDomainService = animalDomainService;
        this.cageDomainService = cageDomainService;
    }

    public List<AnimalDto> listAllAnimals() {
        return animalDomainService.listAllAnimals();
    }

    public String addAnimal(AnimalDto animalDto) {
        cageDomainService.lockAnimal(animalDto);
        return animalDomainService.addAnimal(animalDto);
    }

    public void removeAllAnimals() {
        animalDomainService.removeAll();
    }

}
