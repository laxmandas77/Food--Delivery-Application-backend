package com.das.foodesapi.repositry;

import com.das.foodesapi.entity.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepositry extends MongoRepository<CartEntity,String> {

    Optional<CartEntity> findByUserId(String userId);

    void deleteByUserId(String Id);
}
