package com.example.personsrest;

import com.example.personsrest.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<PersonDTO> getAllPersons() { return personService.findAll().stream().map(this::toDTO).collect(Collectors.toList()); }

    @GetMapping("/{id}")
    public PersonDTO get(@PathVariable("id") String id) {
        return toDTO(personService.findById(id));
    }

    @PostMapping
    public PersonDTO create(@RequestBody CreatePerson createPerson) {
        return toDTO(personService.create(createPerson));
    }

    @PutMapping("/{id}")
    public PersonDTO update(@PathVariable("id") String id, @RequestBody UpdatePerson updatePerson) {
        return toDTO(
                    personService.update(
                            id,
                            updatePerson.getName(),
                            updatePerson.getCity(),
                            updatePerson.getAge()));

    }


    private PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName(), person.getCity(), person.getAge(), person.getGroups());
    }
}
