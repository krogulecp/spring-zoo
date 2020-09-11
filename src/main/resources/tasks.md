# Tasks

## Part I

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

## Part II

1. Nie chcemy, żeby dodany zwierzak miał błędne dane, dlatego wprowadzamy walidację:
    - przy okazji poprawiamy nazwę weight na weightKilos (w request i w DTO)
    - przy okazji dodajemy też name do requestu i DTO
    - pole species nie może być nullowe ani puste
    - pole name nie może być ani nullowe ani puste
    - pole weight nie może być mniejsze od 0 oraz nie może być większe niż 10000kg

## Part III

1. Nie chcemy zwracać domyślnych błędów w przypadku walidacji, dlatego
   tworzymy nową klasę w pakiecie api -> AnimalRestControllerExceptionHandler
2. Adnotujemy klasę adnotacją @RestControllerAdvice
3. Tworzymy metodę, która przechwyci błędy walidacji:
    - nazywamy ją handleValidationException
    - adnotujemy ją @ExceptionHandler i jako argument podajemy MethodArgumentNotValidException.class
    - jako parametr przekazujemy exception tego samego typu co w adnotacji
    - adnotujemy metodę @ResponseStatus i ustawiamy UnprocessableEntity
4. Chcemy jeszcze zwrócić jakiś sensowny błąd:
    - tworzymy klasę ErrorResponse
    - wstawiamy pola code i message
    - zwracamy z metody handleValidationException zbudowany error response

## Part IV
1. Chcemy teraz, żeby nasza aplikacja była zabezpieczona przed.
2. W tym celu dodajemy do POM kolejną zależność - spring-boot-starter-security
3. Tworzymy w pakiecie application, pakiet config
4. Dodajemy tam klasę SecurityConfig
5. Klasa powinna mieć adnotacje @Configuration i @EnableWebSecurity oraz powinna dziedziczyć po WebSecurityConfigurerAdapter
6. W tej klasie powinny być metody przygotowujące użytkowników:
```  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoder().encode("user1Pass"))
                .authorities("ROLE_USER");

```

Passwod encoder:

```
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
```
    
Metoda konfiguracyjna:

```
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
```

7. Przetestujcie w postmanie, czy teraz jest możliwość dostania się do wystawionych endpointów
8. Rozszerzcie konfigurację, tak aby dodać zwierzaka, mógł tylko użytkownik z rolą admina.

## Part V

1. Dodajmy do naszej aplikacji actuatora
2. W tym celu należy dodać zależność spring-boot-starter-actuator
3. Przetestujcie w postmanie działanie andpointu /actuator/health
4. Jak zauważyliście, nie działa - dodajcie zatem konfigurację security, aby endpoint zadziałał

## Part VI

1. W pewnych sytuacjach, kiedy budujemy API, dobrze jest mieć i dać możliwość jego łatwego przetestowania
2. Zaimplementujemy w tym celu swaggera
3. Dodajemy zależność:
```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
``` 
4. Następnie w pakiecie application.config dodajemy klasę SwaggerConfig z adnotacjami @Configuration i @EnableSwagger2
5. Dodajemy metodę:
```
@Bean
public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
}
```
6. Wchodzimy na ścieżkę /swagger-ui
7. Czy jest jakiś problem ?
8. Musimy zaktualizować security i dodać kolejne ścieżki do ignorowanych "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**"
9. Wchodzimy jeszcze raz na powyższy endpoint i testujemy
10. Ale teraz jest jeszcze problem z widocznością większej ilości kontrolerów niż chcemy
11. Skonfigurujemy swaggera tak, żebyśmy widzieli tylko nasz kontroler

# Testing

## Part VII
1. Napiszmy test, sprawdzający, czy aplikacja się nam uruchamia
2. Czy takiego testu już nie ma w aplikacji?

## Part VIII
1. Napiszmy test wyciągnięcia wszystkich zwierzaków - zacznijmy od integracyjnego testu kontrolera
2. Napiszmy test dla sytuacji, gdzie nie ma żadnego zwierzaka (zwracanie wszystkich)
3. Powinniśmy zweryfikować, czy dostaniemy pustą listę zwierzaków
4. Test nie przechodzi ? Naprawmy go.

## Part IX
1. Napiszmy teraz test dla wyciągnięcia wszystkich zwierzaków kiedy są w repo
2. W tym celu powinniśmy zaimplementować po pierwsze metodę add:
    - repozytorim oraz encja w domain.animal powinny mieć dostęp pakietowy
    - możemy dodać zwierzaki z poziomu AnimalService, tylko za pośrednictwem metody addAnimal
3. Przetestujmy też metodę add z kontrolera przy okazji







