package com.groupeisi.secu_impots.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter@Setter

@AllArgsConstructor
public class ApiException extends Exception{
       final String  message ;
      final        HttpStatus  status;
      final LocalDateTime timestamp;

}
