package hotelsolution.reservationservice.repository;

import hotelsolution.reservationservice.model.entity.Reservation;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, BigInteger> {

  List<Reservation> findAllByRoomId(BigInteger roomId);

  List<Reservation> findAllByGuestId(BigInteger guestId);
}
