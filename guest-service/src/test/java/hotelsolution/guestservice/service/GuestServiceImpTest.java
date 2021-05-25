package hotelsolution.guestservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hotelsolution.guestservice.exception.GuestServiceException;
import hotelsolution.guestservice.model.dto.GuestDto;
import hotelsolution.guestservice.model.entity.Guest;
import hotelsolution.guestservice.model.request.GuestCreateRequest;
import hotelsolution.guestservice.model.request.GuestUpdateRequest;
import hotelsolution.guestservice.repository.GuestRepository;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuestServiceImpTest {

  public static final String USERNAME = "mockUsername";
  public static final String PASSWORD = "mockPassword";
  public static final String NAME = "mockName";
  public static final String PHONE_NUMBER = "mockPhoneNumber";
  public static final String MOCK_VALID_USERNAME = "mockValidUsername";
  public static final String UPDATED_PASSWORD = "updatedPassword";
  public static final String UPDATED_NAME = "updatedName";
  public static final BigInteger ONE = BigInteger.valueOf(1);
  public static final BigInteger NON_EXIST_ID = BigInteger.valueOf(-999);

  private GuestService guestService;
  private GuestRepository guestRepository;

  @BeforeEach
  void initialize() {
    Guest guestMock = Guest.builder()
        .id(BigInteger.valueOf(1))
        .userName(USERNAME)
        .password(PASSWORD)
        .name(NAME)
        .phoneNumber(PHONE_NUMBER)
        .createdDateTime(Instant.now())
        .build();
    guestRepository = mock(GuestRepository.class);

    when(guestRepository.findById(ONE)).thenReturn(Optional.of(guestMock));
    when(guestRepository.findAll()).thenReturn(List.of(guestMock));
    when(guestRepository.save(any(Guest.class))).then(returnsFirstArg());
    when(guestRepository.findByUserName(USERNAME)).thenReturn(Optional.of(guestMock));

    guestService = new GuestServiceImpl(guestRepository);
  }

  @Test
  public void findGuestById_whenExisted_thenGuestFound() {
    GuestDto guestDto = guestService.findGuestById(ONE);

    assertThat(guestDto.getId()).isEqualTo(ONE);
    assertThat(guestDto.getUserName()).isEqualTo(USERNAME);
    assertThat(guestDto.getPassword()).isEqualTo(PASSWORD);
    assertThat(guestDto.getName()).isEqualTo(NAME);
    assertThat(guestDto.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
  }

  @Test
  public void findGuestById_whenNonExisted_thenExceptionThrown() {

    assertThrows(GuestServiceException.class,
        () -> guestService.findGuestById(NON_EXIST_ID));
  }

  @Test
  public void findAllGuest_thenReturnListOfGuest() {
    List<GuestDto> guestDtoList = guestService.findAllGuest();

    assertThat(guestDtoList)
        .hasSize(NumberUtils.INTEGER_ONE)
        .extracting(GuestDto::getId)
        .contains(ONE);
  }

  @Test
  public void createGuest_whenUserNameAlreadyExisted_thenExceptionThrown() {
    GuestCreateRequest request = GuestCreateRequest.builder()
        .userName(USERNAME)
        .password(PASSWORD)
        .name(NAME)
        .phoneNumber(PHONE_NUMBER)
        .build();

    assertThrows(GuestServiceException.class, () -> guestService.create(request));
  }

  @Test
  public void createGuest_whenValidUserName_thenGuestSaved() {
    GuestCreateRequest request = GuestCreateRequest.builder()
        .userName(MOCK_VALID_USERNAME)
        .password(PASSWORD)
        .name(NAME)
        .phoneNumber(PHONE_NUMBER)
        .build();
    GuestDto savedGuest = guestService.create(request);

    verify(guestRepository, times(NumberUtils.INTEGER_ONE)).save(any(Guest.class));
    assertThat(savedGuest.getUserName()).isEqualTo(request.getUserName());
    assertThat(savedGuest.getPassword()).isEqualTo(request.getPassword());
    assertThat(savedGuest.getName()).isEqualTo(request.getName());
    assertThat(savedGuest.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
  }

  @Test
  public void updateGuest_whenValidGuestId_thenGuestUpdated() {
    GuestUpdateRequest request = GuestUpdateRequest.builder()
        .password(UPDATED_PASSWORD)
        .name(UPDATED_NAME)
        .build();

    GuestDto updatedGuest = guestService.updateGuest(BigInteger.valueOf(1), request);

    assertThat(updatedGuest.getUserName()).isEqualTo(USERNAME); //value unchanged
    assertThat(updatedGuest.getPassword()).isEqualTo(UPDATED_PASSWORD);
    assertThat(updatedGuest.getName()).isEqualTo(UPDATED_NAME);
    assertThat(updatedGuest.getPhoneNumber()).isEqualTo(PHONE_NUMBER); //value unchanged
  }

  @Test
  public void updateGuest_whenNonExistedId_thenExceptionThrown() {
    GuestUpdateRequest request = GuestUpdateRequest.builder()
        .password(UPDATED_PASSWORD)
        .name(UPDATED_NAME)
        .build();

    assertThrows(GuestServiceException.class,
        () -> guestService.updateGuest(NON_EXIST_ID, request));
  }

  @Test
  public void deleteGuest_whenValidGuestId_thenGuestDeleted() {
    assertThat(guestService.findGuestById(BigInteger.valueOf(1))).isNotNull();
    guestService.deleteGuest(BigInteger.valueOf(1));
    verify(guestRepository, times(NumberUtils.INTEGER_ONE)).deleteById((ONE));

  }

  @Test
  public void deleteGuest_whenNonExisted_thenExceptionThrown() {

    assertThrows(GuestServiceException.class,
        () -> guestService.deleteGuest(NON_EXIST_ID));
  }

}
