package dev.findexinquiryapi.exceptions;

import dev.findexinquiryapi.DTO.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler class which handles all the custom ones along with
 * validation exceptions.
 * @author Erhan CAVDAR
 */
@RestControllerAdvice
public class GlobalHandler {
    // Handling validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ValidationErrorResponse> handleException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        // Get BindingResult object
        BindingResult bindingResult = exception.getBindingResult();
        // Populate errors map with field name and error message
        bindingResult.getFieldErrors().forEach((e) -> errors.put(e.getField(),e.getDefaultMessage()));
        return new ResponseEntity<ValidationErrorResponse>(new ValidationErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(),errors), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(HttpClientErrorException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle ConsumerNotExistentException
    @ExceptionHandler(ConsumerNotExistentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(ConsumerNotExistentException exception){
        return new ResponseEntity<>("Consumer not existent with the given fields please check the fields and try again.", HttpStatus.BAD_REQUEST);

    }
    // Handle ConsumerAlreadyExistsException
    @ExceptionHandler(ConsumerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(ConsumerAlreadyExistsException exception){
        return new ResponseEntity<>("Consumer already exists with the given ID.", HttpStatus.BAD_REQUEST);
    }
    // Handle NotAValidIDException
    @ExceptionHandler(NotAValidIDException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(NotAValidIDException exception){
        return new ResponseEntity<>("National ID should not end with an odd number.", HttpStatus.BAD_REQUEST);
    }
}
