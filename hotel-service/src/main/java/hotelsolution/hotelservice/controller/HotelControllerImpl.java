package hotelsolution.hotelservice.controller;

import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.dto.RoomDto;
import hotelsolution.hotelservice.model.dto.RoomTypeDto;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.model.request.RoomRequest;
import hotelsolution.hotelservice.model.request.RoomTypeRequest;
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
  public ResponseEntity<HotelDto> retrieveHotelByName(String hotelName) {
    log.info("Retrieve hotel with name [{}].", hotelName);
    return ResponseEntity.ok(hotelService.findHotelByName(hotelName));
  }

  @Override
  public ResponseEntity<List<HotelDto>> retrieveHotelByCity(String hotelCity) {
    log.info("Retrieve hotel with city [{}].", hotelCity);
    return ResponseEntity.ok(hotelService.findHotelByCity(hotelCity));
  }

  @Override
  public ResponseEntity<List<RoomDto>> retrieveAllRoomByHotelId(BigInteger hotelId) {

    log.info("Retrieve all room by hotel id [{}]", hotelId);
    return ResponseEntity.ok(hotelService.findAllRoomByHotelId(hotelId));
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

  @Override
  public ResponseEntity<RoomTypeDto> createRoomType(@Valid RoomTypeRequest roomTypeRequest) {
    log.info("Create room type with room name [{}].", roomTypeRequest.getName());
    return ResponseEntity.ok(hotelService.addRoomType(roomTypeRequest));
  }

  @Override
  public ResponseEntity<RoomDto> createRoom(@Valid RoomRequest roomRequest) {
    log.info("Create room with room number [{}]", roomRequest.getRoomNumber());
    return ResponseEntity.ok(hotelService.addRoom(roomRequest));
  }

}
