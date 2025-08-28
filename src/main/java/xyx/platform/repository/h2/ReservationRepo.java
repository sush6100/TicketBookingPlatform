package xyx.platform.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyx.platform.domain.entity.h2.SeatAvailability;

@Repository
public interface ReservationRepo extends JpaRepository<SeatAvailability, String> {

}
