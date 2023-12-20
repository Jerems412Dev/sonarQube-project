package com.exam.exam.controller;
import com.exam.exam.entity.Declarant;
import com.exam.exam.service.DeclarantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/declarants")
public class DeclarantController {
    private final DeclarantService declarantService;
    private static final String DECLARANT = "declarants";
    private static final String DECLARANT_ADD = "Declarant/add";
    private static final String DECLARANT_LIST = "Declarant/list";

    @Autowired
    public DeclarantController(DeclarantService declarantService) {
        this.declarantService = declarantService;
    }

    @PostMapping
    public ModelAndView createDeclarant(@ModelAttribute Declarant declarant,Model model) {
        declarantService.create(declarant);
        model.addAttribute(DECLARANT, declarantService.getAll());
        return new ModelAndView(DECLARANT_LIST);
    }

    @PutMapping("/{id}")
    public Declarant updateDeclarant(@PathVariable Long id, @RequestBody Declarant updatedDeclarant) {
        return declarantService.update(id, updatedDeclarant);
    }

    @GetMapping("/{id}")
    public ModelAndView deleteDeclarant(@PathVariable Long id,Model model) {
        declarantService.delete(id);
        ModelAndView modelAndView = new ModelAndView(DECLARANT_LIST);
        model.addAttribute(DECLARANT, declarantService.getAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView(DECLARANT_LIST);
        model.addAttribute(DECLARANT, declarantService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute(DECLARANT, new Declarant());
        return new ModelAndView(DECLARANT_ADD);
    }
}
