package hotelsolution.apigateway.controller;

import hotelsolution.apigateway.model.AuthRequest;
import hotelsolution.apigateway.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGateWayController {

  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private AuthenticationManager authenticationManager;

  @GetMapping("/")
  public String welcome() {
    return "Welcome to javatechie !!";
  }

  @PostMapping("/authenticate")
  public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
      );
    } catch (Exception ex) {
      throw new Exception("Invalid username/password");
    }
    return jwtUtil.generateToken(authRequest.getUserName());
  }
}