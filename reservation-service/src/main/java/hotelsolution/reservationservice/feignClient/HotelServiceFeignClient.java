package hotelsolution.reservationservice.feignClient;

import hotelsolution.reservationservice.model.dto.HotelDto;
import hotelsolution.reservationservice.model.dto.RoomDto;
import java.math.BigInteger;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${internal-api.hotel-service.name}", path = "${internal-api.hotel-service.path}")
//@FeignClient(value = "${internal-api.hotel-service.name}", url = "http://localhost:8083/api/hotel/city")
public interface HotelServiceFeignClient {

  @GetMapping("/name/{hotelName}")
  ResponseEntity<HotelDto> retrieveHotelByName(@PathVariable String hotelName);

  @GetMapping("/city/{hotelCity}")
  ResponseEntity<List<HotelDto>> retrieveHotelByCity(@PathVariable String hotelCity);

  @GetMapping("/room/{hotelId}")
  ResponseEntity<List<RoomDto>> retrieveAllRoomByHotelId(@PathVariable BigInteger hotelId);
}
