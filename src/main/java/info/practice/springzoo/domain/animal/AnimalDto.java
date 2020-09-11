package info.practice.springzoo.domain.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
@AllArgsConstructor
public class AnimalDto {
    String id;
    String name;
    String species;
    Instant dateOfBirth;
    Integer weightKilos;
    boolean dangerous;

    public static AnimalDto fromEntity(AnimalEntity animalEntity) {
        return AnimalDto.builder()
                .id(animalEntity.getId().toString())
                .name(animalEntity.getName())
                .species(animalEntity.getSpecies())
                .dateOfBirth(animalEntity.getDateOfBirth())
                .weightKilos(animalEntity.getWeightKilos())
                .dangerous(animalEntity.isDangerous())
                .build();
    }
}
