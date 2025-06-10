package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find a user by email
    User findByEmail(String email);

    // // Method to check if a user exists by email
    // boolean existsByEmail(String email);

    // // Method to save a new user
    // User save(User user);
  
}
