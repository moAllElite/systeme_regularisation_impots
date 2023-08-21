package com.groupeisi.secu_impots.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<ApiException> handleRequestException(RequestException requestException){
        ApiException apiException = new ApiException(requestException.getMessage(),requestException.getStatus(),LocalDateTime.now());
        return  new ResponseEntity<>(apiException,requestException.getStatus());
    }
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ApiException> handleEntityNotExceptionFound(EntityNotFoundException e) {
        ApiException apiException=new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return  new ResponseEntity<>(apiException,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<ApiException> handleNumberFormatException(NumberFormatException e){
        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return  new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleMethodArgumentNotValideException(MethodArgumentNotValidException e){
        ApiException apiException=new ApiException("la valeur saisie est invalide",HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return  new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
