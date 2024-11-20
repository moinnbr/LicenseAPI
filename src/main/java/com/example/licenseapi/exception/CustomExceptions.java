package com.example.licenseapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomExceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class LicenseNotFoundException extends RuntimeException {
        public LicenseNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class LicenseInactiveException extends RuntimeException {
        public LicenseInactiveException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class LicenseExpiredException extends RuntimeException {
        public LicenseExpiredException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BinNumberMismatchException extends RuntimeException {
        public BinNumberMismatchException(String message) {
            super(message);
        }
    }

}
