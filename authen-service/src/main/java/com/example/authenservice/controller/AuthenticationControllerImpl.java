package com.example.authenservice.controller;

import com.example.authenservice.form.AuthRequest;
import com.example.authenservice.form.AuthResponse;
import com.example.authenservice.service.UserService;
import com.example.authenservice.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "[AuthenticationControllerImpl]")
@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userDetailService;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public ResponseEntity<String> greeting() {
    log.info("Show information");

    return ResponseEntity.ok("Greeting from Authentication Portal \n "
        + "Please route to /login for authentication of your account!!!");
  }

  @Override
  public ResponseEntity<AuthResponse> login(
      @RequestBody AuthRequest authRequest) throws Exception {
    log.info("Creating authentication token for username [{}].", authRequest.getUsername());

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
              authRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Invalid Username or Password", e);
    }

    String jwt = jwtUtil.generateToken(authRequest.getUsername());

    return ResponseEntity.ok(new AuthResponse(jwt));
  }


}
