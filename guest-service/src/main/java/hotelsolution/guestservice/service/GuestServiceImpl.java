package hotelsolution.guestservice.service;

import hotelsolution.guestservice.enums.ErrorResponse;
import hotelsolution.guestservice.exception.GuestServiceException;
import hotelsolution.guestservice.model.dto.GuestDto;
import hotelsolution.guestservice.model.entity.Guest;
import hotelsolution.guestservice.model.request.GuestCreateRequest;
import hotelsolution.guestservice.model.request.GuestUpdateRequest;
import hotelsolution.guestservice.repository.GuestRepository;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j(topic = "[GuestServiceImpl]")
@Service
public class GuestServiceImpl implements GuestService {

  private GuestRepository guestRepository;

  @Autowired
  public GuestServiceImpl(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @Override
  public GuestDto findGuestById(BigInteger guestId) {
    log.info("Retrieve guest with id [{}].", guestId);

    return guestRepository.findById(guestId)
        .map(Guest::toGuestDto)
        .orElseThrow(() -> {

          log.error("Guest not found with id [{}]", guestId);
          return new GuestServiceException(ErrorResponse.GUEST_NOT_FOUND_EXCEPTION);
        });
  }

  @Override
  public List<GuestDto> findAllGuest() {
    log.info("Retrieve all guests.");

    return guestRepository
        .findAll()
        .stream()
        .map(Guest::toGuestDto)
        .collect(Collectors.toList());
  }

  @Override
  public GuestDto create(GuestCreateRequest request) {
    log.info("Create guest with userName [{}]", request.getUserName());
    validateUserName(request.getUserName());
    log.info("Guest's profile saved successfully, with userName.", request.getUserName());

    return guestRepository
        .save(
            Guest.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .createdDateTime(Instant.now())
                .build())
        .toGuestDto();
  }

  public void validateUserName(String userName) {
    guestRepository.findByUserName(userName)
        .ifPresent(guest -> {
          throw new GuestServiceException(ErrorResponse.EXISTED_USERNAME_EXCEPTION);
        });
  }

  @Override
  public GuestDto updateGuest(BigInteger guestId, GuestUpdateRequest request) {
    log.info("Update guest with id [{}].", guestId);

    return guestRepository
        .findById(guestId)
        .map(guest -> {
          updateNewGuest(request, guest);
          log.info("Guest updated, with id [{}].", guest.getId());

          return guestRepository
              .save(guest)
              .toGuestDto();
        })
        .orElseThrow(
            () -> {

              log.error("Guest not found with id [{}]", guestId);
              return new GuestServiceException(ErrorResponse.GUEST_NOT_FOUND_EXCEPTION);
            });
  }

  private void updateNewGuest(GuestUpdateRequest request, Guest guest) {
    guest.setName(
        Optional.ofNullable(request.getName()).orElse(guest.getName()));
    guest.setPassword(
        Optional.ofNullable(request.getPassword()).orElse(guest.getPassword()));
  }

  @Override
  public GuestDto deleteGuest(BigInteger guestId) {

    return guestRepository
        .findById(guestId)
        .map(guest -> {
          log.info("Guest deleted, with id [{}].", guestId);
          GuestDto deletedGuest = guest.toGuestDto();
          guestRepository.deleteById(guestId);

          return deletedGuest;
        })
        .orElseThrow(() -> {

          log.error("Guest not found with id [{}]", guestId);
          return new GuestServiceException(ErrorResponse.GUEST_NOT_FOUND_EXCEPTION);
        });
  }

}
