### Tasks

1. Tworzymy pakiet application, a w nim pakiet api - tutaj będą dostępne wszystkie kontrolery
2. Tworzymy pakiet domain - tutaj będą poszczególne domeny
3. W pakiecie domain tworzymy pierwszą domenę: animal
4. W pakiecie animal tworzymy klasę AnimalDto, która będzie miała pola:
    - id (string)
    - species (string)
    - dateOfBirth (instant)
    - weight (integer)
    - dangerous (boolean)
5. W pakiecie animal tworzymy interfejs AnimalDomainService z metodami:
    - List<AnimalDto> getAllAnimals()
    - void addAnimal(AnimalDto animalDto)
6. W pakiecie api tworzymy AnimalRestController
7. W powyższym kontrolerze tworzymy metody:
    - list - zwraca AnimalListResponse
    - add - zwraca void
8. W pakiecie application tworzymy AnimalService, który będzie miał metody:
    - List<AnimalDto> getAllAnimals()
    - void addAnimal(AnimalDto animalDto)
8. Medody powinny wywoływać odpowiednie metody z interfejsu AnimalService
9. Medody w AnimalService powinny wywoływać odpowiednie metody za AnimalDomainService

    
    
