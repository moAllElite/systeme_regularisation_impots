package com.groupeisi.secu_impots.dto;

import com.groupeisi.secu_impots.entities.Declaration;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor@NoArgsConstructor
@Data
public class PaiementDto {
    private long id;
    @NotNull(message = "champs obligatoire")
    private Date datePaiement;
    @NotNull(message = "champs obligatoire")
    private  double montantPaiement;
    @NotNull(message = "champs  obligatoire")
    private double redevanceRestant;
    protected Declaration declaration;
}
