package hotelsolution.hotelservice.model.request;

import hotelsolution.hotelservice.model.entity.Address;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class AddressRequest {

  @NotBlank(message = "Address can not be blank")
  private String fullAddress;
  private String city;
  private String country;

  public Address toAddressDao() {

    return Address.builder()
        .fullAddress(this.getFullAddress())
        .city(this.getCity())
        .country(this.getCountry())
        .build();
  }
}
