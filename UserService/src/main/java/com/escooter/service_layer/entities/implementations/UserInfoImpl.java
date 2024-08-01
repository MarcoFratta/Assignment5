package com.escooter.service_layer.entities.implementations;

import com.escooter.service_layer.entities.UserInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class UserInfoImpl implements UserInfo, Serializable {

    @JsonProperty("userId")
    private final String id;
    @JsonProperty("surname")
    private final String surname;
    @JsonProperty("name")
    private final String name;

    public UserInfoImpl(final String id, final String name, final String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String surname() {
        return this.surname;
    }
}
