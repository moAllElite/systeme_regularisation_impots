package com.groupeisi.secu_impots.dto;


import com.groupeisi.secu_impots.entities.Declarant;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor@AllArgsConstructor
public class DeclarationDto {
    private  long id;
    @NotEmpty(message = "Le champs est date est obligatoire")
    @Past(message = "la date saisie est incorrect")
    @Future(message = "la date saisie esst incorrect")
    private Date dateDeclarartion;

     private Declarant declarant;

     private  String etat;
    @Positive(message = "Le champs montant est obligatoire")
    private  double montantDeclaration;

}
