package hotelsolution.hotelservice.model.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class HotelUpdateRequest {

  @NotBlank(message = "Name is required.")
  private String name;

  private String starRating;

  private AddressRequest addressRequest;
  private List<RoomRequest> roomRequestList;

}