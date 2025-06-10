package com.roy.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roy.config.JwtProvider;
import com.roy.domain.USER_ROLE;
import com.roy.model.Cart;
import com.roy.model.User;
import com.roy.model.VerificationCode;
import com.roy.repository.CartRepository;
import com.roy.repository.UserRepository;
import com.roy.repository.VerificationCodeRepository;
import com.roy.response.AuthResponse;
import com.roy.response.LoginRequest;
import com.roy.response.SignupRequest;
import com.roy.service.AuthService;
import com.roy.service.EmailService;
import com.roy.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final CartRepository cartRepository;
  private final JwtProvider jwtProvider;
  private final VerificationCodeRepository verificationCodeRepository;
  private final EmailService emailService;
  private final CustomUserServiceImpl customUserService;

  @Override
  public String createUser(SignupRequest req) throws Exception {
    // String SIGNING_PREFIX="signing_";

    VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
    if(verificationCode==null || !verificationCode.getOtp().equals(req.getOtp())) throw new Exception("Wrong otp...");

    User user = userRepository.findByEmail(req.getEmail());

    if(user == null){
      User createdUser = new User();
      createdUser.setEmail(req.getEmail());
      createdUser.setFullName(req.getFullName());
      createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
      createdUser.setMobile("123456789");
      createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

      user = userRepository.save(createdUser);

      Cart cart = new Cart();
      cart.setUser(user);
      cartRepository.save(cart);
      
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
    Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return jwtProvider.generateToken(authentication);


  }

  @Override
  public void sentLoginOtp(String email) throws Exception{
    String SIGNING_PREFIX = "signin_";
    if(email.startsWith(SIGNING_PREFIX)){
      email = email.substring(SIGNING_PREFIX.length());

      User user = userRepository.findByEmail(email);
      if(user == null){
        throw new Exception("User not exist with provided email");
      }
    }

    VerificationCode isExist = verificationCodeRepository.findByEmail(email);

    if(isExist != null){
      verificationCodeRepository.delete(isExist);
    }

    String otp = OtpUtil.generatedOtp();
    VerificationCode verificationCode = new VerificationCode();
    verificationCode.setOtp(otp);
    verificationCode.setEmail(email);
    verificationCodeRepository.save(verificationCode);

    String subject = "R Bazaar Login/SignUp otp";
    String text = "Your Login/SignUp OTP is - " + otp;
    emailService.sendVerificationOtpEmail(email, otp, subject, text);

  }

  @Override
  public AuthResponse signing(LoginRequest req) {
    String username = req.getEmail();
    String otp = req.getOtp();

    Authentication authentication = authenticate(username, otp);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtProvider.generateToken(authentication);

    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(token);
    authResponse.setMessage("Login Success!!");
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

    authResponse.setRole(USER_ROLE.valueOf(roleName));


    return authResponse;
  }

  private Authentication authenticate(String username, String otp) {  // to verify otp

    UserDetails userDetails = customUserService.loadUserByUsername(username);

    if(userDetails == null)
      throw new BadCredentialsException("Invalid username !!");

    VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
    if(verificationCode == null || !verificationCode.getOtp().equals(otp))
      throw new BadCredentialsException("Wrong OTP!!");


    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
  
}
