package hotelsolution.hotelservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import hotelsolution.hotelservice.exception.HotelServiceException;
import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.service.HotelService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class HotelControllerImplTest {

  private HotelController hotelController;
  private HotelService hotelService;

  @BeforeEach
  public void setUp() {
    hotelService = mock(HotelService.class);
    hotelController = new HotelControllerImpl(hotelService);
  }

  @Test
  public void findHotelById_shouldWork() {
    Mockito.when(hotelService.findHotelById(Mockito.any(BigInteger.class)))
        .thenReturn(HotelDto.builder().build());
    ResponseEntity<HotelDto> responses = hotelController
        .retrieveHotel(BigInteger.valueOf(Mockito.anyLong()));

    Assertions.assertNotNull(responses.getBody());
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void findHotelById_whenNonExisted_shouldNotWork_andThrowException() {
    Mockito.when(hotelService.findHotelById(Mockito.any(BigInteger.class)))
        .thenThrow(HotelServiceException.class);

    assertThrows(HotelServiceException.class,
        () -> hotelController.retrieveHotel(BigInteger.valueOf(Mockito.anyLong())));
  }

  @Test
  public void findAllHotel_shouldWork() {
    List<HotelDto> list = new ArrayList<>();
    HotelDto guestDto = HotelDto.builder().build();
    list.add(guestDto);
    Mockito.when(hotelService.findAllHotel()).thenReturn(list);
    ResponseEntity<List<HotelDto>> responses = hotelController.retrieveAllHotel();

    assertThat(responses.getBody()).hasSize(1);
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createHotel_whenValidUserName_shouldWork() {
    HotelCreateRequest request = HotelCreateRequest
        .builder()
        .name(Mockito.anyString())
        .build();
    Mockito.when(hotelService.create(request))
        .thenReturn(HotelDto.builder().build());
    ResponseEntity<HotelDto> responses = hotelController
        .createHotel(request);
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createHotel_whenInValidHotelName_shouldNotWork_andThrowException() {
    HotelCreateRequest request = HotelCreateRequest
        .builder()
        .name("mockName")
        .build();
    Mockito.when(hotelService.create(request))
        .thenThrow(HotelServiceException.class);

    assertThrows(HotelServiceException.class,
        () -> hotelController
            .createHotel(request));
  }

  @Test
  public void updateHotel_whenValidHotelId_shouldWork() {
    Mockito
        .when(hotelService.updateHotel(Mockito.any(BigInteger.class), Mockito.any(
            HotelUpdateRequest.class)))
        .thenReturn(HotelDto.builder().build());
    ResponseEntity<HotelDto> responses = hotelController
        .updateHotel(Mockito.any(BigInteger.class), Mockito.any(HotelUpdateRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void updateHotel_whenInValidHotelId_shouldNotWork_andThrowException() {
    HotelUpdateRequest request = HotelUpdateRequest
        .builder()
        .name("mockName")
        .build();
    Mockito
        .when(hotelService.updateHotel(BigInteger.valueOf(12), request))
        .thenThrow(HotelServiceException.class);

    assertThrows(HotelServiceException.class,
        () -> hotelController
            .updateHotel(BigInteger.valueOf(12), request));
  }

  @Test
  public void deleteHotel_whenValidHotelId_shouldWork() {
    Mockito.when(hotelService.deleteHotel(Mockito.any(BigInteger.class)))
        .thenReturn(HotelDto.builder().build());
    ResponseEntity<HotelDto> responses = hotelController.deleteHotel(Mockito.any(BigInteger.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void deleteHotel_whenInValidHotelId_shouldNotWork_andThrowException() {
    Mockito.when(hotelService.deleteHotel(Mockito.any(BigInteger.class)))
        .thenThrow(HotelServiceException.class);

    assertThrows(HotelServiceException.class,
        () -> hotelController
            .deleteHotel(BigInteger.valueOf(Mockito.anyLong())));
  }
}