package com.groupeisi.secu_impots.controllers;


import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.service.DeclarantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class DeclarantController {
    public DeclarantService declarantService;
    public static final String DECLARANT_LIST_ROUTE = "declarants";
    public static final String MODEL_DECLARANT = "declarant";

    @GetMapping("/declarants")
    public String declarant(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword
    ) {

        Page<DeclarantDto> declarantPage = declarantService.findByTelephone(
                keyword, page, size
        );
        // Page<DeclarantDto> declarantPage = declarantService.findAll(page, size);
        model.addAttribute("pages", new int[declarantPage.getTotalPages()]);
        //on stock la page courrante
        model.addAttribute("currentPage", page);
        //on stock le nombre total de  page
        model.addAttribute("lenghtPage", declarantPage.getTotalPages() - 1);
        //on stock la page précédante
        model.addAttribute("previousPage", page - 1);
        //on stock la page suivante
        model.addAttribute("nextPage", page + 1);
        //on stock les éléments de la pagination
        model.addAttribute("listDeclarants", declarantPage.getContent());
        model.addAttribute("keyword", keyword);
        return "list_declarants";
    }


    @GetMapping(path = "/formDeclarant")
    public String register(Model model) {
        model.addAttribute("dec", new DeclarantDto());
        return "formDeclarant";
    }

    @PostMapping(path = "/save")
    public String save(
            @Valid @ModelAttribute(name = "dec") DeclarantDto declarantDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "formDeclarant";
        }
        declarantService.save(declarantDto);
        return  "redirect:/"+DECLARANT_LIST_ROUTE;

    }
}
