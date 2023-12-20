package com.exam.exam.controller;
import com.exam.exam.entity.Declaration;
import com.exam.exam.entity.Paiement;
import com.exam.exam.service.DeclarationService;
import com.exam.exam.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/paiements")
public class PaiementController {
    private final PaiementService paiementService;
    private final DeclarationService declarationService;
    private static final String PAIEMENTS = "paiements";
    private static final String PAIEMENT_LIST = "Paiement/list";

    @Autowired
    public PaiementController(PaiementService paiementService, DeclarationService declarationService) {
        this.paiementService = paiementService;
        this.declarationService = declarationService;
    }

    @PostMapping
    public ModelAndView createPaiement(@ModelAttribute Paiement paiement, Model model) {
        paiementService.create(paiement);
        Declaration declaration = declarationService.getById(paiement.getDeclaration().getId());
        declaration.setMontantDeclaration(declaration.getMontantDeclaration()-paiement.getMontantPaiement());
        if(declaration.getMontantDeclaration() < 0){
            declaration.setMontantDeclaration(0.0);
        }
        declarationService.update(declaration.getId(),declaration);
        model.addAttribute(PAIEMENTS, paiementService.getAll());
        return new ModelAndView(PAIEMENT_LIST);
    }

    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable Long id, @RequestBody Paiement updatedPaiement) {
        return paiementService.update(id, updatedPaiement);
    }

    @GetMapping("/{id}")
    public ModelAndView deletePaiement(@PathVariable Long id,Model model) {
        paiementService.delete(id);
        ModelAndView modelAndView = new ModelAndView(PAIEMENT_LIST);
        model.addAttribute(PAIEMENTS, paiementService.getAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView(PAIEMENT_LIST);
        model.addAttribute(PAIEMENTS, paiementService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("declarations", declarationService.getAll());
        model.addAttribute("paiement", new Paiement());
        return new ModelAndView("Paiement/add");
    }
}
