package hotelsolution.reservationservice.model.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RoomDto {

  private final BigInteger id;
  private final String roomNumber;
  private final BigInteger roomTypeId;
  private final BigInteger hotelId;
}
