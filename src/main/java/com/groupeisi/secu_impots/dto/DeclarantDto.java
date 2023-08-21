package com.groupeisi.secu_impots.dto;



import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclarantDto {
    private  Long id;
    @NotEmpty(message = "La raison social ne doit pas etre vide")
    private  String raisonSociale;
    @NotEmpty(message = "Le champs adresse est  obligatoire")
    private  String adresse;
    @NotEmpty(message = "Le champs   email est  obligatoire ")
    @Email(message = "L' email renseigné est invalide ")
    private  String email;
    @Pattern(regexp = "\\d{9}",message = "le numéro de téléphone doit comprendre 9 chiffres ")
    @NotEmpty(message = "Le téléphone ne doit pas etre vide")
     private String  telephone;
}
