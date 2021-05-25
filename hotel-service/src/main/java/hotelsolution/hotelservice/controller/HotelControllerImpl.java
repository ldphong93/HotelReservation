package hotelsolution.hotelservice.controller;

import hotelsolution.hotelservice.model.entity.Hotel;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.repository.HotelRepository;
import hotelsolution.hotelservice.repository.RoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel")
public class HotelControllerImpl {

  @Autowired
  private HotelRepository hotelRepository;
  @Autowired
  private RoomRepository roomRepository;

  @GetMapping
  public List<Hotel> getAllHotel(){
    return hotelRepository.findAll();
  }

  @PostMapping
  void createGuest( @RequestBody HotelCreateRequest request){
    Hotel hotel = Hotel.builder()
        .name(request.getName())
        .starRating(request.getStarRating())
        .address(request.getAddress())
        .totalRoom(request.getTotalRoom())
        .roomList(request.getListRoom())
        .build();

    hotelRepository.save(hotel);

  }
}
