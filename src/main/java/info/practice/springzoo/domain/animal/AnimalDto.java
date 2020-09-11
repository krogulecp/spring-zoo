package info.practice.springzoo.domain.animal;

import lombok.Value;

import java.time.Instant;

@Value
public class AnimalDto {
    String id;
    String species;
    Instant dateOfBirth;
    Integer weight;
    double dangerous;
}
