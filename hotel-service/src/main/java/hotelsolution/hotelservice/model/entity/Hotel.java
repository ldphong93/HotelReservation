package hotelsolution.hotelservice.model.entity;

import hotelsolution.hotelservice.model.dto.HotelDto;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "hotel", schema = "public")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Column(name = "name", updatable = false, nullable = false)
  private String name;

  @Column(name = "star_rating")
  private String starRating;

  //@Column(name = "address")
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hotel")
  private Address address;

  @Column(name = "total_room")
  private int totalRoom;

  @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "hr_fk", referencedColumnName = "id")
  private List<Room> roomList;

  public HotelDto toHotelDto() {
    return HotelDto.builder()
        .id(this.getId())
        .name(this.getName())
        .starRating(this.getStarRating())
        .totalRoom(this.getTotalRoom())
        .build();
  }
}