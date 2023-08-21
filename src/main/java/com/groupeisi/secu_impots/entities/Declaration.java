package com.groupeisi.secu_impots.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "declaration")
@Data
@NoArgsConstructor@AllArgsConstructor
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDeclarartion;
    private  double montantDeclaration;
    private String etat;
    @OneToOne()
    @JoinColumn(name = "idDeclarant")
    private Declarant declarant;
    @OneToMany(mappedBy = "declaration")
    private List<Paiement> Paiements;
}
