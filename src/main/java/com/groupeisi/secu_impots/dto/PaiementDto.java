package com.groupeisi.secu_impots.dto;

import com.groupeisi.secu_impots.entities.Declaration;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor@NoArgsConstructor
@Data
public class PaiementDto {
    private long id;

    private Date datepaiement;
    @NotEmpty(message = "Le champs  montant est  obligatoire ")
    private  double montantPaiement;
    @NotNull
    protected Declaration declaration;
}
