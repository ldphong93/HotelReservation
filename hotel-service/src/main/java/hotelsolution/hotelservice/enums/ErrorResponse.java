package hotelsolution.hotelservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorResponse {

  INVALID_INPUT_EXCEPTION("6000", HttpStatus.BAD_REQUEST, "Invalid hotel input"),
  EXISTED_HOTEL_NAME_EXCEPTION("6001", HttpStatus.CONFLICT, "This hotel name is already used"),
  HOTEL_NOT_FOUND_EXCEPTION("6002", HttpStatus.NOT_FOUND, "Hotel not found."),
  UNHANDLED_EXCEPTION("6003", HttpStatus.INTERNAL_SERVER_ERROR, "Unhandled exception.");

  private final String errorCode;

  private final HttpStatus httpStatus;

  private final String message;

}

