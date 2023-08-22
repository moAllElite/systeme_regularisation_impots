package com.groupeisi.secu_impots.repositories;

import com.groupeisi.secu_impots.entities.Paiement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    Page<Paiement> findAllByDeclaration_DeclarantTelephone(String tel, Pageable pageable);
}
