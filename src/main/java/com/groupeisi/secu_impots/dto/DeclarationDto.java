package com.groupeisi.secu_impots.dto;


import com.groupeisi.secu_impots.entities.Declarant;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor@AllArgsConstructor
public class DeclarationDto {
    private  long id;
    @NotNull(message = "champs obligatoire")
    private Date dateDeclarartion;
     private Declarant declarant;
     private  String etat;
    @NotNull(message = "champs obligatoire")
     private  double montantDeclaration;

}
