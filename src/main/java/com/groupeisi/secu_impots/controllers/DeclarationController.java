package com.groupeisi.secu_impots.controllers;

import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.dto.DeclarationDto;
import com.groupeisi.secu_impots.entities.Declarant;
import com.groupeisi.secu_impots.entities.Declaration;
import com.groupeisi.secu_impots.entities.Etat;
import com.groupeisi.secu_impots.mapper.DeclarantMapper;
import com.groupeisi.secu_impots.service.DeclarantService;
import com.groupeisi.secu_impots.service.DeclarationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller

public class DeclarationController {
    public DeclarationService declarationService;
    public DeclarantService declarantService;
    public DeclarantMapper declarantMapper;
    @GetMapping("/declarations")
    public String showAll(
            Model model,
            @RequestParam(name = "telephone",defaultValue = "")String tel,
            @RequestParam(name = "page",defaultValue = "0")  int page,
            @RequestParam(name = "size",defaultValue = "4")  int size

            ){
        Page<DeclarationDto> declarationPage =  declarationService.findAllPaginated(tel,page,size);
        //nombre total de page
        model.addAttribute("declaration",declarationPage.getContent());
        model.addAttribute("pages",new int[declarationPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        System.out.println("fu"+declarationPage.getContent());
        model.addAttribute("previousPage",page - 1);
        model.addAttribute("nextPage",page + 1);
        model.addAttribute("lengthPage",declarationPage.getTotalPages() -1);
        return "list_declarations";
    }
    @GetMapping(path = "/formDeclaration")
    public  String add(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size,
            @RequestParam(name = "telephone",defaultValue = "") String tel
    ){
        Page<DeclarantDto> declarantPage = declarantService.findByTelephone(
                tel, page,size
        );
        model.addAttribute(
                "obtainedDeclarant", declarantPage.getContent()
        );

        model.addAttribute("declaration",new Declaration());

        return  "formDeclaration";
    }
    @PostMapping(path = "/declarer")
    public  String addDeclaration(
            Model model,
            @ModelAttribute(name = "declarant") Declarant declarant,
            @ModelAttribute(name = "declaration") Declaration declaration,
            @RequestParam(name = "telephone",defaultValue = "") String tel,
            BindingResult bindingResult
    ){
        if( bindingResult.hasErrors()) {
            return "formDeclaration";
        }
        /*
            on récupère le numéro de téléphone  choisie
            on  fait  un getId du déclérant
            */
        DeclarantDto declarantDto =declarantService.findByTelephone(tel);
        /*
            récup info déclarant
         */
        declarant.setId( declarantDto.getId());
        declarant.setEmail(declarantDto.getEmail());
        declarant.setAdresse(declarantDto.getAdresse());
        declarant.setRaisonSociale(declarantDto.getRaisonSociale());
        declaration.setDeclarant(declarant);

        declaration.setEtat(Etat.IMPAYER.name());
        model.addAttribute("declaration",declaration);


        model.addAttribute("message","Enregistrement effectué avec succès");
        declarationService.add(declaration);
        return "redirect:/formDeclaration";
    }


}
