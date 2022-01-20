package com.example.personsrest.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

public class PersonRepositoryImpl implements PersonRepository{

    Map<String, Person> persons = new HashMap<String, Person>();

    public PersonRepositoryImpl() {
        Person person1 = new PersonImpl("1", "Arne Anka", "malmo", 22);

        persons.put(person1.getId(), person1);
    }

    @Override
    public Optional<Person> findById(String id) {
        if (persons.containsKey(id)){
            return Optional.of(persons.get(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(persons.values());
    }

    @Override
    public Page<Person> findAllByNameContainingOrCityContaining(String name, String city, Pageable pageable) {
        List<Person> list = findAll()
                .stream()
                .filter(search -> search.getName().contains(name) || search.getCity().contains(city))
                .collect(Collectors.toList());


        Page<Person> page = new PageImpl<>(list, pageable, pageable.getPageNumber());

        return page;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Person save(Person person) {
        return persons.put(person.getId(), person);
    }

    @Override
    public void delete(String id) {
        persons.remove(id);
    }
}
