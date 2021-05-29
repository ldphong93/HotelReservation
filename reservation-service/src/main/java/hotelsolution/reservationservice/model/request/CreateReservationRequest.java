package hotelsolution.reservationservice.model.request;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class CreateReservationRequest {

  private BigInteger hotelId;

  private BigInteger guestId;

  private BigInteger roomId;

  private BigDecimal rentFee;

  private LocalDate startDate;

  private LocalDate endDate;
}
