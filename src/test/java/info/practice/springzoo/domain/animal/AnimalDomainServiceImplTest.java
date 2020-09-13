package info.practice.springzoo.domain.animal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalDomainServiceImplTest {

    // To będę testował
    private AnimalDomainServiceImpl animalDomainService;

    // To jest zależność mojej klasy, którą będę testował
    private AnimalRepository animalRepositoryMock;

    @BeforeEach
    void setUp() {
        animalRepositoryMock = Mockito.mock(AnimalRepository.class); // stworzyłem mocka
        animalDomainService = new AnimalDomainServiceImpl(animalRepositoryMock); // wstrzykąłem mocka do testowanego obiektu
    }

    @Test
    void shouldReturnEmptyAnimalList(){
        //given
        Mockito.when(animalRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        //when
        List<AnimalDto> animals = animalDomainService.listAllAnimals();

        //then
        assertEquals(0, animals.size());
    }

    @Test
    void shouldReturnAnimalOnList(){
        //given
        final AnimalEntity animalEntity = AnimalEntity.builder()
                .id(UUID.randomUUID())
                .dangerous(true)
                .name("Maciek")
                .species("Lion")
                .dateOfBirth(Instant.parse("2007-12-03T10:15:30.00Z"))
                .weightKilos(200)
                .build();

        Mockito.when(animalRepositoryMock.findAll()).thenReturn(List.of(animalEntity));

        //when
        List<AnimalDto> animals = animalDomainService.listAllAnimals();

        //then
        assertEquals(1, animals.size());
        // sprawdzanie poszczególnych pól
    }
}
