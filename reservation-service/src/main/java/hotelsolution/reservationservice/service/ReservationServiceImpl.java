package hotelsolution.reservationservice.service;

import hotelsolution.reservationservice.enums.ErrorResponse;
import hotelsolution.reservationservice.exception.ReservationServiceException;
import hotelsolution.reservationservice.feignClient.HotelServiceFeignClient;
import hotelsolution.reservationservice.model.dto.HotelDto;
import hotelsolution.reservationservice.model.dto.ReservationDto;
import hotelsolution.reservationservice.model.dto.RoomDto;
import hotelsolution.reservationservice.model.entity.Reservation;
import hotelsolution.reservationservice.model.request.CreateReservationRequest;
import hotelsolution.reservationservice.repository.ReservationRepository;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Slf4j(topic = "[ReservationServiceImpl]")
@Service
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepository reservationRepository;
  private final HotelServiceFeignClient hotelServiceFeignClient;

  @Autowired
  public ReservationServiceImpl(
      ReservationRepository reservationRepository,
      HotelServiceFeignClient hotelServiceFeignClient) {
    this.reservationRepository = reservationRepository;
    this.hotelServiceFeignClient = hotelServiceFeignClient;
  }

  @Override
  @CircuitBreaker(name = "circuit_1", fallbackMethod = "fallBack1")
  public HotelDto findHotelByName(String hotelName) {
    log.info("Retrieve hotel by name [{}]", hotelName);
    ResponseEntity<HotelDto> responses = hotelServiceFeignClient.retrieveHotelByName(hotelName);
    return responses.getBody();
  }

  public HotelDto fallBack1(String hotelName, Exception exception){
    return HotelDto.builder()
        .name("server is down")
        .build();
  }



    @Override
  public List<HotelDto> findAllHotelByCity(String hotelCity) {
    log.info("Retrieve all hotel by city [{}]", hotelCity);
    ResponseEntity<List<HotelDto>> responses = hotelServiceFeignClient
        .retrieveHotelByCity(hotelCity);
    return responses.getBody();
  }

  @Override
  public List<RoomDto> findAllRoomByHotelId(BigInteger hotelId) {
    log.info("Retrieve all room by hotel id [{}]", hotelId);
    ResponseEntity<List<RoomDto>> responses = hotelServiceFeignClient
        .retrieveAllRoomByHotelId(hotelId);
    return responses.getBody();
  }

  @Override
  public ReservationDto create(CreateReservationRequest request) {
    log.info("Create reservation");

    checkAvailability(request.getRoomId(), request.getStartDate(), request.getEndDate());

    Reservation reservation = Reservation.builder()
        .hotelId(request.getHotelId())
        .guestId(request.getGuestId())
        .roomId(request.getRoomId())
        .startDate(request.getStartDate())
        .rentFee(request.getRentFee())
        .endDate(request.getEndDate())
        .createdTime(Instant.now())
        .build();
    Reservation savedReservation = reservationRepository.save(reservation);

    return savedReservation.toDto();

  }

  @Override
  public List<ReservationDto> findAllReservationByGuestId(BigInteger guestId) {
    log.info("Retrieve all reservation by guest with id [{}].", guestId);

    return reservationRepository
        .findAllByGuestId(guestId)
        .stream()
        .map(Reservation::toDto)
        .collect(Collectors.toList());
  }

  private void checkAvailability(BigInteger roomId, LocalDate startDate, LocalDate endDate) {
    log.info("Checking if room is available, with room id [{}]", roomId);

    List<Reservation> checkList = reservationRepository.findAllByRoomId(roomId);

    for (Reservation reserved : checkList) {
      if (reserved.getEndDate().compareTo(startDate) > 0) {
        throw new ReservationServiceException(ErrorResponse.ROOM_UNAVAILABLE_EXCEPTION);
      }
    }
  }

}
