package hotelsolution.reservationservice.model.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RoomDto {

  private BigInteger id;
  private String roomNumber;
  private BigInteger roomTypeId;
  private BigInteger hotelId;
}
