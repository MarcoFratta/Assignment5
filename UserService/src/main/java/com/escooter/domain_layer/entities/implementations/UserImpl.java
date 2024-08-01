package com.escooter.domain_layer.entities.implementations;

import com.escooter.domain_layer.entities.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserImpl implements User {

    @Id
    private final String id;

    private final String name;
    private final String surname;

    public UserImpl(final String id, final String name, final String surname) {
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