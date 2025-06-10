package com.roy.service;

import org.springframework.stereotype.Service;

import com.roy.response.AuthResponse;
import com.roy.response.LoginRequest;
import com.roy.response.SignupRequest;

@Service
public interface AuthService {
  
  String createUser(SignupRequest req) throws Exception;

  AuthResponse  signing(LoginRequest req);

  void sentLoginOtp(String email) throws Exception;
  
}
