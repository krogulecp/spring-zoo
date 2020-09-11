package info.practice.springzoo.domain.cage;

import info.practice.springzoo.domain.animal.AnimalDto;
import org.springframework.stereotype.Service;

@Service
public class CageDomainServiceImpl implements CageDomainService {
    @Override
    public String lockAnimal(AnimalDto animalDto) {
        throw new RuntimeException("not implemented yet");
    }
}
