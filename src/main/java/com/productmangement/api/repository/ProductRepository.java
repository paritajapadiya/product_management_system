package com.productmangement.api.repository;

import com.productmangement.api.model.EntityProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<EntityProduct, String> {
}
