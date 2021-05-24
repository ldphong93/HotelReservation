package hotelsolution.hotelservice.exception;

import hotelsolution.hotelservice.enums.ErrorResponse;
import lombok.Getter;

@Getter
public class GuestServiceException extends RuntimeException {
  private final ErrorResponse errorResponse;

  public GuestServiceException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

}
