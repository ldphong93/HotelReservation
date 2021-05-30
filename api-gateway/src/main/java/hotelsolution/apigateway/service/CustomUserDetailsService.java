package hotelsolution.apigateway.service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) {
    return new org.springframework.security.core.userdetails.User("user2","password1", new ArrayList<>());
  }
}