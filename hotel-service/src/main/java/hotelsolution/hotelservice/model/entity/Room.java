package hotelsolution.hotelservice.model.entity;

import hotelsolution.hotelservice.model.dto.RoomDto;
import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "room_type_id", referencedColumnName = "id")
  private RoomType roomType;

  @ManyToOne
  @JoinColumn(name = "hotel_id", nullable = false)
  private Hotel hotel;

  public RoomDto toDto() {
    return RoomDto.builder()
        .id(this.getId())
        .roomNumber(this.getRoomNumber())
        .roomTypeId(this.getRoomType().getId())
        .hotelId(this.getHotel().getId())
        .build();
  }

}
