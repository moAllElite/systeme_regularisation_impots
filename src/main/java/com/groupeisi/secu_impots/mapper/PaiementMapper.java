package com.groupeisi.secu_impots.mapper;


import com.groupeisi.secu_impots.dto.PaiementDto;
import com.groupeisi.secu_impots.entities.Paiement;
import org.mapstruct.Mapper;

@Mapper
public interface PaiementMapper {
    PaiementDto toEntity(Paiement paiement);
   Paiement fromEntity(PaiementDto paiementDto);
}
