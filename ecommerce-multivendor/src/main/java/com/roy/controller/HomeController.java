package com.roy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roy.response.ApiResponse;

@RestController
public class HomeController {


  @GetMapping("/")
  public ApiResponse homeControllerHandler() {
    ApiResponse response = new ApiResponse();
    response.setMessage("Welcome to the E-commerce Multivendor Platform!");
    response.setSuccess(true);
    return response;
  }

}
