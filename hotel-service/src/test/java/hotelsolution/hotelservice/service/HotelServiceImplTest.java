package hotelsolution.hotelservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hotelsolution.hotelservice.enums.RoomStatus;
import hotelsolution.hotelservice.enums.RoomType;
import hotelsolution.hotelservice.exception.HotelServiceException;
import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.entity.Address;
import hotelsolution.hotelservice.model.entity.Hotel;
import hotelsolution.hotelservice.model.entity.Room;
import hotelsolution.hotelservice.model.request.AddressRequest;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.model.request.RoomRequest;
import hotelsolution.hotelservice.repository.HotelRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelServiceImplTest {


  public static final String HOTEL_NAME = "mockHotelName";
  public static final String STAR_RATING = "mockStarRating";
  public static final int TOTAL_ROOM = Math.toIntExact(NumberUtils.LONG_ONE);
  public static final String VALID_HOTEL_NAME = "mockValidHotelname";
  public static final String UPDATED_STAR_RATING = "updatedStarRating";
  public static final String UPDATED_NAME = "updatedName";
  public static final int UPDATED_TOTAL_ROOM = Math.toIntExact(NumberUtils.LONG_ZERO);

  public static final BigInteger ONE = BigInteger.valueOf(1);
  public static final BigInteger NON_EXIST_ID = BigInteger.valueOf(-999);
  public static final String MOCK_FULL_ADDRESS = "mockFullAddress";
  public static final String MOCK_CITY = "mockCity";
  public static final String MOCK_COUNTRY = "mockCountry";
  public static final BigDecimal PRICE = BigDecimal.valueOf(1);
  public static final String MOCK_ROOM_NUMBER = "mockRoomNumber";

  private HotelService hotelService;
  private HotelRepository hotelRepository;

  @BeforeEach
  void initialize() {

    Address addressMock = Address.builder()
        .fullAddress(MOCK_FULL_ADDRESS)
        .city(MOCK_CITY)
        .country(MOCK_COUNTRY)
        .build();

    Room roomMock = Room.builder()
        .roomNumber("mockRoomNumber")
        .roomStatus(RoomStatus.AVAILABLE)
        .roomType(RoomType.KING_BED)
        .price(PRICE)
        .build();

    List<Room> roomList = new ArrayList<>();
    roomList.add(roomMock);


    Hotel hotelMock = Hotel.builder()
        .id(ONE)
        .name(HOTEL_NAME)
        .starRating(STAR_RATING)
        .totalRoom(TOTAL_ROOM)
        .address(addressMock)
        .rooms(roomList)
        .build();

    hotelRepository = mock(HotelRepository.class);

    when(hotelRepository.findById(ONE)).thenReturn(Optional.of(hotelMock));
    when(hotelRepository.findAll()).thenReturn(List.of(hotelMock));
    when(hotelRepository.save(any(Hotel.class))).then(returnsFirstArg());
    when(hotelRepository.findByName(HOTEL_NAME)).thenReturn(Optional.of(hotelMock));

    hotelService = new HotelServiceImpl(hotelRepository);
  }

  @Test
  public void findHotelById_whenExisted_thenHotelFound() {
    HotelDto hotelDto = hotelService.findHotelById(ONE);

    assertThat(hotelDto.getId()).isEqualTo(ONE);
    assertThat(hotelDto.getName()).isEqualTo(HOTEL_NAME);
    assertThat(hotelDto.getStarRating()).isEqualTo(STAR_RATING);
    assertThat(hotelDto.getTotalRoom()).isEqualTo(TOTAL_ROOM);
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

    HotelCreateRequest request = HotelCreateRequest.builder()
        .name(HOTEL_NAME)
        .starRating(STAR_RATING)
        .totalRoom(TOTAL_ROOM)
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

    RoomRequest roomMock = RoomRequest.builder()
        .roomNumber(MOCK_ROOM_NUMBER)
        .roomStatus(RoomStatus.AVAILABLE)
        .roomType(RoomType.KING_BED)
        .price(PRICE)
        .build();

    List<RoomRequest> roomList = new ArrayList<>();
    roomList.add(roomMock);

    HotelCreateRequest request = HotelCreateRequest.builder()
        .name(VALID_HOTEL_NAME)
        .starRating(STAR_RATING)
        .totalRoom(TOTAL_ROOM)
        .addressRequest(addressMock)
        .roomRequestList(roomList)
        .build();

    HotelDto savedHotel = hotelService.create(request);

    verify(hotelRepository, times(NumberUtils.INTEGER_ONE)).save(any(Hotel.class));
    assertThat(savedHotel.getName()).isEqualTo(request.getName());
    assertThat(savedHotel.getStarRating()).isEqualTo(request.getStarRating());
    assertThat(savedHotel.getTotalRoom()).isEqualTo(request.getTotalRoom());
  }

  @Test
  public void updateGuest_whenValidGuestId_thenGuestUpdated() {
    HotelUpdateRequest request = HotelUpdateRequest.builder()
        .name(UPDATED_NAME)
        .starRating(UPDATED_STAR_RATING)
        .totalRoom(UPDATED_TOTAL_ROOM)
        .build();

    HotelDto updatedHotel = hotelService.updateHotel(BigInteger.valueOf(1), request);

    assertThat(updatedHotel.getName()).isEqualTo(HOTEL_NAME);
    assertThat(updatedHotel.getStarRating()).isEqualTo(UPDATED_STAR_RATING);
    assertThat(updatedHotel.getTotalRoom()).isEqualTo(UPDATED_TOTAL_ROOM);
  }

  @Test
  public void updateHotel_whenNonExistedId_thenExceptionThrown() {
    HotelUpdateRequest request = HotelUpdateRequest.builder()
        .name(UPDATED_NAME)
        .starRating(UPDATED_STAR_RATING)
        .totalRoom(UPDATED_TOTAL_ROOM)
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
