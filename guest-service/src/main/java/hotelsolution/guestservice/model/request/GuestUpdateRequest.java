package hotelsolution.guestservice.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuestUpdateRequest {

  @NotBlank(message = "Password is required.")
  private String password;

  @NotBlank(message = "Name is required.")
  private String name;

}
