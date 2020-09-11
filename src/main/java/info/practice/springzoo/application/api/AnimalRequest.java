package info.practice.springzoo.application.api;

import info.practice.springzoo.domain.animal.AnimalDto;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Validated
public class AnimalRequest {

    @NotBlank
    @NotNull
    String name;

    @NotBlank
    @NotNull
    String species;

    Instant dateOfBirth;

    @Min(value = 0)
    @Max(value = 10000)
    Integer weightKilos;
    boolean dangerous;

    public AnimalDto toAnimalDto() {
        return new AnimalDto(null, name, species, dateOfBirth, weightKilos, dangerous);
    }
}
