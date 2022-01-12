package com.example.personsrest;

import com.example.personsrest.domain.CreatePerson;
import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonDTO;
import com.example.personsrest.domain.PersonRepositoryImpl;
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

    private PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName(), person.getCity(), person.getAge(), person.getGroups());
    }
}
