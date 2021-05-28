package hotelsolution.reservationservice.model.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class ReservationDto {

  private BigInteger id;

  private BigInteger hotelId;

  private BigInteger guestId;

  private BigInteger roomId;

  private LocalDate startDate;

  private LocalDate endDate;

  private BigDecimal rentFee;

}
