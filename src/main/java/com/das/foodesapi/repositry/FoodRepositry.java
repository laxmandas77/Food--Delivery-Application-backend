package com.das.foodesapi.repositry;

import com.das.foodesapi.entity.FoodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepositry extends MongoRepository<FoodEntity,String> {
}
