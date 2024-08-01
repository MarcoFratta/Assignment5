package com.escooter.service_layer;

import com.escooter.data_source_layer.UserRepository;
import com.escooter.domain_layer.entities.User;
import com.escooter.domain_layer.entities.implementations.UserImpl;
import com.escooter.service_layer.entities.Mapper;
import com.escooter.service_layer.entities.implementations.UserMapper;
import com.escooter.service_layer.entities.implementations.UserInfo;
import com.escooter.service_layer.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceImpl implements ServicePort {

    private final UserRepository userRepository;
    private final Mapper<User, UserInfo> mapper = new UserMapper();

    @Autowired
    public ServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserInfo registerNewUser(final String name, final String surname) {
            final var u = new UserImpl(null, name, surname);
            final var savedUser = this.userRepository.insert(u);
            return this.mapper.convert(savedUser);
    }

    @Override
    public UserInfo getUser(final String id) throws UserNotFoundException {
        final Optional<UserImpl> user = this.userRepository.findById(id);
        final User u = user.orElseThrow(UserNotFoundException::new);
        return this.mapper.convert(u);
    }
}
