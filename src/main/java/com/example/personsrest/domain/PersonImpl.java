package com.example.personsrest.domain;

import lombok.Data;

import java.util.List;

@Data
public class PersonImpl implements Person{

    String id;
    String name;
    String city;
    int age;
    List<String> groups;

    public PersonImpl(String id, String name, String city, int age) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public PersonImpl(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public void setAge(int age) {

    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public void setCity(String city) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setActive(boolean active) {

    }

    @Override
    public List<String> getGroups() {
        return null;
    }

    @Override
    public void addGroup(String groupId) {

    }

    @Override
    public void removeGroup(String groupId) {

    }
}
