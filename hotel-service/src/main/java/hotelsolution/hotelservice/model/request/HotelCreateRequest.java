package hotelsolution.hotelservice.model.request;

import hotelsolution.hotelservice.model.entity.Address;
import hotelsolution.hotelservice.model.entity.Room;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelCreateRequest {

  @NotBlank(message = "Name is required.")
  private String name;

  @NotBlank(message = "Star rating is required.")
  private String starRating;

  private Address address;

  private int totalRoom;

  private List<Room> listRoom;

}
