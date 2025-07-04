package com.cnj.inventorysvc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex){
      return  ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Product not found exception: "+ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleProductRuntimeException(RuntimeException ex){

        log.error("Unhandled runtime exception occurred: ",ex);
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("RuntimeException occurred:+ "+ex.getMessage());
    }
}
