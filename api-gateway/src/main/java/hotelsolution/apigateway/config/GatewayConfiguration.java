package hotelsolution.apigateway.config;

import org.springframework.context.annotation.Configuration;

/**
 * For pure java gateway routing
 * At present, using dynamic routing in yml file
 */

@Configuration
public class GatewayConfiguration {
/*
  @Autowired
  PureJavaFilter filter;

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
  }*/

}