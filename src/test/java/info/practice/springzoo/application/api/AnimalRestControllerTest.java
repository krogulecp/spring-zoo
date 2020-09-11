package info.practice.springzoo.application.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AnimalRestControllerTest {

    @Autowired
    private AnimalRestController animalRestController;

    @Test
    void shouldReturnAnimals() {
        //given

        //when
        ResponseEntity<AnimalListResponse> response = animalRestController.list();

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getAnimals());
        assertEquals(response.getBody().getAnimals().size(), 0);
    }
}
