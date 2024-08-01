package com.escooter.service_layer.entities.implementations;


import com.escooter.domain_layer.entities.User;
import com.escooter.domain_layer.entities.implementations.UserImpl;
import com.escooter.service_layer.entities.Mapper;
import com.escooter.service_layer.entities.implementations.UserInfo;

public class UserMapper implements Mapper<User, UserInfo> {


    @Override
    public User revert(final UserInfo userInfo) {
        return new UserImpl(userInfo.id(), userInfo.name(), userInfo.surname());
    }

    @Override
    public UserInfo convert(final User user) {
        return new UserInfo(user.id(), user.name(), user.surname());
    }
}
