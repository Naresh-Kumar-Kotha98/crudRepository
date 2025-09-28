package com.example.crudRepository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.crudRepository.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	Page<Product> findAll(Pageable pageable);
}
