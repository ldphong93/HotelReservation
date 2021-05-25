package hotelsolution.hotelservice.exception;

import hotelsolution.hotelservice.enums.ErrorResponse;
import lombok.Getter;

@Getter
public class HotelServiceException extends RuntimeException {
  private final ErrorResponse errorResponse;

  public HotelServiceException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

}
