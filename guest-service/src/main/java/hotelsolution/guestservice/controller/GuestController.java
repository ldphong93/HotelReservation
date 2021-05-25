package hotelsolution.guestservice.controller;

import hotelsolution.guestservice.model.dto.GuestDto;
import hotelsolution.guestservice.model.request.GuestCreateRequest;
import hotelsolution.guestservice.model.request.GuestUpdateRequest;
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

@RequestMapping("/api/guest")
public interface GuestController {

  @GetMapping("/{guestId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Guest found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = GuestDto.class))}),
      @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content)})
  ResponseEntity<GuestDto> retrieveGuest(@PathVariable BigInteger guestId);

  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "All guest found.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = GuestDto.class))}),
      @ApiResponse(responseCode = "404", description = "Guest not found.", content = @Content)})
  ResponseEntity<List<GuestDto>> retrieveAllGuest();

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Guest created.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = GuestDto.class))}),
      @ApiResponse(responseCode = "400", description = "Can not create guest", content = @Content)})
  ResponseEntity<GuestDto> createGuest(@Valid @RequestBody GuestCreateRequest request);

  @PutMapping("/{guestId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Guest updated.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = GuestDto.class))}),
      @ApiResponse(responseCode = "404", description = "Guest not found.", content = @Content)})
  ResponseEntity<GuestDto> updateGuest(@PathVariable BigInteger guestId,
      @Valid @RequestBody GuestUpdateRequest request);

  @DeleteMapping("/{guestId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Guest deleted.", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = GuestDto.class))}),
      @ApiResponse(responseCode = "404", description = "Guest not found.", content = @Content)})
  ResponseEntity<GuestDto> deleteGuest(@PathVariable BigInteger guestId);
}
