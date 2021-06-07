package hotelsolution.apigateway.filter;

import hotelsolution.apigateway.config.RouterValidator;
import hotelsolution.apigateway.filter.CustomFilter2.Config;
import hotelsolution.apigateway.service.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
@Slf4j
public class CustomFilter2 extends AbstractGatewayFilterFactory<Config> {

  @Autowired
  private RouterValidator routerValidator;

  @Autowired
  private JwtUtil jwtUtil;

  public CustomFilter2() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (((exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();

      if (routerValidator.isSecured.test(request)) {
        if (this.isAuthMissing(request))
          return this.onError(exchange, HttpStatus.UNAUTHORIZED);

        final String token = this.getAuthHeader(request);

        if (jwtUtil.isInvalid(token))
          return this.onError(exchange, HttpStatus.UNAUTHORIZED);

        this.populateRequestWithHeaders(exchange, token);
      }
      return chain.filter(exchange);
    }));
  }

  private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
    Claims claims = jwtUtil.getAllClaimsFromToken(token);
    exchange.getRequest().mutate()
        .header("id", String.valueOf(claims.get("id")))
        .header("role", String.valueOf(claims.get("role")))
        .build();
  }

  private boolean isAuthMissing(ServerHttpRequest request) {
    return !request.getHeaders().containsKey("Authorization");
  }

  private String getAuthHeader(ServerHttpRequest request) {
    return request.getHeaders().getOrEmpty("Authorization").get(0);
  }

  private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(httpStatus);
    return response.setComplete();
  }

  @Data
  public static class Config {
    private String[] ignorePaths = {"/api/authentication"};
  }
}
