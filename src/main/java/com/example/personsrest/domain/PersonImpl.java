package com.example.personsrest.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PersonImpl implements Person{

    private String id;
    private String name;
    private String city;
    private int age;
    private List<String> groups;

    public PersonImpl(String name, String city, int age, List<String> groups) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.city = city;
        this.age = age;
        this.groups = groups;
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
        return this.groups == null ? List.of() : this.groups;
    }

    @Override
    public void addGroup(String groupId) {
        this.groups.add(groupId);
    }

    @Override
    public void removeGroup(String groupId) {
        this.groups.removeIf(groupId::equalsIgnoreCase);
    }
}