package com.encora.choice.webapp.controller.advice;

import com.encora.choice.webapp.vo.ErrorMessage;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Exception mapping strategy
 */
@Log4j
@ControllerAdvice
public class ExceptionControllerAdvice {


    private final Map<String, HttpStatus> faultCodeMap;

    public ExceptionControllerAdvice(Map<String, HttpStatus> faultCodeMap) {
        this.faultCodeMap = faultCodeMap;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleException(Exception e){
        log.error("general exception handler",e);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), e.getMessage(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(ValidationException.class)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleValidationException(Exception e){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(Exception e){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ServerSOAPFaultException.class)
    public ResponseEntity<ErrorMessage> handleServerSOAPFaultException(ServerSOAPFaultException e){
        HttpStatus status = getStatusForFault(e);
        ErrorMessage errorMessage = new ErrorMessage(status.value(), LocalDateTime.now(), e.getMessage(), e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, status);
    }

    private HttpStatus getStatusForFault(ServerSOAPFaultException e) {
        String faultCode = e.getFault().getFaultCode().split(":")[1];
        return faultCodeMap.getOrDefault(faultCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
