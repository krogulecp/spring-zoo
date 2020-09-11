package info.practice.springzoo.domain.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface AnimalRepository extends JpaRepository<AnimalEntity, UUID> {
    AnimalEntity save(AnimalDto animalDto);
}
