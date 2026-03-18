package com.spring.controllers;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.spring.exceptions.CoachXBadRequestException;
import com.spring.exceptions.CoachXNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(CoachXNotFoundException.class)
	public ResponseEntity<Response> handleNotFound(CoachXNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Response.errorResponse(Response.NOT_FOUND, ex.getMessage()));
	}

	
	@ExceptionHandler(CoachXBadRequestException.class)
	public ResponseEntity<Response> handleBadRequest(CoachXBadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(Response.errorResponse(Response.BAD_REQUEST, ex.getMessage()));
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleValidation(MethodArgumentNotValidException ex) {
		String errores = ex.getBindingResult().getFieldErrors().stream()
				.map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).collect(Collectors.joining("; "));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(Response.errorResponse(Response.BAD_REQUEST, "Datos de entrada inválidos: " + errores));
	}

	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Response> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String msg = "Valor inválido para el parámetro '" + ex.getName() + "': " + ex.getValue();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.errorResponse(Response.BAD_REQUEST, msg));
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleGeneral(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Response.errorResponse(500, "Error interno del servidor: " + ex.getMessage()));
	}
}
