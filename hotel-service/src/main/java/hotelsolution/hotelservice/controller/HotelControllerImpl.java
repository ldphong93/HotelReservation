package hotelsolution.hotelservice.controller;

import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.service.HotelService;
import java.math.BigInteger;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "[HotelControllerImpl]")
@RestController
public class HotelControllerImpl implements HotelController{

  private HotelService hotelService;

  @Autowired
  public HotelControllerImpl(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @Override
  public ResponseEntity<HotelDto> retrieveHotel(BigInteger hotelId) {

    log.info("Retrieve hotel with id [{}].", hotelId);
    return ResponseEntity.ok(hotelService.findHotelById(hotelId));
  }

  @Override
  public ResponseEntity<List<HotelDto>> retrieveAllHotel() {

    log.info("Retrieve all hotel.");
    return ResponseEntity.ok(hotelService.findAllHotel());
  }

  @Override
  public ResponseEntity<HotelDto> createHotel(@Valid HotelCreateRequest request) {

    log.info("Create hotel with name [{}]", request.getName());
    return ResponseEntity.ok(hotelService.create(request));
  }

  @Override
  public ResponseEntity<HotelDto> updateHotel(BigInteger hotelId,
      @Valid HotelUpdateRequest request) {

    log.info("Update hotel with id [{}].", hotelId);
    return ResponseEntity.ok(hotelService.updateHotel(hotelId, request));
  }

  @Override
  public ResponseEntity<HotelDto> deleteHotel(BigInteger hotelId) {

    log.info("Delete hotel with id [{}].", hotelId);
    return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
  }

}
