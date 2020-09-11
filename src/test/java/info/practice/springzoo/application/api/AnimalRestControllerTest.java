package info.practice.springzoo.application.api;

import info.practice.springzoo.application.AnimalService;
import info.practice.springzoo.domain.animal.AnimalDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AnimalRestControllerTest {

    @Autowired
    private AnimalRestController animalRestController;

    @Autowired
    private AnimalService animalService;

    @AfterEach
    void tearDown() {
        animalService.removeAllAnimals();
    }

    @Test
    void shouldReturnEmptyAnimals() {
        //given

        //when
        ResponseEntity<AnimalListResponse> response = animalRestController.list();

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getAnimals());
        assertEquals(response.getBody().getAnimals().size(), 0);
    }

    @Test
    void shouldReturnAnimals() {
        //given
        AnimalDto animalDto = sampleAnimal();
        animalService.addAnimal(animalDto);

        //when
        ResponseEntity<AnimalListResponse> response = animalRestController.list();

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<AnimalDto> animals = response.getBody().getAnimals();
        assertNotNull(animals);
        assertEquals(1, animals.size());
        AnimalDto animalDtoResult = animals.get(0);
        assertEquals(animalDto.getName(), animalDtoResult.getName());
        assertEquals(animalDto.getDateOfBirth(), animalDtoResult.getDateOfBirth());
        assertEquals(animalDto.getSpecies(), animalDtoResult.getSpecies());
        assertEquals(animalDto.getWeightKilos(), animalDtoResult.getWeightKilos());
    }

    @Test
    void shouldAddAnimal() {
        //given
        AnimalRequest animalRequest = AnimalRequest.builder()
                .name("Maciek")
                .species("Lion")
                .dateOfBirth(Instant.parse("1970-01-01T00:00:00Z"))
                .dangerous(true)
                .weightKilos(200)
                .build();

        //when
        ResponseEntity<Void> response = animalRestController.add(animalRequest);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(response.getHeaders().getLocation(), URI.create("/animals/" + animalService.listAllAnimals().get(0).getId()));
    }

    private AnimalDto sampleAnimal() {
        return AnimalDto.builder()
                .name("Maciek")
                .species("Lion")
                .dateOfBirth(Instant.parse("1970-01-01T00:00:00Z"))
                .dangerous(true)
                .weightKilos(200)
                .build();
    }
}
