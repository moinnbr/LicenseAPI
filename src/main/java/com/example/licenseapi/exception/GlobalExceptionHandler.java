package com.example.licenseapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomExceptions.LicenseNotFoundException.class)
    public ResponseEntity<String> handleLicenseNotFoundException(CustomExceptions.LicenseNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomExceptions.LicenseInactiveException.class)
    public ResponseEntity<String> handleLicenseInactiveException(CustomExceptions.LicenseInactiveException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomExceptions.LicenseExpiredException.class)
    public ResponseEntity<String> handleLicenseExpiredException(CustomExceptions.LicenseExpiredException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomExceptions.BinNumberMismatchException.class)
    public ResponseEntity<String> handleBinNumberMismatchException(CustomExceptions.BinNumberMismatchException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
