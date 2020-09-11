package info.practice.springzoo.domain.animal;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class AnimalDomainServiceImpl implements AnimalDomainService {

    private final AnimalRepository animalRepository;

    AnimalDomainServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<AnimalDto> listAllAnimals() {
        return animalRepository.findAll().stream()
                .map(AnimalDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public String addAnimal(AnimalDto animalDto) {
        return animalRepository.save(AnimalEntity.fromDto(animalDto)).getId().toString();
    }

    @Override
    public void removeAll() {
        animalRepository.deleteAll();
    }
}
