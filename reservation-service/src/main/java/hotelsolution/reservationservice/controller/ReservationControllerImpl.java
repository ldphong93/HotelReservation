package hotelsolution.reservationservice.controller;

import hotelsolution.reservationservice.model.dto.HotelDto;
import hotelsolution.reservationservice.model.dto.ReservationDto;
import hotelsolution.reservationservice.model.dto.RoomDto;
import hotelsolution.reservationservice.model.entity.ResponseAPI;
import hotelsolution.reservationservice.model.request.CreateReservationRequest;
import hotelsolution.reservationservice.service.ReservationService;
import java.math.BigInteger;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j(topic = "[ReservationControllerImpl]")
@RestController
public class ReservationControllerImpl implements ReservationController {

  private final ReservationService reservationService;

  @Autowired
  public ReservationControllerImpl(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @Override
  public ResponseAPI retrieveHotelByNameAPI(String hotelName) {

    log.info("Retrieve hotel by name [{}]", hotelName);
    return ResponseAPI.builder()
        .data(reservationService.findHotelByName(hotelName))
        .code(HttpStatus.OK)
        .message("hotel found")
        .build();
  }

  @Override
  public ResponseEntity<HotelDto> retrieveHotelByName(String hotelName) {

    log.info("Retrieve hotel by name [{}]", hotelName);
    return ResponseEntity.ok(reservationService.findHotelByName(hotelName));
  }

  @Override
  public ResponseEntity<List<HotelDto>> retrieveHotelByCity(String hotelCity) {

    log.info("Retrieve all hotel by city [{}]", hotelCity);
    return ResponseEntity.ok(reservationService.findAllHotelByCity(hotelCity));
  }

  @Override
  public ResponseEntity<List<RoomDto>> retrieveAllRoomByHotelId(BigInteger hotelId) {

    log.info("Retrieve all room by hotel id [{}]", hotelId);
    return ResponseEntity.ok(reservationService.findAllRoomByHotelId(hotelId));
  }

  @Override
  public ResponseEntity<ReservationDto> createReservation(CreateReservationRequest request) {

    log.info("Create reservation");
    return ResponseEntity.ok(reservationService.create(request));
  }

  @Override
  public ResponseEntity<List<ReservationDto>> retrieveAllReservationByGuestId(BigInteger guestId) {

    log.info("Retrieve all reservation by guest with id [{}].", guestId);
    return ResponseEntity.ok(reservationService.findAllReservationByGuestId(guestId));
  }

}
