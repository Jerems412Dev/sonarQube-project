package com.exam.exam.controller;
import com.exam.exam.entity.Declaration;
import com.exam.exam.entity.Paiement;
import com.exam.exam.service.DeclarationService;
import com.exam.exam.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/paiements")
public class PaiementController {
    private final PaiementService paiementService;
    private final DeclarationService declarationService;

    @Autowired
    public PaiementController(PaiementService paiementService, DeclarationService declarationService) {
        this.paiementService = paiementService;
        this.declarationService = declarationService;
    }

    @PostMapping
    public ModelAndView createPaiement(@ModelAttribute Paiement paiement, Model model, Declaration declaration) {
        paiementService.create(paiement);
        declaration = declarationService.getById(paiement.getDeclaration().getId());
        declaration.setMontantDeclaration(declaration.getMontantDeclaration()-paiement.getMontantPaiement());
        if(declaration.getMontantDeclaration() < 0){
            declaration.setMontantDeclaration(0.0);
        }
        declarationService.update(declaration.getId(),declaration);
        model.addAttribute("paiements", paiementService.getAll());
        ModelAndView modelAndView = new ModelAndView("Paiement/list");
        return modelAndView;
    }

    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable Long id, @RequestBody Paiement updatedPaiement) {
        return paiementService.update(id, updatedPaiement);
    }

    @GetMapping("/{id}")
    public ModelAndView deletePaiement(@PathVariable Long id,Model model) {
        paiementService.delete(id);
        ModelAndView modelAndView = new ModelAndView("Paiement/list");
        model.addAttribute("paiements", paiementService.getAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView("Paiement/list");
        model.addAttribute("paiements", paiementService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("declarations", declarationService.getAll());
        model.addAttribute("paiement", new Paiement());
        ModelAndView modelAndView = new ModelAndView("Paiement/add");
        return modelAndView;
    }
}
