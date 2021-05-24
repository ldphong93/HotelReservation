package hotelsolution.guestservice.repository;

import hotelsolution.guestservice.model.entity.Guest;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, BigInteger> {

  Optional<Guest> findByUserName(String userName);
}
