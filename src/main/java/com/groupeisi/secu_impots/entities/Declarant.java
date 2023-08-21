package com.groupeisi.secu_impots.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity @Table(name = "declarant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Declarant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  long id;
    private  String raisonSociale;
    private  String adresse;
    private  String email;
    private String  telephone;



}
