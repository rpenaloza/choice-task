package com.encora.choice.webapp.controller.advice;

import com.encora.choice.webapp.vo.ErrorMessage;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//import javax.validation.ValidationException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Log4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${soap.client.faultMapping}")
    private Map<String,Integer> faulCodeMapping;

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
        String faultCode = e.getFault().getFaultCode().split(":")[1];
        //TODO add soap fault constants to properties
        //TODO map fault constants to status
        log.info("faulCodeMapping.getOrDefault(faultCode,500) "+faulCodeMapping.getOrDefault(faultCode,500));
        ErrorMessage errorMessage = new ErrorMessage(faulCodeMapping.getOrDefault(faultCode,500), LocalDateTime.now() ,e.getMessage(),e.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }


}
