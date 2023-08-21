package com.groupeisi.secu_impots.controllers;

import com.groupeisi.secu_impots.dto.PaiementDto;
import com.groupeisi.secu_impots.entities.Paiement;
import com.groupeisi.secu_impots.service.PaiementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@AllArgsConstructor
public class PaiementController {
    public final PaiementService service;

   @GetMapping("/paiement")
    public PaiementDto save(
            PaiementDto paiementDto
    ){
        return service.save(paiementDto);
    }

    @PostMapping("/payer")
    public String payer(
       Model model,
       @Valid @ModelAttribute(name = "paie") PaiementDto paiementDto
    ){
       return  "list_declarations";
    }

    public String paiements(
            Model model,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        Page<PaiementDto> paiementsPaginated = service.findAllPaginated(page,size);
        model.addAttribute("currentPage", page);
          model.addAttribute("pages",new int [paiementsPaginated.getTotalPages()]);
        model.addAttribute("previousPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        return "list_paiements";
    }
}
