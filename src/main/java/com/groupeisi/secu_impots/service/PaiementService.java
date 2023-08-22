package com.groupeisi.secu_impots.service;


import com.groupeisi.secu_impots.dto.PaiementDto;
import com.groupeisi.secu_impots.entities.Paiement;
import com.groupeisi.secu_impots.mapper.PaiementMapper;
import com.groupeisi.secu_impots.repositories.PaiementRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class PaiementService {
    public PaiementService(PaiementRepository repository, MessageSource messageSource, PaiementMapper paiementMapper) {
        this.repository = repository;
        this.messageSource = messageSource;
        this.paiementMapper = paiementMapper;

    }

    public PaiementRepository repository;
    MessageSource messageSource;
    public PaiementMapper paiementMapper;



    public  PaiementDto save (PaiementDto paiementDto)
    {
        return  paiementMapper.toEntity(
                repository.save(
                               paiementMapper.fromEntity(
                                       paiementDto
                               )
                )
        );

    }

    public Page<PaiementDto> findAllPaginated(int page,int size){
        return  repository
                .findAll(PageRequest.of(page, size))
                .map(paiementMapper::toEntity);

    }
}
