package com.exam.exam.controller;
import com.exam.exam.entity.Declarant;
import com.exam.exam.entity.Declaration;
import com.exam.exam.service.DeclarantService;
import com.exam.exam.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/declarations")
public class DeclarationController {
    private final DeclarationService declarationService;
    private final DeclarantService declarantService;

    @Autowired
    public DeclarationController(DeclarationService declarationService, DeclarantService declarantService) {
        this.declarationService = declarationService;
        this.declarantService = declarantService;
    }

    @PostMapping
    public ModelAndView createDeclaration(@ModelAttribute Declaration declaration, Model model) {
        declarationService.create(declaration);
        model.addAttribute("declarations", declarationService.getAll());
        ModelAndView modelAndView = new ModelAndView("Declaration/list");
        return modelAndView;
    }

    @PutMapping("/{id}")
    public Declaration updateDeclaration(@PathVariable Long id, @RequestBody Declaration updatedDeclaration) {
        return declarationService.update(id, updatedDeclaration);
    }

    @GetMapping("/{id}")
    public ModelAndView deleteDeclaration(@PathVariable Long id,Model model) {
        declarationService.delete(id);
        ModelAndView modelAndView = new ModelAndView("Declaration/list");
        model.addAttribute("declarations", declarationService.getAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView("Declaration/list");
        model.addAttribute("declarations", declarationService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("declarants", declarantService.getAll());
        model.addAttribute("declaration", new Declaration());
        ModelAndView modelAndView = new ModelAndView("Declaration/add");
        return modelAndView;
    }
}
