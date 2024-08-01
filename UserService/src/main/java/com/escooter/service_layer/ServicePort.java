package com.escooter.service_layer;



import com.escooter.service_layer.entities.implementations.UserInfo;
import com.escooter.service_layer.exceptions.UserIdAlreadyExistingException;
import com.escooter.service_layer.exceptions.UserNotFoundException;

public interface ServicePort {

    UserInfo registerNewUser(String name, String surname) throws UserIdAlreadyExistingException;

    UserInfo getUser(String id) throws UserNotFoundException;

}


