package com.example.personsrest;

import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonDTO;
import com.example.personsrest.domain.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    /*@PostMapping
    public Person create() {return personService.create();}*/

    private PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName(), person.getCity(), person.getAge(), person.getGroups());
    }
}
