package com.groupeisi.secu_impots.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public class RequestException extends  RuntimeException{

    final String message;
    final HttpStatus status;

}
