package info.practice.springzoo.application.api;

import info.practice.springzoo.domain.animal.AnimalDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalListResponse {

    List<AnimalDto> animals;

    public static AnimalListResponse from(List<AnimalDto> animals) {
        return new AnimalListResponse(animals);
    }
}
