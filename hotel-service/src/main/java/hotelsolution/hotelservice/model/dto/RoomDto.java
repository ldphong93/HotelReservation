package hotelsolution.hotelservice.model.dto;

import hotelsolution.hotelservice.enums.RoomStatus;
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
  private RoomStatus roomStatus;
  private BigInteger roomTypeId;
  private BigInteger hotelId;
}
