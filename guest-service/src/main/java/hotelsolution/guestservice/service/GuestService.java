package hotelsolution.guestservice.service;

import hotelsolution.guestservice.model.dto.GuestDto;
import hotelsolution.guestservice.model.request.GuestCreateRequest;
import hotelsolution.guestservice.model.request.GuestUpdateRequest;
import java.math.BigInteger;
import java.util.List;

public interface GuestService {

  GuestDto findGuestById(BigInteger guestId);

  List<GuestDto> findAllGuest();

  GuestDto create(GuestCreateRequest request);

  GuestDto updateGuest(BigInteger guestId, GuestUpdateRequest request);

  GuestDto deleteGuest(BigInteger guestId);
}
