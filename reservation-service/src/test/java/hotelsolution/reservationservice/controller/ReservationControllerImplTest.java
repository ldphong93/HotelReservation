package hotelsolution.reservationservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import hotelsolution.reservationservice.feignClient.HotelServiceFeignClient;
import hotelsolution.reservationservice.model.dto.ReservationDto;
import hotelsolution.reservationservice.model.request.CreateReservationRequest;
import hotelsolution.reservationservice.service.ReservationService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ReservationControllerImplTest {

  private ReservationController reservationController;
  private ReservationService reservationService;
  private HotelServiceFeignClient hotelServiceFeignClient;

  @BeforeEach
  public void setUp() {
    reservationService = mock(ReservationService.class);
    reservationController = new ReservationControllerImpl(reservationService);
    hotelServiceFeignClient = mock(HotelServiceFeignClient.class);
  }

  @Test
  public void retrieveAllReservationByGuestId_shouldWork() {
    List<ReservationDto> reservationDtoList = new ArrayList<>();
    reservationDtoList.add(ReservationDto.builder().build());
    Mockito.when(reservationService.findAllReservationByGuestId(Mockito.any(BigInteger.class)))
        .thenReturn(reservationDtoList);
    ResponseEntity<List<ReservationDto>> responses = reservationController
        .retrieveAllReservationByGuestId(BigInteger.valueOf(Mockito.anyLong()));

    assertThat(responses.getBody()).hasSize(1);
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createReservation_whenRoomAvailable_shouldWork() {
    CreateReservationRequest request = CreateReservationRequest.builder().build();
    Mockito.when(reservationService.create(request))
        .thenReturn(ReservationDto.builder().build());
    ResponseEntity<ReservationDto> responses = reservationController.createReservation(request);

    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }
}
