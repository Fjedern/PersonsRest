package com.example.personsrest.domain;

import lombok.Data;

@Data
public class PersonImpl implements Person{

    String getId();

    String getName();

    @Override
    public void addGroup(String groupId) {

    }

    @Override
    public void removeGroup(String groupId) {

    }
}
