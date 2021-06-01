package com.example.authenservice.form;

import lombok.Getter;

@Getter
public class AuthResponse {

  private final String jwt;

  public AuthResponse(String jwt) {
    this.jwt = jwt;
  }
}
