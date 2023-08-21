package com.groupeisi.secu_impots.mapper;

import com.groupeisi.secu_impots.dto.DeclarationDto;
import com.groupeisi.secu_impots.entities.Declaration;
import org.mapstruct.Mapper;
@Mapper
public interface DeclarationMapper {
    DeclarationDto toEntity(Declaration declaration);
    Declaration fromEntity(DeclarationDto declarationDto);
}
