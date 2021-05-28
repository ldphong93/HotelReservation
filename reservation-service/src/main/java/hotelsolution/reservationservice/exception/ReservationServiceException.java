package hotelsolution.reservationservice.exception;

import hotelsolution.reservationservice.enums.ErrorResponse;
import lombok.Getter;

@Getter
public class ReservationServiceException extends RuntimeException{
  private final ErrorResponse errorResponse;

  public ReservationServiceException(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

}
