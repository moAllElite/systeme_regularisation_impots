package com.groupeisi.secu_impots.repositories;

import com.groupeisi.secu_impots.entities.Declarant;
import com.groupeisi.secu_impots.entities.Declaration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeclarationRepository extends JpaRepository<Declaration,Long> {
    Page<Declaration> findAllByDeclarant_Telephone(String tel, Pageable pageable);
    Declaration findAllByDeclarant_Telephone(String tel);
}
