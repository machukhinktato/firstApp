package it.machukhinktato.spring.controllers;


import it.machukhinktato.spring.models.Person;
import it.machukhinktato.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons")
    public String showPersons(Model model) {

//        Optional<Person> person = personRepository.findById(1L);
        Iterable<Person> persons = personRepository.findAll();
//        model.addAttribute("persons", person.orElse(new Person()));
        model.addAttribute("persons", persons);

        return "persons";
    }


    @GetMapping("/persons/new")
    public String formPerson(Model model) {
        return "new-persons";
    }

    @PostMapping("/persons/new")
    public String postPerson(@RequestParam String name, @RequestParam String secondName) {
        Person personModel = new Person(name, secondName);
        personRepository.save(personModel);
        return "redirect:/persons";
    }

    @GetMapping("/person/{id}")
    public String getPersonDetail(@PathVariable(value = "id") long id, Model model) {
//        Optional<Person> person = personRepository.findById(id);
//        System.out.println(person.toString());
//        AbstractList<Person> res = new ArrayList<>();
//        person.ifPresent(res::add);
//        model.addAttribute("person", res);
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return "redirect:/persons";
        }
        model.addAttribute("person", person);
        return "person-detail";
    }


    @GetMapping("/person/{id}/edit")
    public String personEdit(@PathVariable(value = "id") long id, Model model) {
        Person person = personRepository.findById(id).orElse(null);
        model.addAttribute("person", person);
        return "person-edit";
    }

    @PostMapping("/person/{id}/edit")
    public String postPersonUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String secondName) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return "redirect:/persons";
        }
        person.setName(name);
        person.setSecond_name(secondName);
        personRepository.save(person);
        return "redirect:/persons";
    }

    @PostMapping("/person/{id}/remove")
    public String postPersonRemove(@PathVariable(value = "id") long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return "redirect:/persons";
        }
        personRepository.delete(person);
        return "redirect:/persons";
    }


}
