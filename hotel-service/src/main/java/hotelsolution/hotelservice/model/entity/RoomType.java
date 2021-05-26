package hotelsolution.hotelservice.model.entity;

import hotelsolution.hotelservice.enums.RoomTypeName;
import hotelsolution.hotelservice.model.dto.RoomTypeDto;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roomType", schema = "public")
public class RoomType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Enumerated(EnumType.STRING)
  @Column(name = "name")
  private RoomTypeName name;

  @Column(name = "capacity")
  private int capacity;

  @Column(name = "rent_fee")
  private BigDecimal rentFee;

  @OneToOne(mappedBy = "roomType")
  private Room room;

  public RoomTypeDto toDto() {
    return RoomTypeDto.builder()
        .id(this.getId())
        .name(this.getName())
        .capacity(this.getCapacity())
        .rentFee(this.getRentFee())
        .build();
  }
}
