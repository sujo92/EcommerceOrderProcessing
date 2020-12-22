package com.suziesta.Order.exceptions;


import com.suziesta.Order.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleExceprion(Exception ex, WebRequest request){
        String errMessageDesc = ex.getLocalizedMessage();
        if(errMessageDesc==null) errMessageDesc = ex.toString();
        ErrorMessage msg = new ErrorMessage(ZonedDateTime.now(),errMessageDesc);
        return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handlBadReqExceprion(BadRequestException ex, WebRequest request){
        String errMessageDesc = ex.getLocalizedMessage();
        if(errMessageDesc==null) errMessageDesc = ex.toString();
        ErrorMessage msg = new ErrorMessage(ZonedDateTime.now(),errMessageDesc);
        return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handlResourceNotExceprion(ResourceNotFoundException ex, WebRequest request){
        String errMessageDesc = ex.getLocalizedMessage();
        if(errMessageDesc==null) errMessageDesc = ex.toString();
        ErrorMessage msg = new ErrorMessage(ZonedDateTime.now(),errMessageDesc);
        return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
