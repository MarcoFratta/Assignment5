package com.escooter.domain_layer.entities.implementations;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User  {

    @Id
    private  String id;

    private  String name;
    private  String surname;

    public User(final String id, final String name, final String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }


    public String getSurname() {
        return this.surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", surname='" + this.surname + '\'' +
                '}';
    }
}