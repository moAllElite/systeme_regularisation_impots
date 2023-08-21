package com.groupeisi.secu_impots.service;


import com.groupeisi.secu_impots.dto.PaiementDto;
import com.groupeisi.secu_impots.entities.Paiement;
import com.groupeisi.secu_impots.mapper.PaiementMapper;
import com.groupeisi.secu_impots.repositories.PaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaiementService {
    private PaiementRepository repository;
    MessageSource messageSource;
    private PaiementMapper paiementMapper;
    public PaiementDto paiementDto;
    /**
     *

     * @return id paiement
     */
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

    @Transactional(readOnly = true)
    public Page<PaiementDto> findAllPaginated(int page,int size){
        return  repository
                .findAll(PageRequest.of(page, size))
                .map(paiementMapper::toEntity);

    }
}
