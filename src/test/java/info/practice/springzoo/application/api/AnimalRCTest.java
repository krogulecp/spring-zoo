package info.practice.springzoo.application.api;

import info.practice.springzoo.application.AnimalService;
import info.practice.springzoo.domain.animal.AnimalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalRCTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        animalService.removeAllAnimals();
    }

    @Test
    void shouldGetAllAnimalsWithOneInRepo() {
        // given
        // trzeba włożyć do naszego repo zwierzaka
        // włożyć zwierzaka możemy poprzez wywołanie metody addAnimal w animal service\
        // trzeba zaimplementować odpowiednie metody w AnimalDomainServiceImpl

        //when
        ResponseEntity<AnimalListResponse> response = testRestTemplate
                .withBasicAuth("user1", "user1Pass")
                .getForEntity("/animals", AnimalListResponse.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AnimalListResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getAnimals().size());
    }

    @Test
    void shouldAddAnimal() {
        //given
        AnimalRequest animalRequest = AnimalRequest.builder()
                .name("Andrzej")
                .species("Seal")
                .dangerous(false)
                .weightKilos(50)
                .dateOfBirth(Instant.parse("2007-12-03T10:15:30.00Z"))
                .build();

        //when
        ResponseEntity<Void> response = testRestTemplate
                .withBasicAuth("admin", "adminPass")
                .postForEntity("/zoo/animals", animalRequest, Void.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 10001})
    void shouldReturnValidationErrorResponseForWeightNotValid(Integer weightKilos) {
        //given
        AnimalRequest animalRequest = AnimalRequest.builder()
                .name("Andrzej")
                .species("Seal")
                .dangerous(false)
                .weightKilos(weightKilos)
                .dateOfBirth(Instant.parse("2007-12-03T10:15:30.00Z"))
                .build();

        //when
        ResponseEntity<ErrorResponse> response = testRestTemplate
                .withBasicAuth("admin", "adminPass")
                .postForEntity("/zoo/animals", animalRequest, ErrorResponse.class);

        //then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("VALIDATION_ERROR", response.getBody().getCode());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Andrzej, Seal, 12",
            "M, Lion, 2",
            "zxczxczxczxc, qqqqqqqq, 22"
    })
    void shouldAddAnimalWithParams(String name, String species, int weightKilos) {
        //given
        AnimalRequest animalRequest = AnimalRequest.builder()
                .name(name)
                .species(species)
                .dangerous(false)
                .weightKilos(weightKilos)
                .dateOfBirth(Instant.parse("2007-12-03T10:15:30.00Z"))
                .build();

        //when
        ResponseEntity<Void> response = testRestTemplate
                .withBasicAuth("admin", "adminPass")
                .postForEntity("/zoo/animals", animalRequest, Void.class);

        //then
        List<AnimalDto> animals = animalService.listAllAnimals();
        AnimalDto animalInRepo = animals.get(0);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(name, animalInRepo.getName());
        assertEquals(species, animalInRepo.getSpecies());
        assertEquals(weightKilos, animalInRepo.getWeightKilos());
    }
}
