package hotelsolution.hotelservice.model.request;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class RoomRequest {

  private String roomNumber;

  private BigInteger roomTypeId;

  private BigInteger hotelId;
}