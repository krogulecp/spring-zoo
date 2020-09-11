package info.practice.springzoo.domain.animal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity(name = "animal")
class AnimalEntity {

    @Id
    @GeneratedValue
    UUID id;
    String name;
    String species;
    Instant dateOfBirth;
    Integer weightKilos;
    boolean dangerous;
}
