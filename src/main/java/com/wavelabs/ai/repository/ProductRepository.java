package com.wavelabs.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wavelabs.ai.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
