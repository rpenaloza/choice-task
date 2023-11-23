package com.encora.choice.webapp.controller.advice;

import com.encora.choice.webapp.vo.ErrorMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.xml.ws.WebServiceException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Exception mapping strategy
 */
@Log4j2
@ControllerAdvice
public class ExceptionControllerAdvice {


    private final Map<String, HttpStatus> faultCodeMap;

    public ExceptionControllerAdvice(Map<String, HttpStatus> faultCodeMap) {
        this.faultCodeMap = faultCodeMap;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleException(Exception e){
        log.error("Caught unmapped exception of type "+ e.getClass().getCanonicalName());
        log.error("general exception handler",e);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), e.getMessage(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleValidationException(ConstraintViolationException e){
        log.error("ConstraintViolationException handler ",e);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleMethodArgumentException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException handler ",e);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException e){
        log.error("NoSuchElementException handler ",e);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(WebServiceException.class)
    public ResponseEntity<ErrorMessage> handleWebServiceException(WebServiceException e){
        log.error(e.getCause());
        log.error(e.getMessage());
        log.error(e.getLocalizedMessage());
        log.error("WebServiceException handler ",e);
        HttpStatus status = getStatusForFault(e);
        ErrorMessage errorMessage = new ErrorMessage(status.value(), LocalDateTime.now(), e.getMessage(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, status);
    }

    private HttpStatus getStatusForFault(WebServiceException e) {
        String message = e.getMessage();
        String faultCode = faultCodeMap.keySet().stream().filter(message::contains).findFirst().orElse("DEFAULT");
        log.info("mapping exception for fault code "+faultCode);
        return faultCodeMap.getOrDefault(faultCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
