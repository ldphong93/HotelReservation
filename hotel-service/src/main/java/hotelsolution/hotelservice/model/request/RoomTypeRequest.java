package hotelsolution.hotelservice.model.request;

import hotelsolution.hotelservice.enums.RoomTypeName;
import java.math.BigDecimal;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class RoomTypeRequest {

  @Enumerated(EnumType.STRING)
  private RoomTypeName name;

  private int capacity;

  private BigDecimal rentFee;
}