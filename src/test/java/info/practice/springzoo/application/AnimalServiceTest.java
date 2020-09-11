package info.practice.springzoo.application;

import info.practice.springzoo.domain.animal.AnimalDomainService;
import info.practice.springzoo.domain.animal.AnimalDto;
import info.practice.springzoo.domain.cage.CageDomainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalServiceTest {

    private AnimalDomainService animalDomainService = Mockito.mock(AnimalDomainService.class);
    private CageDomainService cageDomainService = Mockito.mock(CageDomainService.class);
    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        animalService = new AnimalService(animalDomainService, cageDomainService);
    }

    @Test
    void shouldAddAnimal() {
        //given
        final AnimalDto animalDto = AnimalDto.builder()
                .id("animal-1")
                .name("Maciek")
                .species("Lion")
                .dateOfBirth(Instant.parse("1970-01-01T00:00:00Z"))
                .dangerous(true)
                .weightKilos(200)
                .build();

        final String cageId = "cage-1";

        Mockito.when(animalDomainService.addAnimal(Mockito.any())).thenReturn(animalDto.getId());
        Mockito.when(cageDomainService.lockAnimal(Mockito.any())).thenReturn(cageId);

        //when
        String animalId = animalService.addAnimal(animalDto);

        //then
        assertEquals(animalDto.getId(), animalId);
    }

    @Test
    void shouldNotAddAnimalWhenAddingCageThrowsException() {
        //given
        final AnimalDto animalDto = AnimalDto.builder()
                .id("animal-1")
                .name("Maciek")
                .species("Lion")
                .dateOfBirth(Instant.parse("1970-01-01T00:00:00Z"))
                .dangerous(true)
                .weightKilos(200)
                .build();

        final String cageId = "cage-1";

        Mockito.when(animalDomainService.addAnimal(Mockito.any())).thenReturn(animalDto.getId());
        Mockito.when(cageDomainService.lockAnimal(Mockito.any())).thenThrow(RuntimeException.class);

        //when
        //then
        assertThrows(RuntimeException.class, () -> animalService.addAnimal(animalDto));
    }

}
