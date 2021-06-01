package hotelsolution.apigateway.config;

import hotelsolution.apigateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

  @Autowired
  AuthenticationFilter filter;

  @Bean
  public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()

        .route("guest-service", r -> r.path("/api/guest/**")
            .filters(f -> f.filter(filter))
            .uri("http://localhost:8082"))

        .route("hotel-service", r -> r.path("/api/hotel/**")
            .filters(f -> f.filter(filter))
            .uri("http://localhost:8083"))

        .route("reservation-service", r -> r.path("/api/reservation/**")
            .filters(f -> f.filter(filter))
            .uri("http://localhost:8084"))

        .route("authentication-service", r -> r.path("/api/authentication/**")
            .filters(f -> f.filter(filter))
            .uri("http://localhost:8085"))

        .build();
  }

}