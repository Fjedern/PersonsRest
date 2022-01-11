package com.example.personsrest;

import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonImpl;
import com.example.personsrest.domain.PersonRepository;
import com.example.personsrest.remote.GroupRemote;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private GroupRemote groupRemote;

    /*public Person create(Person person) {
        PersonImpl createdPerson = new PersonImpl("1", "filip", "22", "malmo");
        return createdPerson;
    }*/

    public Person create() {
        PersonImpl createdPerson = new PersonImpl("1", "filip", "22", "malmo");
        return createdPerson;
    }
}
