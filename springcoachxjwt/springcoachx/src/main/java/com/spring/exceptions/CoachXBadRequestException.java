package com.spring.exceptions;

public class CoachXBadRequestException extends RuntimeException {
    public CoachXBadRequestException(String mensaje) {
        super(mensaje);
    }
}
