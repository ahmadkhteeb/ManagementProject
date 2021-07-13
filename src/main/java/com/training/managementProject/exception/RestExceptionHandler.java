package com.training.managementProject.exception;

import javassist.bytecode.DuplicateMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Handling not found exceptions
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception){
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                exception
        );
        apiError.setMessage(exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Handling duplicate entity exception
    @ExceptionHandler(DuplicateMemberException.class)
    protected ResponseEntity<Object> handleDuplicateMemberException(DuplicateMemberException exception){
        ApiError apiError = new ApiError(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                exception
        );
        apiError.setMessage(exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

}
