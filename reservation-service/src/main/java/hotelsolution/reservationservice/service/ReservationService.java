package hotelsolution.reservationservice.service;

import hotelsolution.reservationservice.model.dto.HotelDto;
import hotelsolution.reservationservice.model.dto.ReservationDto;
import hotelsolution.reservationservice.model.dto.RoomDto;
import hotelsolution.reservationservice.model.request.CreateReservationRequest;
import java.math.BigInteger;
import java.util.List;

public interface ReservationService {

  HotelDto findHotelByName(String hotelName);

  List<HotelDto> findAllHotelByCity(String hotelCity);

  List<RoomDto> findAllRoomByHotelId(BigInteger hotelId);

  ReservationDto create(CreateReservationRequest request);

  List<ReservationDto> findAllReservationByGuestId(BigInteger guestId);

}
