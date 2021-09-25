package dev.loanapplicationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Erhan Cavdar
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handling validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        // Get BindingResult object
        BindingResult bindingResult = exception.getBindingResult();
        // Populate errors map with field name and error message
        bindingResult.getFieldErrors().forEach((e) -> errors.put(e.getField(), e.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }
    // Handle applicaiton not found
    @ExceptionHandler(NotFoundAnyApplicationsException.class)
    public ResponseEntity<String> handleException(NotFoundAnyApplicationsException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    // Handle consumer not found on remote
    @ExceptionHandler(ConsumerNotFoundOnRemoteException.class)
    public ResponseEntity<String> handleException(ConsumerNotFoundOnRemoteException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    // Handle ID not valid
    @ExceptionHandler(NotAValidIDException.class)
    public ResponseEntity<String> handleException(NotAValidIDException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    // Handle not nullable exception
    @ExceptionHandler(NotNullableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<String> handleException(NotNullableException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    // Handle unsupported filter exception
    @ExceptionHandler(UnsupportedFilterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(UnsupportedFilterException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
