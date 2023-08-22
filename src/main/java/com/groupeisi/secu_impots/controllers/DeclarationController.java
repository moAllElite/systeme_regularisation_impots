package com.groupeisi.secu_impots.controllers;

import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.dto.DeclarationDto;
import com.groupeisi.secu_impots.dto.PaiementDto;
import com.groupeisi.secu_impots.entities.Declarant;
import com.groupeisi.secu_impots.entities.Declaration;
import com.groupeisi.secu_impots.entities.Etat;
import com.groupeisi.secu_impots.entities.Paiement;
import com.groupeisi.secu_impots.mapper.DeclarantMapper;
import com.groupeisi.secu_impots.mapper.DeclarationMapper;
import com.groupeisi.secu_impots.mapper.PaiementMapper;
import com.groupeisi.secu_impots.service.DeclarantService;
import com.groupeisi.secu_impots.service.DeclarationService;
import com.groupeisi.secu_impots.service.PaiementService;
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


@AllArgsConstructor
@Controller

public class DeclarationController {
    public DeclarationService declarationService;
    public DeclarantService declarantService;
    public PaiementMapper paiementMapper;
    public final PaiementService paiementService;
    public DeclarantMapper declarantMapper;
    public DeclarationMapper declarationMapper;
    @GetMapping("/declarations")
    public String showAll(
            Model model,
            @RequestParam(name = "telephone",defaultValue = "")String tel,
            @RequestParam(name = "page",defaultValue = "0")  int page,
            @RequestParam(name = "size",defaultValue = "4")  int size
        //    @ModelAttribute(name = "paye") PaiementDto paiementDto
            ){
        Page<DeclarationDto> declarationPage =  declarationService.findAllPaginated(tel,page,size);
        //nombre total de page
        model.addAttribute("declaration",declarationPage.getContent());
        model.addAttribute("pages",new int[declarationPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("previousPage",page - 1);
        model.addAttribute("nextPage",page + 1);
        model.addAttribute("lengthPage",declarationPage.getTotalPages() -1);

        return "list_declarations";
    }
    /*
        paiementDto
     */
    @PostMapping(path = "/versement")
    public String payer(
            Model model,
            @RequestParam(name = "telephone") String tel,
            @ModelAttribute(name = "paye") PaiementDto paiementDto,

            @ModelAttribute(name = "declaration")DeclarationDto declarationDto,
            BindingResult bindingResult
    ){
        if( bindingResult.hasErrors()) {
            return "list_declarations";
        }
        /*paiementDto*/

        declarationDto = declarationService.findDeclarationByTelephoneDeclarant(tel);
        //   recup info deeclaration
        //   mapping dto -> entity
        paiementDto.setDeclaration(
                declarationMapper.fromEntity(declarationDto)
        );

        if(
                paiementDto.getMontantPaiement() == paiementDto.getDeclaration().getMontantDeclaration()
                || paiementDto.getRedevanceRestant() == paiementDto.getMontantPaiement()
        ){
            declarationDto.setEtat(
                    Etat.PAYER.name()
            );
            paiementDto.setDeclaration(
                    declarationMapper.fromEntity(
                            declarationDto
                    )
            );
            paiementDto.setRedevanceRestant(
                    0
            );
            paiementService.save(paiementDto);
        }else if(
                paiementDto.getMontantPaiement() > paiementDto.getDeclaration().getMontantDeclaration() ||
                paiementDto.getRedevanceRestant() < paiementDto.getMontantPaiement()
        ){
            model.addAttribute(
                    "error",
                    "echec transaction montant renseigné supérieur à la redevance "
            );
            return "redirect:/transaction";
        }else {
            double      reste=  paiementDto.getDeclaration().getMontantDeclaration() - paiementDto.getMontantPaiement();
            System.out.println("le reste"+reste);
            paiementDto.setRedevanceRestant(reste);
            declarationDto.setEtat(
                    Etat.IMPAYER.name()
            );
            paiementDto.setDeclaration(
                    declarationMapper.fromEntity(declarationDto)
            );
            paiementService.save(paiementDto);
        }

        System.out.println("paye"+paiementDto);
        model.addAttribute("paye",paiementDto);
        return  "redirect:/formDeclaration";
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
            on  fait  un getId du déclérantv*/

        DeclarantDto declarantDto =declarantService.findByTelephone(tel);

           // récup info déclarant

        declarant.setId( declarantDto.getId());
        declarant.setEmail(declarantDto.getEmail());
        declarant.setAdresse(declarantDto.getAdresse());
        declarant.setRaisonSociale(declarantDto.getRaisonSociale());
        declaration.setDeclarant(declarant);

        declaration.setEtat(Etat.IMPAYER.name());
        model.addAttribute("declaration",declaration);
         declarationService.add(declaration);
          return "redirect:/declarations";
    }


    /**
     * paiement
     */
    @GetMapping("/transaction")
    public String showAll(
            Model model,
            @RequestParam(name = "telephone",defaultValue = "")String tel,
            @RequestParam(name = "page",defaultValue = "0")  int page,
            @RequestParam(name = "size",defaultValue = "4")  int size,
            @ModelAttribute(name = "paye") PaiementDto paiementDto
    ){
        Page<DeclarationDto> declarationPage =  declarationService.findAllPaginated(tel,page,size);
        //nombre total de page
        model.addAttribute("declaration",declarationPage.getContent());
        model.addAttribute("pages",new int[declarationPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("previousPage",page - 1);
        model.addAttribute("nextPage",page + 1);
        model.addAttribute("lengthPage",declarationPage.getTotalPages() -1);
        /*
            paiement
         */


        DeclarationDto declarationDto    =
                declarationService.findDeclarationByTelephoneDeclarant(tel);
        //   recup info deeclaration

        if (declarationDto != null){
            //   mapping dto -> entity
            paiementDto.setDeclaration(
                    declarationMapper.fromEntity(declarationDto)
            );
            model.addAttribute("paye",paiementDto);
        }

        return "paid_declarations";
    }


    @GetMapping(path = "/paiements")
    public String paiements(
            Model model,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) {
        Page<PaiementDto> paiementsPaginated = paiementService.findAllPaginated(page,size);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages",new int [paiementsPaginated.getTotalPages()]);
        model.addAttribute("previousPage", page - 1);
        model.addAttribute("nextPage", page + 1);
        return "list_paiement";
    }
}
