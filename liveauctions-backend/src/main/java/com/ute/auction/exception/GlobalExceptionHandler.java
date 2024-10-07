package com.ute.auction.exception;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(ConstraintViolationException.class)
    // public ResponseEntity<List<String>>
    // handleValidationExceptions(ConstraintViolationException ex) {
    // List<String> errors = new ArrayList<>();
    // ex.getConstraintViolations().forEach(error -> {
    // errors.add(error.getPropertyPath() + ": " + error.getMessage());
    // });
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    // }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceExistedException.class)
    public ResponseEntity<String> handleResourceExistedException(ResourceExistedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFormatException.class)
    public ResponseEntity<String> handleResourceNotFormatException(ResourceNotFormatException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

}
