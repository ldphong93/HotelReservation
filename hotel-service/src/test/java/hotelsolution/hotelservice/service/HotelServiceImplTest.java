package hotelsolution.hotelservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hotelsolution.hotelservice.exception.HotelServiceException;
import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.entity.Address;
import hotelsolution.hotelservice.model.entity.Hotel;
import hotelsolution.hotelservice.model.request.AddressRequest;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.repository.HotelRepository;
import hotelsolution.hotelservice.repository.RoomRepository;
import hotelsolution.hotelservice.repository.RoomTypeRepository;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelServiceImplTest {
  public static final String HOTEL_NAME = "mockHotelName";
  public static final String STAR_RATING = "mockStarRating";
  public static final String VALID_HOTEL_NAME = "mockValidHotelName";
  public static final String UPDATED_STAR_RATING = "updatedStarRating";
  public static final String UPDATED_NAME = "updatedName";

  public static final BigInteger ONE = BigInteger.valueOf(1);
  public static final BigInteger NON_EXIST_ID = BigInteger.valueOf(-999);
  public static final String MOCK_FULL_ADDRESS = "mockFullAddress";
  public static final String MOCK_CITY = "mockCity";
  public static final String MOCK_COUNTRY = "mockCountry";

  private HotelService hotelService;
  private HotelRepository hotelRepository;
  private RoomRepository roomRepository;
  private RoomTypeRepository roomTypeRepository;

  @BeforeEach
  void setUp() {
    Address addressMock = Address.builder()
        .fullAddress(MOCK_FULL_ADDRESS)
        .city(MOCK_CITY)
        .country(MOCK_COUNTRY)
        .build();

    Hotel hotelMock = Hotel.builder()
        .id(ONE)
        .name(HOTEL_NAME)
        .starRating(STAR_RATING)
        .address(addressMock)
        .build();

    hotelRepository = mock(HotelRepository.class);
    roomRepository = mock(RoomRepository.class);
    roomTypeRepository = mock(RoomTypeRepository.class);
    hotelService = new HotelServiceImpl(hotelRepository, roomRepository, roomTypeRepository);

    when(hotelRepository.findById(ONE)).thenReturn(Optional.of(hotelMock));
    when(hotelRepository.findAll()).thenReturn(List.of(hotelMock));
    when(hotelRepository.findAll()).thenReturn(List.of(hotelMock));
    when(hotelRepository.save(any(Hotel.class))).then(returnsFirstArg());
    when(hotelRepository.findByName(HOTEL_NAME)).thenReturn(Optional.of(hotelMock));
  }

  @Test
  public void findHotelById_whenExisted_thenHotelFound() {
    Address addressMock = Address.builder()
        .fullAddress(MOCK_FULL_ADDRESS)
        .city(MOCK_CITY)
        .country(MOCK_COUNTRY)
        .build();
    Hotel hotelMock = Hotel.builder()
        .id(ONE)
        .name(HOTEL_NAME)
        .starRating(STAR_RATING)
        .address(addressMock)
        .build();
    HotelDto hotelDto = hotelService.findHotelById(ONE);
    assertThat(hotelDto.getId()).isEqualTo(ONE);
    assertThat(hotelDto.getName()).isEqualTo(HOTEL_NAME);
    assertThat(hotelDto.getStarRating()).isEqualTo(STAR_RATING);
  }

  @Test
  public void findHotelById_whenNonExisted_thenExceptionThrown() {
    assertThrows(HotelServiceException.class,
        () -> hotelService.findHotelById(NON_EXIST_ID));
  }

  @Test
  public void findAllHotel_thenReturnListOfHotel() {
    List<HotelDto> hotelDtoList = hotelService.findAllHotel();
    assertThat(hotelDtoList)
        .hasSize(NumberUtils.INTEGER_ONE)
        .extracting(HotelDto::getId)
        .contains(ONE);
  }

  @Test
  public void createHotel_whenHotelNameAlreadyExisted_thenExceptionThrown() {
    AddressRequest addressMock = AddressRequest.builder()
        .fullAddress(MOCK_FULL_ADDRESS)
        .city(MOCK_CITY)
        .country(MOCK_COUNTRY)
        .build();
    HotelCreateRequest request = HotelCreateRequest.builder()
        .name(HOTEL_NAME)
        .starRating(STAR_RATING)
        .addressRequest(addressMock)
        .build();
    assertThrows(HotelServiceException.class, () -> hotelService.create(request));
  }

  @Test
  public void createHotel_whenValidHotelName_thenHotelSaved() {
    AddressRequest addressMock = AddressRequest.builder()
        .fullAddress(MOCK_FULL_ADDRESS)
        .city(MOCK_CITY)
        .country(MOCK_COUNTRY)
        .build();
    HotelCreateRequest request = HotelCreateRequest.builder()
        .name(VALID_HOTEL_NAME)
        .starRating(STAR_RATING)
        .addressRequest(addressMock)
        .build();
    HotelDto savedHotel = hotelService.create(request);

    verify(hotelRepository, times(NumberUtils.INTEGER_ONE)).save(any(Hotel.class));
    assertThat(savedHotel.getName()).isEqualTo(request.getName());
    assertThat(savedHotel.getStarRating()).isEqualTo(request.getStarRating());

  }

  @Test
  public void updateGuest_whenValidGuestId_thenGuestUpdated() {
    AddressRequest addressUpdateMock = AddressRequest.builder()
        .fullAddress("fullAddressUpdateMock")
        .city("cityUpdateMock")
        .country("countryUpdateMock")
        .build();
    HotelUpdateRequest request = HotelUpdateRequest.builder()
        .name(UPDATED_NAME)
        .starRating(UPDATED_STAR_RATING)
        .addressRequest(addressUpdateMock)
        .build();
    HotelDto updatedHotel = hotelService.updateHotel(BigInteger.valueOf(1), request);

    assertThat(updatedHotel.getName()).isEqualTo(HOTEL_NAME);
    assertThat(updatedHotel.getStarRating()).isEqualTo(UPDATED_STAR_RATING);
  }

  @Test
  public void updateHotel_whenNonExistedId_thenExceptionThrown() {
    HotelUpdateRequest request = HotelUpdateRequest.builder()
        .name(UPDATED_NAME)
        .starRating(UPDATED_STAR_RATING)
        .build();

    assertThrows(HotelServiceException.class,
        () -> hotelService.updateHotel(NON_EXIST_ID, request));
  }

  @Test
  public void deleteGuest_whenValidGuestId_thenGuestDeleted() {

    assertThat(hotelService.findHotelById(BigInteger.valueOf(1))).isNotNull();
    hotelService.deleteHotel(BigInteger.valueOf(1));
    verify(hotelRepository, times(NumberUtils.INTEGER_ONE)).deleteById((ONE));
  }

  @Test
  public void deleteGuest_whenNonExisted_thenExceptionThrown() {

    assertThrows(HotelServiceException.class,
        () -> hotelService.deleteHotel(NON_EXIST_ID));
  }

}
