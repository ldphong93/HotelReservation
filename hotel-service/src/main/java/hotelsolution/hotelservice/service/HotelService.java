package hotelsolution.hotelservice.service;

import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import java.math.BigInteger;
import java.util.List;

public interface HotelService {

  HotelDto findHotelById(BigInteger hotelId);

  List<HotelDto> findAllHotel();

  HotelDto create(HotelCreateRequest request);

  HotelDto updateHotel(BigInteger hotelId, HotelUpdateRequest request);

  HotelDto deleteHotel(BigInteger hotelId);
}
