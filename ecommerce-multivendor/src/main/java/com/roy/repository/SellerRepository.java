package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
  Seller findByEmail(String email);
}
