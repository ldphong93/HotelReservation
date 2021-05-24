package hotelsolution.guestservice.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuestCreateRequest {

  @NotBlank(message = "User name is required.")
  private String userName;

  @NotBlank(message = "Password is required.")
  private String password;

  @NotBlank(message = "Name is required.")
  private String name;

  @Size(min = 8, max = 12)
  @PositiveOrZero(message = "Only allow digits")
  private String phoneNumber;

}
