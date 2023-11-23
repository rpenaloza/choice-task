package com.encora.choice.webapp.controller.advice;

import com.encora.choice.webapp.vo.ErrorMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.xml.ws.WebServiceException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionControllerAdviceTest {

    private static ExceptionControllerAdvice controllerAdvice;

    @BeforeAll
    static void setup(){
        Map<String, HttpStatus> faultStatusMap = new HashMap<>();
        faultStatusMap.put("NO_SUCH_HOTEL", HttpStatus.NOT_FOUND);
        controllerAdvice = new ExceptionControllerAdvice(faultStatusMap);
    }

    @Test
    void handleException() {
        ResponseEntity<ErrorMessage> errorMessageResponseEntity = controllerAdvice.handleException(new Exception());
        assertEquals(errorMessageResponseEntity.getStatusCode().value(),500);
    }

    @Test
    void handleValidationException() {
        ResponseEntity<ErrorMessage> errorMessageResponseEntity = controllerAdvice.handleValidationException(new ConstraintViolationException(null));
        assertEquals(errorMessageResponseEntity.getStatusCode().value(),400);
    }

    void testHandleValidationException() throws NoSuchMethodException {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(null, "card");
        ResponseEntity<ErrorMessage> errorMessageResponseEntity = controllerAdvice.handleMethodArgumentException(new MethodArgumentNotValidException(new MethodParameter(
                this.getClass().getDeclaredMethod("testHandleValidationException", ExceptionControllerAdvice.class), 0),result));
        assertEquals(errorMessageResponseEntity.getStatusCode().value(),400);
    }

    @Test
    void handleNoSuchElementException() {
        ResponseEntity<ErrorMessage> errorMessageResponseEntity = controllerAdvice.handleNoSuchElementException(new NoSuchElementException());
        assertEquals(errorMessageResponseEntity.getStatusCode().value(),404);
    }

    @Test
    void handleServerSOAPFaultException() {
        ResponseEntity<ErrorMessage> errorMessageResponseEntity = controllerAdvice.handleWebServiceException(new WebServiceException(""));
        assertEquals(errorMessageResponseEntity.getStatusCode().value(),500);

    }
}