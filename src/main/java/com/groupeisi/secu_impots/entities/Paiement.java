package com.groupeisi.secu_impots.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
@Entity
@Table(name = "paiement")
@NoArgsConstructor@AllArgsConstructor
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePaiement;
    private  double montantPaiement;
    private double redevanceRestant;
    @ManyToOne
    @JoinColumn(name = "idDeclaration")
    private Declaration declaration;




}
