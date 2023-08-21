package com.groupeisi.secu_impots.exception;

import lombok.AllArgsConstructor;




@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {
       private final String message;
}
