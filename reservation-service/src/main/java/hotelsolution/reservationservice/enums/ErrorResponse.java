package hotelsolution.reservationservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorResponse {

  INVALID_INPUT_EXCEPTION("5000", HttpStatus.BAD_REQUEST, "Invalid reservation input exception."),
  ROOM_UNAVAILABLE_EXCEPTION("5001", HttpStatus.CONFLICT, "Room not available exception."),
  UNHANDLED_EXCEPTION("5003", HttpStatus.INTERNAL_SERVER_ERROR, "Unhandled exception."),
  RESERVATION_NOT_FOUND("5004", HttpStatus.NOT_FOUND, "Reservation not found exception.");

  private final String errorCode;

  private final HttpStatus httpStatus;

  private final String message;

}
