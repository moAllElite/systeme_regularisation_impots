package com.groupeisi.secu_impots.repositories;


import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.entities.Declarant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeclarantRepository extends JpaRepository<Declarant, Long> {
    Page<Declarant> findByTelephoneContains(String tel, Pageable pageable);
    //@Query(value = "select d from Declarant d  where d.telephone=:telephone")
    Declarant findDeclarantByTelephone(String telephone);
}
