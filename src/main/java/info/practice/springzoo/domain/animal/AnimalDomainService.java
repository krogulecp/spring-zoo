package info.practice.springzoo.domain.animal;

import java.util.List;

public interface AnimalDomainService {
    List<AnimalDto> listAllAnimals();

    String addAnimal(AnimalDto animalDto);

    void removeAll();
}
