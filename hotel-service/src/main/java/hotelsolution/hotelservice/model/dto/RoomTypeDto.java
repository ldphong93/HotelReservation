package hotelsolution.hotelservice.model.dto;

import hotelsolution.hotelservice.enums.RoomTypeName;
import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RoomTypeDto {

  private BigInteger id;
  private RoomTypeName name;
  private int capacity;
  private BigDecimal rentFee;
}
