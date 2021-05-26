package hotelsolution.hotelservice.model.request;

import hotelsolution.hotelservice.enums.RoomStatus;
import java.math.BigInteger;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class RoomRequest {

  private String roomNumber;

  @Enumerated(EnumType.STRING)
  private RoomStatus roomStatus;

  private BigInteger roomTypeId;

  private BigInteger hotelId;
}