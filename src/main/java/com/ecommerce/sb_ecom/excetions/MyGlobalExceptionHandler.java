package com.ecommerce.sb_ecom.excetions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.sb_ecom.payload.ApiResonpose;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName, message);
        });

        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResonpose> resourceNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();
        ApiResonpose apiResonpose = new ApiResonpose(message, false);
        return new ResponseEntity<>(apiResonpose, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ApiResonpose> apiException(APIException e) {
        String message = e.getMessage();
        ApiResonpose apiResonpose = new ApiResonpose(message, false);
        return new ResponseEntity<>(apiResonpose, HttpStatus.BAD_REQUEST);
    }

}
