package com.example.personsrest.remote;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class GroupRemoteImpl implements GroupRemote{

    private static final String BASE_URL = "api/groups";

    WebClient webClient;

    @Override
    public String getNameById(String groupId) {
        return webClient.get()
                .uri(BASE_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(empl), Employee.class)
                .retrieve()
                .bodyToMono(Group.class);
        ;
    }

    @Override
    public String createGroup(String name) {
        return null;
    }

    @Override
    public String removeGroup(String name) {
        return null;
    }
}
