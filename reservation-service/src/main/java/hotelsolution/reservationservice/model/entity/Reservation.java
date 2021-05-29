package hotelsolution.reservationservice.model.entity;

import hotelsolution.reservationservice.model.dto.ReservationDto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "reservation", schema = "public")
public class Reservation {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Column(name = "hotel_id")
  private BigInteger hotelId;

  @Column(name = "guest_id")
  private BigInteger guestId;

  @Column(name = "room_id")
  private BigInteger roomId;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @Column(name = "rent_fee")
  private BigDecimal rentFee;

  @Column(name = "created_time")
  private Instant createdTime;

  public ReservationDto toDto() {
    return ReservationDto.builder()
        .id(this.id)
        .hotelId(this.hotelId)
        .guestId(this.guestId)
        .roomId(this.roomId)
        .startDate(this.startDate)
        .endDate(this.endDate)
        .rentFee(this.rentFee)
        .build();
  }
}
