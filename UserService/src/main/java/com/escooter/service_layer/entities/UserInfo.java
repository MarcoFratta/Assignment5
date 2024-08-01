package com.escooter.service_layer.entities;


import com.escooter.service_layer.entities.implementations.UserInfoImpl;

public interface UserInfo {

    static UserInfo from(final String id, final String name, final String surname) {
        return new UserInfoImpl(id, name, surname);
    }

    String id();

    String name();

    String surname();
}
