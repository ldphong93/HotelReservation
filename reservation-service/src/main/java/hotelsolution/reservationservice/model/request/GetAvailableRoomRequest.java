package hotelsolution.reservationservice.model.request;

import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAvailableRoomRequest {

  private final BigInteger hotelId;
  private final LocalDate startDate;
  private final LocalDate endDate;
}
