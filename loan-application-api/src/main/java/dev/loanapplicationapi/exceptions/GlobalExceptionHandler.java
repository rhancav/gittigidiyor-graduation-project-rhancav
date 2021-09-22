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

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handling validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        // Get BindingResult object
        BindingResult bindingResult = exception.getBindingResult();
        // Populate errors map with field name and error message
        bindingResult.getFieldErrors().forEach((e) -> errors.put(e.getField(),e.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }
}
