package hotelsolution.hotelservice.service;

import hotelsolution.hotelservice.enums.ErrorResponse;
import hotelsolution.hotelservice.exception.HotelServiceException;
import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.dto.RoomDto;
import hotelsolution.hotelservice.model.dto.RoomTypeDto;
import hotelsolution.hotelservice.model.entity.Hotel;
import hotelsolution.hotelservice.model.entity.Room;
import hotelsolution.hotelservice.model.entity.RoomType;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.model.request.RoomRequest;
import hotelsolution.hotelservice.model.request.RoomTypeRequest;
import hotelsolution.hotelservice.repository.HotelRepository;
import hotelsolution.hotelservice.repository.RoomRepository;
import hotelsolution.hotelservice.repository.RoomTypeRepository;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j(topic = "[HotelServiceImpl]")
@Service
public class HotelServiceImpl implements HotelService {

  private HotelRepository hotelRepository;
  private RoomRepository roomRepository;
  private RoomTypeRepository roomTypeRepository;

  @Autowired
  public HotelServiceImpl(HotelRepository hotelRepository,
      RoomRepository roomRepository,
      RoomTypeRepository roomTypeRepository) {
    this.hotelRepository = hotelRepository;
    this.roomRepository = roomRepository;
    this.roomTypeRepository = roomTypeRepository;
  }

  @Override
  public HotelDto findHotelById(BigInteger hotelId) {
    log.info("Retrieve hotel with id [{}].", hotelId);

    return hotelRepository.findById(hotelId)
        .map(Hotel::toHotelDto)
        .orElseThrow(() -> {

          log.error("Hotel not found with id [{}].", hotelId);
          return new HotelServiceException(ErrorResponse.HOTEL_NOT_FOUND_EXCEPTION);
        });
  }

  @Override
  public HotelDto findHotelByName(String hotelName) {
    log.info("Retrieve hotel with name [{}].", hotelName);

    return hotelRepository
        .findByName(hotelName)
        .map(Hotel::toHotelDto)
        .orElseThrow(() -> {

          log.error("Hotel not found with name [{}].", hotelName);
          return new HotelServiceException(ErrorResponse.HOTEL_NOT_FOUND_EXCEPTION);
        });
  }

  @Override
  public List<HotelDto> findHotelByCity(String hotelCity) {
    log.info("Retrieve hotel with city [{}].", hotelCity);

    return hotelRepository
        .findAllByAddress_City(hotelCity)
        .stream()
        .map(Hotel::toHotelDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<RoomDto> findAllRoomByHotelId(BigInteger hotelId) {
    log.info("Retrieve all room by hotel id [{}]", hotelId);

    return roomRepository.findAllByHotelId(hotelId)
        .stream()
        .map(Room::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<HotelDto> findAllHotel() {
    log.info("Retrieve all hotel.");

    return hotelRepository
        .findAll()
        .stream()
        .map(Hotel::toHotelDto)
        .collect(Collectors.toList());
  }

  @Override
  public HotelDto create(HotelCreateRequest request) {
    log.info("Create hotel with name [{}].", request.getName());
    validateHotelName(request.getName());
    log.info("Hotel's info saved successfully, with name [{}].", request.getName());

    return hotelRepository
        .save(
            Hotel.builder()
                .name(request.getName())
                .starRating(request.getStarRating())
                .address(request.getAddressRequest().toAddressDao())
                .build())
        .toHotelDto();
  }

  public void validateHotelName(String name) {
    hotelRepository.findByName(name)
        .ifPresent(hotel -> {
          throw new HotelServiceException(ErrorResponse.EXISTED_HOTEL_NAME_EXCEPTION);
        });
  }

  @Override
  public HotelDto updateHotel(BigInteger hotelId, HotelUpdateRequest request) {
    log.info("Update hotel with id [{}].", hotelId);
    validateHotelName(request.getName());
    log.info("Hotel's info edited successfully, with name [{}].", request.getName());

    return hotelRepository
        .findById(hotelId)
        .map(hotel -> {
          updateNewHotel(request, hotel);
          log.info("Hotel updated, with id [{}].", hotel.getId());

          return hotelRepository
              .save(hotel)
              .toHotelDto();
        })
        .orElseThrow(
            () -> {

              log.error("Hotel not found with id [{}]", hotelId);
              return new HotelServiceException(ErrorResponse.HOTEL_NOT_FOUND_EXCEPTION);
            });
  }

  private void updateNewHotel(HotelUpdateRequest request, Hotel hotel) {
    hotel.setStarRating(Optional
        .ofNullable(request.getStarRating())
        .orElse(hotel.getName()));
    hotel.setAddress(Optional
        .ofNullable(request
            .getAddressRequest()
            .toAddressDao())
        .orElse(hotel.getAddress()));
  }

  @Override
  public HotelDto deleteHotel(BigInteger hotelId) {
    return hotelRepository
        .findById(hotelId)
        .map(hotel -> {
          log.info("Hotel deleted, with id [{}].", hotelId);
          HotelDto deletedHotel = hotel.toHotelDto();
          hotelRepository.deleteById(hotelId);

          return deletedHotel;
        })
        .orElseThrow(() -> {

          log.error("Hotel not found with id [{}]", hotelId);
          return new HotelServiceException(ErrorResponse.HOTEL_NOT_FOUND_EXCEPTION);
        });
  }

  @Override
  public RoomTypeDto addRoomType(RoomTypeRequest roomTypeRequest) {
    log.info("Create room type with name [{}].", roomTypeRequest.getName());

    return roomTypeRepository.save(
        RoomType.builder()
            .name(roomTypeRequest.getName())
            .capacity(roomTypeRequest.getCapacity())
            .rentFee(roomTypeRequest.getRentFee())
            .build())
        .toDto();
  }

  @Override
  public RoomDto addRoom(RoomRequest roomRequest) {
    log.info("Create room with room number [{}].", roomRequest.getRoomNumber());

    return roomRepository.save(
        Room.builder()
            .roomNumber(roomRequest.getRoomNumber())
            .roomType(roomTypeRepository.findById(roomRequest.getRoomTypeId()).get())
            .hotel(hotelRepository.findById(roomRequest.getHotelId()).get())
            .build())
        .toDto();
  }

}
