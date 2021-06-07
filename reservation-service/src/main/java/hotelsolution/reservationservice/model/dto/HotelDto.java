package hotelsolution.reservationservice.model.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HotelDto {

  private final BigInteger id;
  private final String name;
  private final String starRating;
}
