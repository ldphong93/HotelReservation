package com.example.authenservice.controller;

import com.example.authenservice.form.AuthRequest;
import com.example.authenservice.form.AuthResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/authentication")
public interface AuthenticationController {

  @GetMapping("/info")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Authentication information.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
      @ApiResponse(responseCode = "404", description = "Information not found", content = @Content)})
  ResponseEntity<String> greeting();

  @PostMapping("/login")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Authentication successful.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
      @ApiResponse(responseCode = "404", description = "Information not found", content = @Content)})
  ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws Exception;

}
