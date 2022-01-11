package com.example.personsrest;

import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonImpl;
import com.example.personsrest.domain.PersonRepository;
import com.example.personsrest.remote.GroupRemote;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;
    private GroupRemote groupRemote;

    public Person create() {
        PersonImpl createdPerson = new PersonImpl("1", "filip", "malmo", 22);
        return personRepository.save(createdPerson);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(String id){
        return personRepository.findById(id).orElse(null);
    }

}
