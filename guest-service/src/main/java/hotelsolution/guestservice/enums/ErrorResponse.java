package hotelsolution.guestservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorResponse {

  INVALID_INPUT_EXCEPTION("5000", HttpStatus.BAD_REQUEST, "Invalid guest input"),
  EXISTED_USERNAME_EXCEPTION("5001", HttpStatus.CONFLICT, "This username is already used"),
  GUEST_NOT_FOUND_EXCEPTION("5002", HttpStatus.NOT_FOUND, "Guest not found."),
  UNHANDLED_EXCEPTION("5003", HttpStatus.INTERNAL_SERVER_ERROR, "Unhandled exception.");

  private final String errorCode;

  private final HttpStatus httpStatus;

  private final String message;

}

