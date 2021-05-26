package hotelsolution.hotelservice.repository;

import hotelsolution.hotelservice.model.entity.Hotel;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, BigInteger> {

  Optional<Hotel> findByName(String name);

}
