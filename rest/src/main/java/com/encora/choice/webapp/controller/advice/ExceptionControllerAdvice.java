package com.encora.choice.webapp.controller.advice;

import com.encora.choice.webapp.vo.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception e){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleValidationException(Exception e){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoSuchElementException(Exception e){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
    }
}
