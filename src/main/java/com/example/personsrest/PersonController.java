package com.example.personsrest;

import com.example.personsrest.domain.*;
import com.example.personsrest.remote.GroupRemote;
import lombok.AllArgsConstructor;
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
    private GroupRemote groupRemote;

    @Autowired
    public PersonController(PersonService personService, GroupRemote groupRemote) {
        this.personService = personService;
        this.groupRemote = groupRemote;
    }

    @GetMapping()
    public List<PersonDTO> getAllPersons(@RequestParam(required = false) String search) {
        if(search == null) {
            return personService.findAll().stream().map(this::toDTO).collect(Collectors.toList());
        } else {
            return personService.findAllByNameOrCityContaining(search, 0, 10)
                    .map(this::toDTO).stream().collect(Collectors.toList());
        }

    }

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        personService.delete(id);
    }

    @PutMapping("/{id}/addGroup/{groupName}")
    public PersonDTO addGroup(@PathVariable("id") String id, @PathVariable("groupName") String groupName){
        return toDTO(personService.addGroup(id, groupName));
    }

    @DeleteMapping("/{id}/removeGroup/{groupId}")
    public PersonDTO removeGroup(@PathVariable("id") String id, @PathVariable("groupId") String groupId) {
        return toDTO(personService.removeGroup(id, groupId));
    }

    private PersonDTO toDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getCity(),
                person.getAge(),
                person.getGroups().stream().map(name -> groupRemote.getNameById(name)).collect(Collectors.toList()));
    }

    private PersonDTO toDTO2(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getCity(),
                person.getAge(),
                person.getGroups().stream().map(name -> groupRemote.removeGroup(name)).collect(Collectors.toList()));
    }
}
