package com.groupeisi.secu_impots.service;


import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.entities.Declarant;
import com.groupeisi.secu_impots.mapper.DeclarantMapper;
import com.groupeisi.secu_impots.repositories.DeclarantRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeclarantService {

    public DeclarantRepository declarantRepository;
    public DeclarantMapper declarantMapper;
    MessageSource messageSource;
    public DeclarantService(DeclarantRepository declarantRepository, DeclarantMapper declarantMapper, MessageSource messageSource) {
        this.declarantRepository = declarantRepository;
        this.declarantMapper = declarantMapper;
        this.messageSource = messageSource;
    }
    @Transactional
    public DeclarantDto save(DeclarantDto declarantDto){
        return declarantMapper.toEntity(
                declarantRepository.save(
                        declarantMapper.fromEntity(
                                declarantDto
                        )
                )
        );

    }


    public Page<DeclarantDto> findByTelephone(String tel, int page, int size ){
        return declarantRepository
                .findByTelephoneContains(tel, PageRequest.of(page,size))
                .map(declarantMapper::toEntity);
    }
    public DeclarantDto findByTelephone(String telephone){
        return  declarantMapper.toEntity(
                declarantRepository
                        .findDeclarantByTelephone(telephone)
        )
        ;
                /*.orElseThrow(
                        ()-> new EntityNotFoundException(
                                messageSource.getMessage("declarant.notfound",
                                new Object[]{},
                                Locale.getDefault()
                                )
                        )
                )
                .getId();*/
    }
}
