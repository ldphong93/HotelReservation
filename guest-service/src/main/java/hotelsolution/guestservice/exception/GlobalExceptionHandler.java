package hotelsolution.guestservice.exception;

import brave.Tracer;
import hotelsolution.guestservice.enums.ErrorResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j(topic = "[GlobalExceptionHandler]")
public class GlobalExceptionHandler {

  @Autowired
  private Tracer tracer;

  private final ExceptionConverter exceptionConverter = new ExceptionConverter();

  @ExceptionHandler(GuestServiceException.class)
  protected ResponseEntity<Object> handleProfileServiceException(
      GuestServiceException exception) {
    log.error("Guest Service Exception [{}]", exception.getErrorResponse().getMessage());
    return ResponseEntity.status(exception.getErrorResponse().getHttpStatus())
        .body(exceptionConverter.convertToJsonNode(exception.getErrorResponse(), StringUtils.EMPTY, tracer));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception) {
    List<String> errors = exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());

    return ResponseEntity.badRequest().body(exceptionConverter
        .convertToJsonNode(ErrorResponse.INVALID_INPUT_EXCEPTION, errors.toString(),tracer));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> unhandledExceptions(Exception exception) {

    log.error("Unhandled Exception [{}]", exception);
    ResponseEntity<Object> entity = new ResponseEntity<>(
        exceptionConverter.convertToJsonNode(
            ErrorResponse.UNHANDLED_EXCEPTION,
            StringUtils.EMPTY, tracer),
        HttpStatus.BAD_REQUEST);
    return entity;
  }

}
