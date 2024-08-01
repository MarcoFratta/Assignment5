package com.escooter.service_layer.entities.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import java.io.Serializable;


public class UserInfo implements Serializable {

    @Nullable
    @JsonProperty("userId")
    private final String id;
    @JsonProperty("surname")
    private final String surname;
    @JsonProperty("name")
    private final String name;

    public UserInfo(@Nullable final String id, final String name, final String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String surname() {
        return this.surname;
    }
}
