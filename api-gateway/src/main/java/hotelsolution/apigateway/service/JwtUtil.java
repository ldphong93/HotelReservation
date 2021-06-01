package hotelsolution.apigateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private String SECRET_KEY = "Phong_Le";

  public Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(String token) {
    return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
  }

  public boolean isInvalid(String token) {
    return this.isTokenExpired(token);
  }

}