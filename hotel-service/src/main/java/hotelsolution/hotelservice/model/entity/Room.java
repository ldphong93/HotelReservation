package hotelsolution.hotelservice.model.entity;

import hotelsolution.hotelservice.enums.RoomStatus;
import hotelsolution.hotelservice.enums.RoomType;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room", schema = "public")
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Column(name = "room_number")
  private String roomNumber;

  @Column(name = "room_type")
  @Enumerated(EnumType.STRING)
  private RoomType roomType;

  @Enumerated(EnumType.STRING)
  @Column(name = "room_status")
  private RoomStatus roomStatus;

  @Column(name = "price")
  private BigDecimal price;

}
