package com.exam.exam.controller;
import com.exam.exam.entity.Declaration;
import com.exam.exam.service.DeclarantService;
import com.exam.exam.service.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/declarations")
public class DeclarationController {
    private final DeclarationService declarationService;
    private final DeclarantService declarantService;
    private static final String DECLARATIONS = "declarations";
    private static final String DECLARATION_LIST = "Declaration/list";

    @Autowired
    public DeclarationController(DeclarationService declarationService, DeclarantService declarantService) {
        this.declarationService = declarationService;
        this.declarantService = declarantService;
    }

    @PostMapping
    public ModelAndView createDeclaration(@ModelAttribute Declaration declaration, Model model) {
        declarationService.create(declaration);
        model.addAttribute(DECLARATIONS, declarationService.getAll());
        return new ModelAndView(DECLARATION_LIST);
    }

    @PutMapping("/{id}")
    public Declaration updateDeclaration(@PathVariable Long id, @RequestBody Declaration updatedDeclaration) {
        return declarationService.update(id, updatedDeclaration);
    }

    @GetMapping("/{id}")
    public ModelAndView deleteDeclaration(@PathVariable Long id,Model model) {
        declarationService.delete(id);
        ModelAndView modelAndView = new ModelAndView(DECLARATION_LIST);
        model.addAttribute(DECLARATIONS, declarationService.getAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView(DECLARATION_LIST);
        model.addAttribute(DECLARATIONS, declarationService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("declarants", declarantService.getAll());
        model.addAttribute("declaration", new Declaration());
        return new ModelAndView("Declaration/add");
    }
}
