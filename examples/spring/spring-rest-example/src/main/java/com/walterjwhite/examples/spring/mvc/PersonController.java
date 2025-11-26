package com.walterjwhite.examples.spring.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("people", personService.findAll());
        model.addAttribute("personForm", new Person());
        return "index";
    }

    @PostMapping("/person")
    public String addPerson(@ModelAttribute("personForm") @Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("people", personService.findAll());
            return "index";
        }
        try {
            personService.save(person);
            return "redirect:/";
        } catch (MaxCapacityExceededException ex) {
            model.addAttribute("people", personService.findAll());
            model.addAttribute("personForm", person);
            model.addAttribute("errorMessage", ex.getMessage());
            return "index";
        }
    }

    @GetMapping("/person/{id}")
    public String personDetail(@PathVariable("id") Long id, Model model) {
        return personService.findById(id)
                .map(p -> {
                    model.addAttribute("person", p);
                    return "detail";
                })
                .orElse("redirect:/");
    }

    @PostMapping("/person/{id}/delete")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deleteById(id);
        return "redirect:/";
    }
}