package com.roy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roy.domain.USER_ROLE;
import com.roy.model.User;
import com.roy.model.VerificationCode;
import com.roy.repository.UserRepository;
import com.roy.response.ApiResponse;
import com.roy.response.AuthResponse;
import com.roy.response.LoginRequest;
import com.roy.response.SignupRequest;
import com.roy.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepository;
  private final AuthService authService;



  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception{

    String jwt = authService.createUser(req);

    AuthResponse res = new AuthResponse();
    res.setJwt(jwt);
    res.setMessage("Register Success!!");
    res.setRole(USER_ROLE.ROLE_CUSTOMER);

    return ResponseEntity.ok(res);
  }



  @PostMapping("/signing")
  public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception{

    AuthResponse authResponse = authService.signing(req);

    return ResponseEntity.ok(authResponse);
  }



  @PostMapping("/send/login-signup-otp")
  public ResponseEntity<ApiResponse> sendOtpHandler(@RequestBody VerificationCode req) throws Exception{

    authService.sentLoginOtp(req.getEmail());

    ApiResponse res = new ApiResponse();
    res.setMessage("OTP Send Successfully!!");

    return ResponseEntity.ok(res);
  }
  
}
