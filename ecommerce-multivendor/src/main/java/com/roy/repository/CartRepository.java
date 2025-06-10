package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

  
} 
