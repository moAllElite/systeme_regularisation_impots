package com.groupeisi.secu_impots.controllers;

import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.dto.DeclarationDto;
import com.groupeisi.secu_impots.dto.PaiementDto;
import com.groupeisi.secu_impots.entities.Declarant;
import com.groupeisi.secu_impots.mapper.DeclarationMapper;
import com.groupeisi.secu_impots.service.DeclarationService;
import com.groupeisi.secu_impots.service.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
public class PaiementController {
    public final PaiementService paiementService;
    public final DeclarationService declarationService;
    public DeclarationMapper declarationMapper;



    @GetMapping(path = "/paiements")
    public String paiements(
            Model model,
            @RequestParam (name = "tel",defaultValue = "") String tel,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        Page<PaiementDto> paiementsPaginated = paiementService
                .findAllPaiementByTelephone(tel,page,size);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages",new int [paiementsPaginated.getTotalPages()]);
        model.addAttribute("previousPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        return "list_paiement";
    }

}
