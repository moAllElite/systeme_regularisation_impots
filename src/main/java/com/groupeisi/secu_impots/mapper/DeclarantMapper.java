package com.groupeisi.secu_impots.mapper;

import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.entities.Declarant;
import org.mapstruct.Mapper;

@Mapper
public interface DeclarantMapper{
    DeclarantDto toEntity(Declarant declarant);
    Declarant fromEntity(DeclarantDto declarantDto);
}