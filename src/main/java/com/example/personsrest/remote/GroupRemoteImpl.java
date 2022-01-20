package com.example.personsrest.remote;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class GroupRemoteImpl implements GroupRemote{

    private static final String BASE_URL = "api/groups";

    WebClient webClient;

    /*public GroupRemoteImpl(WebClient webClient) {
        this.webClient = WebClient.create("https://groups.edu.sensera.se/");
    }

    @Override
    public String getNameById(String groupId) {
        return webClient.get()
                .uri(BASE_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(empl), Employee.class)
                .retrieve()
                .bodyToMono(Group.class);
        ;
    }*/

    @Override
    public String getNameById(String groupId) {
        return null;
    }

    @Override
    public String createGroup(String name) {
        return null;
    }

    @Override
    public String removeGroup(String name) {
        return null;
    }

    @Value
    static class Group {
        String id;
        String name;

        @JsonCreator
        public Group(@JsonProperty("id") String id, @JsonProperty("name") String name) {
            this.id = id;
            this.name = name;
        }
    }
}
