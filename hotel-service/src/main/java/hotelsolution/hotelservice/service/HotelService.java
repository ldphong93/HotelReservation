package hotelsolution.hotelservice.service;

import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.dto.RoomDto;
import hotelsolution.hotelservice.model.dto.RoomTypeDto;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.model.request.RoomRequest;
import hotelsolution.hotelservice.model.request.RoomTypeRequest;
import java.math.BigInteger;
import java.util.List;

public interface HotelService {

  HotelDto findHotelById(BigInteger hotelId);

  List<HotelDto> findAllHotel();

  HotelDto create(HotelCreateRequest request);

  HotelDto updateHotel(BigInteger hotelId, HotelUpdateRequest request);

  HotelDto deleteHotel(BigInteger hotelId);

  RoomTypeDto addRoomType(RoomTypeRequest roomTypeRequest);

  RoomDto addRoom(RoomRequest roomRequest);

  HotelDto findHotelByName(String hotelName);

  List<HotelDto> findHotelByCity(String hotelCity);

  List<RoomDto> findAllRoomByHotelId(BigInteger hotelId);
}
