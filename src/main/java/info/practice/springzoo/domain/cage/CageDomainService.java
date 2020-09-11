package info.practice.springzoo.domain.cage;

import info.practice.springzoo.domain.animal.AnimalDto;

public interface CageDomainService {
    String lockAnimal(AnimalDto animalDto);
}
