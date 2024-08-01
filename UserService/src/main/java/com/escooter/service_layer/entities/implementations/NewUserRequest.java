package com.escooter.service_layer.entities.implementations;

public record NewUserRequest(String name, String surname) {
    @Override
    public String toString() {
        return "NewUserRequest{" +
                "name='" + this.name + '\'' +
                ", surname='" + this.surname + '\'' +
                '}';
    }
}
