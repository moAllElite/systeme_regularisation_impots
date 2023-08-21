package com.groupeisi.secu_impots.repositories;

import com.groupeisi.secu_impots.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaiementRepository extends JpaRepository<Paiement,Long> {
}
