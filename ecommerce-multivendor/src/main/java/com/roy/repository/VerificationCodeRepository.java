package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
  VerificationCode findByEmail(String email);
  
}
