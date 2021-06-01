package hotelsolution.reservationservice.controller;

import hotelsolution.reservationservice.model.dto.HotelDto;
import hotelsolution.reservationservice.model.dto.ReservationDto;
import hotelsolution.reservationservice.model.dto.RoomDto;
import hotelsolution.reservationservice.model.entity.ResponseAPI;
import hotelsolution.reservationservice.model.request.CreateReservationRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.math.BigInteger;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/reservation")
public interface ReservationController {

  @GetMapping("/name/API/{hotelName}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "404", description = "Hotel not found", content = @Content)})
  ResponseAPI retrieveHotelByNameAPI(@PathVariable String hotelName);

  @GetMapping("/name/{hotelName}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "404", description = "Hotel not found", content = @Content)})
  ResponseEntity<HotelDto> retrieveHotelByName(@PathVariable String hotelName);

  @GetMapping("/city/{hotelCity}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "404", description = "Hotel not found", content = @Content)})
  ResponseEntity<List<HotelDto>> retrieveHotelByCity(@PathVariable String hotelCity);

  @GetMapping("/room/{hotelId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "All room found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = RoomDto.class))}),
      @ApiResponse(responseCode = "404", description = "Room not found", content = @Content)})
  ResponseEntity<List<RoomDto>> retrieveAllRoomByHotelId(@PathVariable BigInteger hotelId);

  @PostMapping("/create")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Reservation created.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationDto.class))}),
      @ApiResponse(responseCode = "404", description = "Can not create reservation.", content = @Content)})
  ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody CreateReservationRequest request);

  @GetMapping("/booking/{guestId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Guest's reservation found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationDto.class))}),
      @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content)})
  ResponseEntity<List<ReservationDto>> retrieveAllReservationByGuestId(@PathVariable BigInteger guestId);

}
