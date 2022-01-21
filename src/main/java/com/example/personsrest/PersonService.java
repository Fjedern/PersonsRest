package com.example.personsrest;

import com.example.personsrest.domain.CreatePerson;
import com.example.personsrest.domain.Person;
import com.example.personsrest.domain.PersonImpl;
import com.example.personsrest.domain.PersonRepository;
import com.example.personsrest.remote.GroupRemote;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;
    private GroupRemote groupRemote;

    public Person create(CreatePerson createPerson) {
        PersonImpl createdPerson = new PersonImpl(createPerson.getName(), createPerson.getCity(), createPerson.getAge(), new ArrayList<String>() );
        return personRepository.save(createdPerson);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(String id){
        return personRepository.findById(id).orElse(null);
    }

    public Person update(String id, String name, String city, int age) {
        Person updatePerson = personRepository.findById(id).orElse(null);
        assert updatePerson != null;
        updatePerson.setName(name);
        updatePerson.setCity(city);
        updatePerson.setAge(age);

        return personRepository.save(updatePerson);
    }

    public void delete(String id) {
        if(id != null){
            personRepository.delete(id);
        }

    }

    public Person addGroup(String id, String groupName) {
        Person person = personRepository.findById(id).orElse(null);
        String groupId = groupRemote.createGroup(groupName);
        System.out.println(groupId);
        person.addGroup(groupId);
        System.out.println(person);
        return personRepository.save(person);
    }

    public Person removeGroup(String id, String groupId) {
        Person person = personRepository.findById(id).orElse(null);
        if(groupId.length() >= 30){
            person.removeGroup(groupId);    //name
        } else {
            person.getGroups().removeIf(g -> groupRemote.getNameById(g).equals(groupId)); //id
        }

        return personRepository.save(person);
    }

    public Page<Person> findAllByNameOrCityContaining(String search, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);

        return personRepository.findAllByNameContainingOrCityContaining(search, search, page);
    }
}
