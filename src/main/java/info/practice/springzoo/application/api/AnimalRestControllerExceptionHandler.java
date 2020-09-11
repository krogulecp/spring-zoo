package info.practice.springzoo.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnimalRestControllerExceptionHandler {

    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .code(VALIDATION_ERROR)
                .message(exception.getMessage())
                .build();
    }
}
