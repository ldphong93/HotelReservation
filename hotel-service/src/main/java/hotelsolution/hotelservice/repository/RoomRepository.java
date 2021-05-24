package hotelsolution.hotelservice.repository;

import hotelsolution.hotelservice.model.entity.Room;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, BigInteger> {

}
