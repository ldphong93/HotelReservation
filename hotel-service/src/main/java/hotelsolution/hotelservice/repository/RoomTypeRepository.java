package hotelsolution.hotelservice.repository;

import hotelsolution.hotelservice.model.entity.RoomType;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, BigInteger> {

}
