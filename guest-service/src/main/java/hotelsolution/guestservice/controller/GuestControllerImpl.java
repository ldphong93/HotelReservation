package hotelsolution.guestservice.controller;

import hotelsolution.guestservice.model.dto.GuestDto;
import hotelsolution.guestservice.model.request.GuestCreateRequest;
import hotelsolution.guestservice.model.request.GuestUpdateRequest;
import hotelsolution.guestservice.service.GuestService;
import java.math.BigInteger;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "[GuestControllerImpl]")
@RestController
public class GuestControllerImpl implements GuestController {

  private GuestService guestService;

  @Autowired
  public GuestControllerImpl(GuestService guestService) {
    this.guestService = guestService;
  }

  @Override
  public ResponseEntity<GuestDto> retrieveGuest(BigInteger guestId) {

    log.info("Retrieve guest with id [{}].", guestId);
    return ResponseEntity.ok(guestService.findGuestById(guestId));
  }

  @Override
  public ResponseEntity<List<GuestDto>> retrieveAllGuest() {

    log.info("Retrieve all guests.");
    return ResponseEntity.ok(guestService.findAllGuest());
  }

  @Override
  public ResponseEntity<GuestDto> createGuest(@Valid GuestCreateRequest request) {

    log.info("Create guest with userName [{}]", request.getUserName());
    return ResponseEntity.ok(guestService.create(request));
  }

  @Override
  public ResponseEntity<GuestDto> updateGuest(BigInteger guestId,
      @Valid GuestUpdateRequest request) {

    log.info("Update guest with id [{}].", guestId);
    return ResponseEntity.ok(guestService.updateGuest(guestId, request));
  }

  @Override
  public ResponseEntity<GuestDto> deleteGuest(BigInteger guestId) {

    log.info("Delete guest with id [{}].", guestId);
    return ResponseEntity.ok(guestService.deleteGuest(guestId));
  }

}
