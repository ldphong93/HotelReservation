package hotelsolution.reservationservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import hotelsolution.reservationservice.feignClient.HotelServiceFeignClient;
import hotelsolution.reservationservice.model.dto.ReservationDto;
import hotelsolution.reservationservice.model.entity.Reservation;
import hotelsolution.reservationservice.model.request.CreateReservationRequest;
import hotelsolution.reservationservice.repository.ReservationRepository;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ReservationServiceImplTest {

  private ReservationService reservationService;
  private ReservationRepository reservationRepository;
  private HotelServiceFeignClient hotelServiceFeignClient;

  @BeforeEach
  void setUp() {
    reservationRepository = Mockito.mock(ReservationRepository.class);
    hotelServiceFeignClient = Mockito.mock(HotelServiceFeignClient.class);
    reservationService = new ReservationServiceImpl(reservationRepository, hotelServiceFeignClient);
  }

  @Test
  public void findAllReservationByGuestId_thenReturnListOfReservation() {
    List<Reservation> reservationList = new ArrayList<>();
    reservationList.add(Reservation.builder().build());
    Mockito.when(reservationRepository.findAllByGuestId(Mockito.any(BigInteger.class)))
        .thenReturn(reservationList);

    List<ReservationDto> foundReservation = reservationService
        .findAllReservationByGuestId(BigInteger.valueOf(Mockito.anyLong()));

    assertThat(foundReservation)
        .hasSize(NumberUtils.INTEGER_ONE)
        .extracting(ReservationDto::getId);
  }

  @Test
  public void createReservation_whenRoomAvailable_shouldWork() {
    CreateReservationRequest request = CreateReservationRequest.builder().build();
    List<Reservation> reservationList = new ArrayList<>();
    Mockito.when(reservationRepository.findAllByRoomId(request.getRoomId()))
        .thenReturn(reservationList);
    Reservation mockReservation = Reservation.builder()
        .hotelId(request.getHotelId())
        .guestId(request.getGuestId())
        .roomId(request.getRoomId())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .createdTime(Instant.now())
        .build();
    Mockito.when(reservationRepository.save(Mockito.any(Reservation.class)))
        .thenReturn(mockReservation);

    ReservationDto savedReservationDto = reservationService.create(request);

    Assertions.assertEquals(savedReservationDto.getId(), mockReservation.getId());
  }
}