package hotelsolution.hotelservice.repository;

import hotelsolution.hotelservice.model.entity.Hotel;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, BigInteger> {

  Optional<Hotel> findByName(String name);

  /*@Query(
      value = "SELECT * FROM hotel INNER JOIN address ON hotel.address_id= address.id AND address.city = ?1",
      nativeQuery = true)
  Optional<Hotel> findAllByCity(String hotelCity); Alternative*/

  Optional<Hotel> findAllByAddress_City(String hotelCity);
}
