package hotelsolution.hotelservice.model.request;

import hotelsolution.hotelservice.enums.RoomStatus;
import hotelsolution.hotelservice.enums.RoomType;
import hotelsolution.hotelservice.model.entity.Room;
import java.math.BigDecimal;
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
  private RoomType roomType;

  @Enumerated(EnumType.STRING)
  private RoomStatus roomStatus;

  private BigDecimal price;

  public Room toRoomDao() {

    return Room.builder()
        .roomNumber(this.getRoomNumber())
        .roomType(this.getRoomType())
        .roomStatus(this.getRoomStatus())
        .price(this.getPrice())
        .build();
  }
}