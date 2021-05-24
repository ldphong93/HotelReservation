package hotelsolution.guestservice.model.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GuestDto {

  private BigInteger id;
  private String userName;
  private String password;
  private String name;
  private String phoneNumber;
}
