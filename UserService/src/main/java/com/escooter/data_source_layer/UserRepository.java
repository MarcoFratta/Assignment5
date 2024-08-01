package com.escooter.data_source_layer;


import com.escooter.domain_layer.entities.implementations.UserImpl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserImpl, String> {
}
