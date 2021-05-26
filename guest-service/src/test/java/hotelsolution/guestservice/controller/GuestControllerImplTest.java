package hotelsolution.guestservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import hotelsolution.guestservice.exception.GuestServiceException;
import hotelsolution.guestservice.model.dto.GuestDto;
import hotelsolution.guestservice.model.request.GuestCreateRequest;
import hotelsolution.guestservice.model.request.GuestUpdateRequest;
import hotelsolution.guestservice.service.GuestService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@ExtendWith(SpringExtension.class)
public class GuestControllerImplTest {

  private GuestController guestController;
  private GuestService guestService;

  @BeforeEach
  public void initialize() {
    guestService = mock(GuestService.class);
    guestController = new GuestControllerImpl(guestService);
  }

  @Test
  public void findGuestById_shouldWork() {
    Mockito.when(guestService.findGuestById(Mockito.any(BigInteger.class)))
        .thenReturn(GuestDto.builder().build());
    ResponseEntity<GuestDto> responses = guestController
        .retrieveGuest(BigInteger.valueOf(Mockito.anyLong()));

    Assertions.assertNotNull(responses.getBody());
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void findGuestById_whenNonExisted_shouldNotWork_andThrowException() {
    Mockito.when(guestService.findGuestById(Mockito.any(BigInteger.class)))
        .thenThrow(GuestServiceException.class);

    assertThrows(GuestServiceException.class,
        () -> guestController.retrieveGuest(BigInteger.valueOf(Mockito.anyLong())));
  }

  @Test
  public void findAllGuest_shouldWork() {
    List<GuestDto> list = new ArrayList<>();
    GuestDto guestDto = GuestDto.builder().build();
    list.add(guestDto);
    Mockito.when(guestService.findAllGuest()).thenReturn(list);
    ResponseEntity<List<GuestDto>> responses = guestController.retrieveAllGuest();

    assertThat(responses.getBody()).hasSize(1);
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createGuest_whenValidUserName_shouldWork() {
    GuestCreateRequest request = GuestCreateRequest
        .builder()
        .userName(Mockito.anyString())
        .build();
    Mockito.when(guestService.create(request))
        .thenReturn(GuestDto.builder().build());
    ResponseEntity<GuestDto> responses = guestController
        .createGuest(request);
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createGuest_whenInValidUserName_shouldNotWork_andThrowException() {
    GuestCreateRequest request = GuestCreateRequest
        .builder()
        .userName("mockUserName")
        .build();
    Mockito.when(guestService.create(request))
        .thenThrow(GuestServiceException.class);

    assertThrows(GuestServiceException.class,
        () -> guestController
            .createGuest(request));
  }

  @Test
  public void updateGuest_whenValidGuestId_shouldWork() {
    Mockito
        .when(guestService.updateGuest(Mockito.any(BigInteger.class), Mockito.any(
            GuestUpdateRequest.class)))
        .thenReturn(GuestDto.builder().build());
    ResponseEntity<GuestDto> responses = guestController
        .updateGuest(Mockito.any(BigInteger.class), Mockito.any(GuestUpdateRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void deleteGuest_whenValidGuestId_shouldWork() {
    Mockito.when(guestService.deleteGuest(Mockito.any(BigInteger.class)))
        .thenReturn(GuestDto.builder().build());
    ResponseEntity<GuestDto> responses = guestController.deleteGuest(Mockito.any(BigInteger.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void deleteGuest_whenInValidGuestId_shouldNotWork_andThrowException() {
    Mockito.when(guestService.deleteGuest(Mockito.any(BigInteger.class)))
        .thenThrow(GuestServiceException.class);

    assertThrows(GuestServiceException.class,
        () -> guestController
            .deleteGuest(BigInteger.valueOf(Mockito.anyLong())));
  }
}