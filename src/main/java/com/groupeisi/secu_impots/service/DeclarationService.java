package com.groupeisi.secu_impots.service;

import com.groupeisi.secu_impots.dto.DeclarationDto;
import com.groupeisi.secu_impots.entities.Declaration;
import com.groupeisi.secu_impots.mapper.DeclarationMapper;
import com.groupeisi.secu_impots.repositories.DeclarationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class DeclarationService {
    public DeclarationService(DeclarationRepository declarationRepository, DeclarationMapper declarationMapper) {
        this.declarationRepository = declarationRepository;
        this.declarationMapper = declarationMapper;
    }

    public DeclarationRepository declarationRepository;
    public DeclarationMapper declarationMapper;

    /**
     *
     */
    @Transactional
    public Declaration add(Declaration declaration){
        return declarationRepository.save(
                        declaration
        );
    }

    /**
     * @return paginate list  of declaration
     */
    public Page<DeclarationDto> findAllPaginated(String tel, int page, int size){
        return declarationRepository
                .findAllByDeclarant_Telephone(tel,PageRequest.of(page, size))
                .map(declarationMapper::toEntity);
    }

    public  DeclarationDto findDeclarationByTelephoneDeclarant(String   tel){
        return  declarationMapper.toEntity(
                declarationRepository
                        .findAllByDeclarant_Telephone(tel)
        );
    }
}
