package com.groupeisi.secu_impots.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "paiement")
@NoArgsConstructor@AllArgsConstructor
@Data
public class Paiement {
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date datepaiement;
    private  double montantPaiement;
    @ManyToOne
    @JoinColumn(name = "idDeclaration")
    private Declaration declaration;




}
