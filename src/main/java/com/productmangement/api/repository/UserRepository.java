package com.productmangement.api.repository;

import com.productmangement.api.model.EntityProduct;
import com.productmangement.api.model.EntityUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<EntityUser, String> {
    Optional<EntityUser> findByUsername(String username);
}
