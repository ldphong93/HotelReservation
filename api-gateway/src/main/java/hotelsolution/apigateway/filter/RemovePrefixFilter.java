package hotelsolution.apigateway.filter;


import hotelsolution.apigateway.filter.RemovePrefixFilter.Config;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "[RemovePrefixFilter]")
public class RemovePrefixFilter extends AbstractGatewayFilterFactory<Config> {

  public RemovePrefixFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return ((exchange, chain) -> {

      if(Objects.isNull(config.getPrefix())) {
        log.info("prefix null");
        return chain.filter(exchange);
      }

      ServerHttpRequest request = exchange.getRequest();
      String path = request.getURI().getRawPath();

      ServerWebExchangeUtils.addOriginalRequestUrl(exchange, request.getURI());

      if(path.equals(config.getPrefix())) {
        path = "/";
      }
      else if(path.startsWith(config.getPrefix())) {
        path = path.replaceAll(config.getPrefix(), StringUtils.EMPTY);
      }

      ServerHttpRequest newRequest = request.mutate().path(path).build();
      exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());

      return chain.filter(exchange.mutate().request(newRequest).build());
    });
  }

  public static class Config {
    private String prefix;

    public Config(String prefix){
      this.prefix = prefix;
    }

    public String getPrefix() {
      return prefix;
    }

    public void setPrefix(String prefix) {
      this.prefix = prefix;
    }
  }

}

