package com.example.engine.exception;
import com.example.engine.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
   @ExceptionHandler(value = ResourceNotFoundException.class)
   public ResponseEntity<Object> exception(ResourceNotFoundException ex) {   	
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(value = IllegalArgumentException.class)
   public ResponseEntity<Object> exception() {   	
      return new ResponseEntity<>("Wrong uuid format", HttpStatus.NOT_FOUND);
   }
}