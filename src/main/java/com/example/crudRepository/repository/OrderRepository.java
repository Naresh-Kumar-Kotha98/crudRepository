package com.example.crudRepository.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.crudRepository.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
  
}