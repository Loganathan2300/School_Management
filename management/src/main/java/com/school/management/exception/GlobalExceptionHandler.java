package com.school.management.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.school.management.dto.MessageResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
    public ResponseEntity<MessageResponse> handleCustomException(CustomException ex, WebRequest request) {
		MessageResponse messageResponse = new MessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        });

        MessageResponse messageResponse = new MessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Validation error",
                errors.toString());

        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
	
	
}
