package ru.coffeemachine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.coffeemachine.dto.response.ValidationExceptionResponse;
import ru.coffeemachine.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationExceptionResponse> handleAccessDeniedException(HttpMessageNotReadableException exception) {
        ValidationExceptionResponse response = ValidationExceptionResponse.builder()
                .errors(Collections.singletonList(
                        ValidationExceptionResponse.ValidationErrorDto.builder()
                                .message(exception.getMessage())
                                .exception(exception.getClass().getCanonicalName())
                                .object(exception.getClass().toString())
                                .build()))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationExceptionResponse> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationExceptionResponse.ValidationErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationExceptionResponse.ValidationErrorDto errorDto = ValidationExceptionResponse.ValidationErrorDto.builder()
                    .message(errorMessage)
                    .exception(exception.getClass().getCanonicalName())
                    .object(exception.getClass().toString())
                    .build();

            String objectName;
            if (error instanceof FieldError)
                objectName = ((FieldError) error).getField();
            else
                objectName = error.getObjectName();
            errorDto.setObject(objectName);
            errors.add(errorDto);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationExceptionResponse.builder().errors(errors).build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ValidationExceptionResponse> handleNotFoundException(EntityNotFoundException exception) {
        ValidationExceptionResponse response = ValidationExceptionResponse.builder()
                .errors(Collections.singletonList(
                        ValidationExceptionResponse.ValidationErrorDto.builder()
                                .exception(exception.getClass().getCanonicalName())
                                .message(exception.getMessage())
                                .object(exception.getClass().toString())
                                .build()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ValidationExceptionResponse> handleAnyException(Exception exception) {
        ValidationExceptionResponse response = ValidationExceptionResponse.builder()
                .errors(Collections.singletonList(
                        ValidationExceptionResponse.ValidationErrorDto.builder()
                                .exception(exception.getClass().getCanonicalName())
                                .message(exception.getMessage())
                                .object(exception.getClass().toString())
                                .build()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

