package hotelsolution.guestservice.model.entity;

import hotelsolution.guestservice.model.dto.GuestDto;
import java.math.BigInteger;
import java.time.Instant;
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
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guest", schema = "public")
public class Guest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @Column(name = "user_name", updatable = false, nullable = false)
  private String userName;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "name")
  private String name;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @CreationTimestamp
  @Column(name = "created_date_time", updatable = false, nullable = false)
  private Instant createdDateTime;

  public GuestDto toGuestDto() {
    return GuestDto.builder()
        .id(this.getId())
        .userName(this.getUserName())
        .password(this.getPassword())
        .name(this.getName())
        .phoneNumber(this.getPhoneNumber())
        .build();
  }
}
