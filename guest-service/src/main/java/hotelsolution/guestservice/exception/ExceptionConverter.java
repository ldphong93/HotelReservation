package hotelsolution.guestservice.exception;

import brave.Tracer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hotelsolution.guestservice.enums.ErrorResponse;

public class ExceptionConverter {

  private final ObjectMapper mapper = new ObjectMapper();

  public JsonNode convertToJsonNode(ErrorResponse errorResponse,
      String additionError, Tracer tracer) {
    ObjectNode rootNode = mapper.createObjectNode();
    rootNode.put("errorCode", errorResponse.getErrorCode());
    rootNode.put("errorMessage",
        errorResponse.getMessage() + additionError);
    rootNode.put("traceId", tracer.currentSpan().context().traceIdString());
    return rootNode;
  }

}
