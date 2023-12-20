package com.exam.exam.controller;
import com.exam.exam.entity.Declarant;
import com.exam.exam.service.DeclarantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/declarants")
public class DeclarantController {
    private final DeclarantService declarantService;

    @Autowired
    public DeclarantController(DeclarantService declarantService) {
        this.declarantService = declarantService;
    }

    @PostMapping
    public ModelAndView createDeclarant(@ModelAttribute Declarant declarant,Model model) {
        declarantService.create(declarant);
        model.addAttribute("declarants", declarantService.getAll());
        ModelAndView modelAndView = new ModelAndView("Declarant/list");
        return modelAndView;
    }

    @PutMapping("/{id}")
    public Declarant updateDeclarant(@PathVariable Long id, @RequestBody Declarant updatedDeclarant) {
        return declarantService.update(id, updatedDeclarant);
    }

    @GetMapping("/{id}")
    public ModelAndView deleteDeclarant(@PathVariable Long id,Model model) {
        declarantService.delete(id);
        ModelAndView modelAndView = new ModelAndView("Declarant/list");
        model.addAttribute("declarants", declarantService.getAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView("Declarant/list");
        model.addAttribute("declarants", declarantService.getAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("declarant", new Declarant());
        ModelAndView modelAndView = new ModelAndView("Declarant/add");
        return modelAndView;
    }
}
