package hotelsolution.hotelservice.repository;

import hotelsolution.hotelservice.model.entity.Room;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, BigInteger> {

  List<Room> findAllByHotelId(BigInteger hotelId);
}
