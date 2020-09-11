### Tasks

1. Tworzymy pakiet api - tutaj będą dostępne wszystkie kontrolery
2. Tworzymy pakiet domain - tutaj będą poszczególne domeny
3. W pakiecie domain tworzymy pierwszą domenę: animal
4. W pakiecie animal tworzymy klasę AnimalDto, która będzie miała pola:
    - id (uuid)
    - species (string)
    - dateOfBirth (instant)
    - weight (integer)
    - dangerous (boolean)
5. W pakiecie animal tworzymy interfejs AnimalService z metodami:
    - List<AnimalDto> getAllAnimals()
    - void addAnimal(AnimalDto animalDto)
    - void removeAnimal()
    - AnimalDto update(AnimalDto animalDto)
6. W pakiecie API tworzymy AnimalRestController
7. W powyższym kontrolerze tworzymy metody:
    - list - zwraca AnimalListResponse
    - add - zwraca void
    - remove - zwraca void
    - updateData - zwraca SingleAnimalResponse
8. Medody powinny wywoływać odpowiednie metody z interfejsu AnimalService
    
    
