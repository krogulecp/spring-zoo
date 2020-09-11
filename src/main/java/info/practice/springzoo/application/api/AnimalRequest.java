package info.practice.springzoo.application.api;

import info.practice.springzoo.domain.animal.AnimalDto;
import lombok.Data;

import java.time.Instant;

@Data
public class AnimalRequest {
    String species;
    Instant dateOfBirth;
    Integer weight;
    double dangerous;

    public AnimalDto toAnimalDto() {
        return new AnimalDto(null, species, dateOfBirth, weight, dangerous);
    }
}
