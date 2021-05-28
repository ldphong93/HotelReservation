package hotelsolution.reservationservice.model.request;

import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAvailableRoomRequest {

  private BigInteger hotelId;
  private LocalDate startDate;
  private LocalDate endDate;
}
