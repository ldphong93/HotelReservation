package hotelsolution.hotelservice.controller;

import hotelsolution.hotelservice.model.dto.HotelDto;
import hotelsolution.hotelservice.model.dto.RoomDto;
import hotelsolution.hotelservice.model.dto.RoomTypeDto;
import hotelsolution.hotelservice.model.request.HotelCreateRequest;
import hotelsolution.hotelservice.model.request.HotelUpdateRequest;
import hotelsolution.hotelservice.model.request.RoomRequest;
import hotelsolution.hotelservice.model.request.RoomTypeRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.math.BigInteger;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/hotel")
public interface HotelController {

  @GetMapping("/id/{hotelId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "404", description = "Hotel not found", content = @Content)})
  ResponseEntity<HotelDto> retrieveHotel(@PathVariable BigInteger hotelId);

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
      @ApiResponse(responseCode = "404", description = "Room not found.", content = @Content)})
  ResponseEntity<List<RoomDto>> retrieveAllRoomByHotelId(@PathVariable BigInteger hotelId);

  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "All hotel found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "404", description = "Hotel not found.", content = @Content)})
  ResponseEntity<List<HotelDto>> retrieveAllHotel();

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel created.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "400", description = "Can not create hotel", content = @Content)})
  ResponseEntity<HotelDto> createHotel(@Valid @RequestBody HotelCreateRequest request);

  @PutMapping("/{hotelId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel updated.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "404", description = "Guest not found.", content = @Content)})
  ResponseEntity<HotelDto> updateHotel(@PathVariable BigInteger hotelId,
      @Valid @RequestBody HotelUpdateRequest request);

  @DeleteMapping("/{hotelId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel deleted.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))}),
      @ApiResponse(responseCode = "404", description = "Hotel not found.", content = @Content)})
  ResponseEntity<HotelDto> deleteHotel(@PathVariable BigInteger hotelId);

  @PostMapping("/add-room-type")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Room type created.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = RoomTypeDto.class))}),
      @ApiResponse(responseCode = "400", description = "Can not create room type.", content = @Content)})
  ResponseEntity<RoomTypeDto> createRoomType(@Valid @RequestBody RoomTypeRequest roomTypeRequest);


  @PostMapping("/add-room")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Room created.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = RoomDto.class))}),
      @ApiResponse(responseCode = "400", description = "Can not create room", content = @Content)})
  ResponseEntity<RoomDto> createRoom(@Valid @RequestBody RoomRequest roomRequest);

}